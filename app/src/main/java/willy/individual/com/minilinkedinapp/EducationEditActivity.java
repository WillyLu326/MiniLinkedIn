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
import java.util.List;

import willy.individual.com.minilinkedinapp.models.Education;
import willy.individual.com.minilinkedinapp.utils.DateUtils;

/**
 * Created by zhenglu on 2/20/17.
 */

public class EducationEditActivity extends AppCompatActivity {

    public static final String KEY_EDUCATION = "education";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.education_edit);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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
        Education education = new Education();
        education.schoolName = ((EditText)findViewById(R.id.education_edit_school_name)).getText().toString();
        education.major = ((EditText)findViewById(R.id.education_edit_major)).getText().toString();
        education.startDate = DateUtils.stringToDate(((EditText)findViewById(R.id.education_edit_start_date)).getText().toString());
        education.endDate = DateUtils.stringToDate(((EditText)findViewById(R.id.education_edit_end_date)).getText().toString());
        education.courses = getCourses(((EditText)findViewById(R.id.education_edit_courses)).getText().toString());

        // Save Data
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EDUCATION, (Parcelable) education);
        setResult(Activity.RESULT_OK, resultIntent);

        finish();
    }

    private List<String> getCourses(String courses) {
        String[] strs = courses.split("\\n");
        List<String> result = new ArrayList<>();
        for (String str : strs) {
            result.add(str);
        }
        return result;
     }
}
