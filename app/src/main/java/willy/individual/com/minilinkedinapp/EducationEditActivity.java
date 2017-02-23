package willy.individual.com.minilinkedinapp;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import willy.individual.com.minilinkedinapp.models.Education;
import willy.individual.com.minilinkedinapp.utils.DateUtils;
import willy.individual.com.minilinkedinapp.utils.ToolUtils;

/**
 * Created by zhenglu on 2/20/17.
 */

public class EducationEditActivity extends BasicEditActivity<Education> {

    public static final String KEY_EDUCATION = "education";
    public static final String KEY_EDUCATION_DELETE = "education_delete";

    @Override
    protected int getLayoutId() {
        return R.layout.education_edit;
    }

    @Override
    protected Education getIntentData() {
        return getIntent().getParcelableExtra(KEY_EDUCATION);
    }

    @Override
    protected void setupEditUI() {
        ((EditText)findViewById(R.id.education_edit_school_name)).setText(data.schoolName);
        ((EditText)findViewById(R.id.education_edit_major)).setText(data.major);
        ((EditText)findViewById(R.id.education_edit_start_date)).setText(DateUtils.dateToString(data.startDate));
        ((EditText)findViewById(R.id.education_edit_end_date)).setText(DateUtils.dateToString(data.endDate));
        ((EditText)findViewById(R.id.education_edit_courses)).setText(ToolUtils.convertListToString(data.courses));

        findViewById(R.id.education_edit_delete_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(KEY_EDUCATION_DELETE, data.id);
                setResult(Activity.RESULT_OK, resultIntent);

                finish();
            }
        });
    }

    @Override
    protected void setupDefaultUI() {
        findViewById(R.id.education_edit_delete_btn).setVisibility(View.INVISIBLE);
    }

    protected void saveAndExit() {
        if (data == null) {
            data = new Education();
        }

        data.schoolName = ((EditText)findViewById(R.id.education_edit_school_name)).getText().toString();
        data.major = ((EditText)findViewById(R.id.education_edit_major)).getText().toString();
        data.startDate = DateUtils.stringToDate(((EditText)findViewById(R.id.education_edit_start_date)).getText().toString());
        data.endDate = DateUtils.stringToDate(((EditText)findViewById(R.id.education_edit_end_date)).getText().toString());
        data.courses = ToolUtils.convertStringToList(((EditText)findViewById(R.id.education_edit_courses)).getText().toString());

        // Save Data
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EDUCATION, data);
        setResult(Activity.RESULT_OK, resultIntent);

        finish();
    }

}
