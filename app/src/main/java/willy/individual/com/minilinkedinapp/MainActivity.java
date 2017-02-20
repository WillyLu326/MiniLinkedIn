package willy.individual.com.minilinkedinapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import willy.individual.com.minilinkedinapp.models.BasicInfo;
import willy.individual.com.minilinkedinapp.models.Education;
import willy.individual.com.minilinkedinapp.utils.DateUtils;

public class MainActivity extends AppCompatActivity {

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
                startActivity(intent);
            }
        });

        LinearLayout educationsView = (LinearLayout) findViewById(R.id.educations_layout);
        for (Education education : educations) {
            View view = getEducationView(education);
            educationsView.addView(view);
        }
    }

    private View getEducationView(Education education) {
        View view = getLayoutInflater().inflate(R.layout.education_item, null);
        educationTv = (TextView) view.findViewById(R.id.education_info);
        courseTv    = (TextView) view.findViewById(R.id.education_courses);

        educationTv.setText(education.schoolName + " ("
                + DateUtils.dateToString(education.startDate) + " ~ "
                + DateUtils.dateToString(education.endDate) + ")");
        courseTv.setText(getEducationCourses(education));

        return view;
    }


    private void fakeData() {
        basicInfo = new BasicInfo();
        basicInfo.userName = "Willy Lu";
        basicInfo.email = "willylu@email.com";

        educations = new ArrayList<>();

        Education education1 = new Education();
        education1.schoolName = "CPP";
        education1.startDate = DateUtils.stringToDate("09/2012");
        education1.endDate = DateUtils.stringToDate("06/2013");
        education1.courses = new ArrayList<>();
        education1.courses.add("Angular 2");
        education1.courses.add("Node JS");
        education1.courses.add("Android");

        Education education2 = new Education();
        education2.schoolName = "CPP9999";
        education2.startDate = DateUtils.stringToDate("09/2012");
        education2.endDate = DateUtils.stringToDate("06/2013");
        education2.courses = new ArrayList<>();
        education2.courses.add("Angular 2");
        education2.courses.add("Node JS");
        education2.courses.add("Android");

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
