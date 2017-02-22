package willy.individual.com.minilinkedinapp;

import android.app.Activity;
import android.content.Intent;
import android.widget.EditText;

import willy.individual.com.minilinkedinapp.models.Project;
import willy.individual.com.minilinkedinapp.utils.DateUtils;
import willy.individual.com.minilinkedinapp.utils.ToolUtils;

/**
 * Created by zhenglu on 2/22/17.
 */

public class ProjectEditActivity extends BasicEditActivity<Project> {

    public static final String KEY_PROJECT = "project";

    @Override
    protected int getLayoutId() {
        return R.layout.project_edit;
    }

    @Override
    protected Project getIntentData() {
        return getIntent().getParcelableExtra(KEY_PROJECT);
    }

    @Override
    protected void setupEditUI() {
        ((EditText) findViewById(R.id.project_edit_project_name)).setText(data.projectName);
        ((EditText) findViewById(R.id.project_edit_project_title)).setText(data.summary);
        ((EditText) findViewById(R.id.project_edit_start_date)).setText(DateUtils.dateToString(data.startDate));
        ((EditText) findViewById(R.id.project_edit_end_date)).setText(DateUtils.dateToString(data.endDate));
        ((EditText) findViewById(R.id.project_edit_project_summary)).setText(ToolUtils.convertListToString(data.tools));
    }

    @Override
    protected void saveAndExit() {
        if (data == null) {
            data = new Project();
        }

        data.projectName = ((EditText) findViewById(R.id.project_edit_project_name)).getText().toString();
        data.summary = ((EditText) findViewById(R.id.project_edit_project_title)).getText().toString();
        data.startDate = DateUtils.stringToDate(((EditText) findViewById(R.id.project_edit_start_date)).getText().toString());
        data.endDate = DateUtils.stringToDate(((EditText) findViewById(R.id.project_edit_end_date)).getText().toString());
        data.tools = ToolUtils.convertStringToList(((EditText) findViewById(R.id.project_edit_project_summary)).getText().toString());

        // Save Data
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_PROJECT, data);
        setResult(Activity.RESULT_OK, resultIntent);

        finish();
    }
}
