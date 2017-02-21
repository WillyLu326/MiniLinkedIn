package willy.individual.com.minilinkedinapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.text.TextUtilsCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import willy.individual.com.minilinkedinapp.models.BasicInfo;
import willy.individual.com.minilinkedinapp.models.Education;
import willy.individual.com.minilinkedinapp.utils.DateUtils;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_EDUCATION = 100;

    private TextView usernameTv;
    private TextView emailTv;
    private TextView educationTv;
    private TextView courseTv;

    private BasicInfo basicInfo;
    private List<Education> educations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fakeData();
        setupUI();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQ_CODE_EDUCATION && resultCode == Activity.RESULT_OK) {
            Education education = data.getParcelableExtra(EducationEditActivity.KEY_EDUCATION);
            updateEducations(education);
            setupEducations();
        }
    }

    private void updateEducations(Education newEducation) {
        boolean found = false;
        for (int i = 0; i < educations.size(); ++i) {
            if (TextUtils.equals(newEducation.id, educations.get(i).id)) {
                educations.set(i, newEducation);
                found = true;
                break;
            }
        }

        if (!found) {
            educations.add(newEducation);
        }
    }

    private void setupUI() {
        setupBasicInfo();
        setupEducations();
    }

    private void setupBasicInfo() {
        usernameTv  = (TextView) findViewById(R.id.profile_name);
        emailTv     = (TextView) findViewById(R.id.profile_email);

        usernameTv.setText(basicInfo.userName);
        emailTv.setText(basicInfo.email);
    }

    private void setupEducations() {
        // Education Add Button Listener
        findViewById(R.id.education_add_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EducationEditActivity.class);
                startActivityForResult(intent, REQ_CODE_EDUCATION);
            }
        });

        LinearLayout educationsView = (LinearLayout) findViewById(R.id.educations_layout);
        educationsView.removeAllViews();
        for (Education education : educations) {
            View view = getEducationView(education);
            educationsView.addView(view);
        }
    }

    private View getEducationView(final Education education) {
        View view = getLayoutInflater().inflate(R.layout.education_item, null);
        educationTv = (TextView) view.findViewById(R.id.education_info);
        courseTv    = (TextView) view.findViewById(R.id.education_courses);

        educationTv.setText(education.schoolName + " " + education.major + " ("
                + DateUtils.dateToString(education.startDate) + " ~ "
                + DateUtils.dateToString(education.endDate) + ")");
        courseTv.setText(getEducationCourses(education));

        view.findViewById(R.id.education_edit_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, EducationEditActivity.class);
                intent.putExtra(EducationEditActivity.KEY_EDUCATION, education);
                startActivityForResult(intent, REQ_CODE_EDUCATION);
            }
        });

        return view;
    }



    private void fakeData() {
        basicInfo = new BasicInfo();
        basicInfo.userName = "Willy Lu";
        basicInfo.email = "willylu@email.com";

        educations = new ArrayList<>();

        Education education1 = new Education();
        education1.schoolName = "CPP";
        education1.major = "CS";
        education1.startDate = DateUtils.stringToDate("09/2012");
        education1.endDate = DateUtils.stringToDate("06/2013");
        education1.courses = new ArrayList<>();
        education1.courses.add("Angular 2");
        education1.courses.add("Node JS");
        education1.courses.add("Android");

        Education education2 = new Education();
        education2.schoolName = "Cal Poly";
        education2.major = "CS";
        education2.startDate = DateUtils.stringToDate("09/2014");
        education2.endDate = DateUtils.stringToDate("06/2015");
        education2.courses = new ArrayList<>();
        education2.courses.add("MEAN Stack");
        education2.courses.add("Primeng");
        education2.courses.add("Ionic 2");

        educations.add(education1);
        educations.add(education2);
    }

    private String getEducationCourses(Education education) {
        String result = "";
        for (int i = 0; i < education.courses.size(); ++i) {
            if (i == education.courses.size() - 1) {
                result += "- " + education.courses.get(i);
            } else {
                result += "- " + education.courses.get(i) + "\n";
            }
        }
        return result;
    }
}
