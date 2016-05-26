package com.tallerii.match;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.media.Image;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.tallerii.match.core.ImageManager;
import com.tallerii.match.core.SystemData;
import com.tallerii.match.core.UserProfile;

public class SelectImageActivity extends AppCompatActivity {

    private boolean photoTaked = false;
    Bitmap imageBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_image);
    }

    public void onTakePhotoButtonClick(View v){
        dispatchTakePictureIntent();
    }

    public void onAcceptButton(View v){
        Intent returnIntent = new Intent();
        String photo = ImageManager.codeToBase64(imageBitmap);
        SystemData.getInstance().getUserProfile().setPhoto(photo);
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
    }

    static final int REQUEST_IMAGE_CAPTURE = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == RESULT_OK) {
            Bundle extras = data.getExtras();
            imageBitmap = (Bitmap) extras.get("data");

            ImageView imageView = (ImageView) findViewById(R.id.asi_iv_takeImage);
            imageView.setImageBitmap(imageBitmap);
            photoTaked = true;
        }
    }

}
