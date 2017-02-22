package willy.individual.com.minilinkedinapp;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import willy.individual.com.minilinkedinapp.models.BasicInfo;
import willy.individual.com.minilinkedinapp.models.Education;
import willy.individual.com.minilinkedinapp.utils.DateUtils;
import willy.individual.com.minilinkedinapp.utils.ModelUtils;

public class MainActivity extends AppCompatActivity {

    private static final int REQ_CODE_EDUCATION = 100;

    private static final String SP_KEY_EDUCATION = "sp_education";

    private BasicInfo basicInfo;
    private List<Education> educations;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        loadData();
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

        ModelUtils.saveModel(this, SP_KEY_EDUCATION, educations);
    }

    private void setupUI() {
        setupBasicInfo();
        setupEducations();
    }

    private void setupBasicInfo() {
        ((TextView) findViewById(R.id.profile_name)).setText(basicInfo.userName);
        ((TextView) findViewById(R.id.profile_email)).setText(basicInfo.email);
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

        ((TextView) view.findViewById(R.id.education_info)).setText(education.schoolName + " " + education.major + " ("
                + DateUtils.dateToString(education.startDate) + " ~ "
                + DateUtils.dateToString(education.endDate) + ")");

        ((TextView) view.findViewById(R.id.education_courses)).setText(getEducationCourses(education));

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

    private void loadData() {
        basicInfo = new BasicInfo();
        basicInfo.userName = "Willy Lu";
        basicInfo.email = "willylu@email.com";

        List<Education> savedEducation = ModelUtils.readModel(this, SP_KEY_EDUCATION, new TypeToken<List<Education>>(){});
        educations = savedEducation == null ? new ArrayList<Education>() : savedEducation;
    }

}
