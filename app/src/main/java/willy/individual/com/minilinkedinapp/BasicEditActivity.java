package willy.individual.com.minilinkedinapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

/**
 * Created by zhenglu on 2/21/17.
 */

public abstract class BasicEditActivity<T> extends AppCompatActivity {

    protected T data;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        data = getIntentData();

        if (data != null) {
            setupEditUI();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_edit, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
            case R.id.ic_save:
                saveAndExit();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    protected abstract int getLayoutId();

    protected abstract T getIntentData();

    protected abstract void setupEditUI();

    protected abstract void saveAndExit();
}
