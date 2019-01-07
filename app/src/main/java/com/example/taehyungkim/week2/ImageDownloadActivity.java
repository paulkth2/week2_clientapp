package com.example.taehyungkim.week2;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;

public class ImageDownloadActivity extends AppCompatActivity {
    SimpleDraweeView masaka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_download_activity);

        Uri uri = Uri.parse("http://socrip3.kaist.ac.kr:9180/uploads/Desert.jpg");
        masaka = (SimpleDraweeView) findViewById(R.id.my_image_view);
        masaka.setImageURI(uri);

    }
}
