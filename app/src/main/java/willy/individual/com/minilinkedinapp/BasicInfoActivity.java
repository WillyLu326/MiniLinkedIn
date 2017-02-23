package willy.individual.com.minilinkedinapp;

import willy.individual.com.minilinkedinapp.BasicEditActivity;
import willy.individual.com.minilinkedinapp.R;
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

    }

    @Override
    protected void saveAndExit() {

    }
}
