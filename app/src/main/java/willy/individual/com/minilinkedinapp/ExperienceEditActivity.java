package willy.individual.com.minilinkedinapp;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import willy.individual.com.minilinkedinapp.models.Experience;
import willy.individual.com.minilinkedinapp.utils.DateUtils;
import willy.individual.com.minilinkedinapp.utils.ToolUtils;

/**
 * Created by zhenglu on 2/21/17.
 */

public class ExperienceEditActivity extends BasicEditActivity<Experience>{

    public static final String KEY_EXPERIENCE = "experience";
    public static final String KEY_EXPERIENCE_DELETE = "experience_delete";

    @Override
    protected int getLayoutId() {
        return R.layout.experience_edit;
    }

    @Override
    protected Experience getIntentData() {
        return getIntent().getParcelableExtra(KEY_EXPERIENCE);
    }

    @Override
    protected void setupEditUI() {
        ((EditText) findViewById(R.id.experience_edit_company_name)).setText(data.companyName);
        ((EditText) findViewById(R.id.experience_edit_job_title)).setText(data.jobTitle);
        ((EditText) findViewById(R.id.experience_edit_start_date)).setText(DateUtils.dateToString(data.startDate));
        ((EditText) findViewById(R.id.experience_edit_end_date)).setText(DateUtils.dateToString(data.endDate));
        ((EditText) findViewById(R.id.experience_edit_project_summary)).setText(ToolUtils.convertListToString(data.projects));

        findViewById(R.id.experience_edit_delete_btn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent resultIntent = new Intent();
                resultIntent.putExtra(KEY_EXPERIENCE_DELETE, data.id);
                setResult(Activity.RESULT_OK, resultIntent);

                finish();
            }
        });
    }

    @Override
    protected void setupDefaultUI() {
        findViewById(R.id.experience_edit_delete_btn).setVisibility(View.INVISIBLE);
    }

    @Override
    protected void saveAndExit() {
        if (data == null) {
            data = new Experience();
        }

        data.companyName = ((EditText) findViewById(R.id.experience_edit_company_name)).getText().toString();
        data.jobTitle = ((EditText) findViewById(R.id.experience_edit_job_title)).getText().toString();
        data.startDate = DateUtils.stringToDate(((EditText) findViewById(R.id.experience_edit_start_date)).getText().toString());
        data.endDate = DateUtils.stringToDate(((EditText) findViewById(R.id.experience_edit_end_date)).getText().toString());
        data.projects = ToolUtils.convertStringToList(((EditText) findViewById(R.id.experience_edit_project_summary)).getText().toString());

        // Save Data
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_EXPERIENCE, data);
        setResult(Activity.RESULT_OK, resultIntent);

        finish();
    }
}
