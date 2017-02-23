package willy.individual.com.minilinkedinapp;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.EditText;

import willy.individual.com.minilinkedinapp.models.BasicInfo;

/**
 * Created by zhenglu on 2/22/17.
 */

public class BasicInfoActivity extends BasicEditActivity<BasicInfo>{

    public static final String KEY_BASICINFO = "basicinfo";

    @Override
    protected int getLayoutId() {
        return R.layout.basicinfo_edit;
    }

    @Override
    protected BasicInfo getIntentData() {
        return getIntent().getParcelableExtra(KEY_BASICINFO);
    }

    @Override
    protected void setupEditUI() {
        ((EditText) findViewById(R.id.basicinfo_edit_name)).setText(data.userName);
        ((EditText) findViewById(R.id.basicinfo_edit_email)).setText(data.email);

        // TODO Image Uploading
        findViewById(R.id.basicinfo_edit_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    protected void setupDefaultUI() {

    }

    @Override
    protected void saveAndExit() {
        if (data == null) {
            data = new BasicInfo();
        }

        data.userName =  ((EditText) findViewById(R.id.basicinfo_edit_name)).getText().toString();
        data.email = ((EditText) findViewById(R.id.basicinfo_edit_email)).getText().toString();

        // Save Data
        Intent resultIntent = new Intent();
        resultIntent.putExtra(KEY_BASICINFO, data);
        setResult(Activity.RESULT_OK, resultIntent);

        finish();
    }
}
