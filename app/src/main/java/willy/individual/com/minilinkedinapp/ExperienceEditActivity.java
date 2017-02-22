package willy.individual.com.minilinkedinapp;

import willy.individual.com.minilinkedinapp.models.Experience;

/**
 * Created by zhenglu on 2/21/17.
 */

public class ExperienceEditActivity extends BasicEditActivity<Experience>{

    @Override
    protected int getLayoutId() {
        return 0;
    }

    @Override
    protected Experience getIntentData() {
        return null;
    }

    @Override
    protected void setupEditUI() {

    }

    @Override
    protected void saveAndExit() {

    }
}
