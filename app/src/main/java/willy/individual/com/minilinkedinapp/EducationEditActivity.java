package willy.individual.com.minilinkedinapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import willy.individual.com.minilinkedinapp.models.Education;
import willy.individual.com.minilinkedinapp.utils.DateUtils;

/**
 * Created by zhenglu on 2/20/17.
 */

public class EducationEditActivity extends AppCompatActivity {

    public static final String KEY_EDUCATION = "education";

    private Education education;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        education = getIntent().getParcelableExtra(KEY_EDUCATION);

        if (education != null) {
            ((EditText)findViewById(R.id.education_edit_school_name)).setText(education.schoolName);
            ((EditText)findViewById(R.id.education_edit_major)).setText(education.major);
            ((EditText)findViewById(R.id.education_edit_start_date)).setText(DateUtils.dateToString(education.startDate));
            ((EditText)findViewById(R.id.education_edit_end_date)).setText(DateUtils.dateToString(education.endDate));
            ((EditText)findViewById(R.id.education_edit_courses)).setText(getCoursesString(education.courses));
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.ic_save:
                saveAndExit();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void saveAndExit() {
        if (education == null) {
            education = new Education();
        }

        education.schoolName = ((EditText)findViewById(R.id.education_edit_school_name)).getText().toString();
        education.major = ((EditText)findViewById(R.id.education_edit_major)).getText().toString();
        education.startDate = DateUtils.stringToDate(((EditText)findViewById(R.id.education_edit_start_date)).getText().toString());
        education.endDate = DateUtils.stringToDate(((EditText)findViewById(R.id.education_edit_end_date)).getText().toString());
        education.courses = getCoursesList(((EditText)findViewById(R.id.education_edit_courses)).getText().toString());

        // Save Data
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EDUCATION, education);
        setResult(Activity.RESULT_OK, resultIntent);

        finish();
    }

    private List<String> getCoursesList(String courses) {
        String[] strs = courses.split("\\n");
        List<String> result = new ArrayList<>();
        for (String str : strs) {
            result.add(str);
        }
        return result;
     }

    private String getCoursesString(List<String> courses) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < courses.size(); ++i) {
            if (i == courses.size() - 1) {
                sb.append(courses.get(i));
            } else {
                sb.append(courses.get(i) + "\n");
            }
        }
        return sb.toString();
    }
}
