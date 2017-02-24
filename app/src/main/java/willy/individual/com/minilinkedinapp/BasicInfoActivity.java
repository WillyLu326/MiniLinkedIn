package willy.individual.com.minilinkedinapp;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import willy.individual.com.minilinkedinapp.models.BasicInfo;
import willy.individual.com.minilinkedinapp.utils.ImageUtils;
import willy.individual.com.minilinkedinapp.utils.PermissionUtils;

/**
 * Created by zhenglu on 2/22/17.
 */

public class BasicInfoActivity extends BasicEditActivity<BasicInfo>{

    public static final String KEY_BASICINFO = "basicinfo";
    private static final int REQ_CODE_PICK_IMAGE = 100;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQ_CODE_PICK_IMAGE && resultCode == Activity.RESULT_OK) {
            Uri imageUri = data.getData();
            if (imageUri != null) {
                showImage(imageUri);
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == PermissionUtils.REQ_CODE_WRITE_EXTERNAL_STORAGE
                && grantResults.length > 0
                && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            pickImage();
        }
    }

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
                if (!PermissionUtils.checkPermission(BasicInfoActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    PermissionUtils.requestReadExternalStoragePermission(BasicInfoActivity.this);
                } else {
                    pickImage();
                }
            }
        });
    }

    @Override
    protected void setupDefaultUI() {
        findViewById(R.id.basicinfo_edit_profile).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!PermissionUtils.checkPermission(BasicInfoActivity.this,
                        Manifest.permission.READ_EXTERNAL_STORAGE)) {
                    PermissionUtils.requestReadExternalStoragePermission(BasicInfoActivity.this);
                } else {
                    pickImage();
                }
            }
        });
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

    private void showImage(@NonNull Uri imageUri) {
        ImageView imageView = (ImageView) findViewById(R.id.basicinfo_edit_profile);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);

        imageView.setTag(imageUri);
        ImageUtils.loadImage(this, imageUri, imageView);
    }

    private void pickImage() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "Select picture"),
                REQ_CODE_PICK_IMAGE);

    }
}
