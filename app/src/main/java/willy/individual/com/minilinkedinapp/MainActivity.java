package willy.individual.com.minilinkedinapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;

import willy.individual.com.minilinkedinapp.models.BasicInfo;
import willy.individual.com.minilinkedinapp.models.Education;
import willy.individual.com.minilinkedinapp.utils.DateUtils;

public class MainActivity extends AppCompatActivity {

    private TextView usernameTv;
    private TextView emailTv;
    private TextView educationTv;
    private TextView courseTv;

    private BasicInfo basicInfo;
    private Education education;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fakeData();
        setupUI();
    }

    private void setupUI() {
        setupBasicInfo();
        setupEducation();
    }

    private void setupBasicInfo() {
        usernameTv  = (TextView) findViewById(R.id.profile_name);
        emailTv     = (TextView) findViewById(R.id.profile_email);

        usernameTv.setText(basicInfo.userName);
        emailTv.setText(basicInfo.email);
    }

    private void setupEducation() {
        educationTv = (TextView) findViewById(R.id.education_info);
        courseTv    = (TextView) findViewById(R.id.education_courses);

        educationTv.setText(education.schoolName + " ("
                + DateUtils.dateToString(education.startDate) + " ~ "
                + DateUtils.dateToString(education.endDate) + ")");
        courseTv.setText(getEducationCourses());
    }

    private void fakeData() {
        basicInfo = new BasicInfo();
        basicInfo.userName = "Willy Lu";
        basicInfo.email = "willylu@email.com";

        education = new Education();
        education.schoolName = "CPP";
        education.startDate = DateUtils.stringToDate("09/2012");
        education.endDate = DateUtils.stringToDate("06/2013");
        education.courses = new ArrayList<>();
        education.courses.add("Angular 2");
        education.courses.add("Node JS");
        education.courses.add("Android");
    }

    private String getEducationCourses() {
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
