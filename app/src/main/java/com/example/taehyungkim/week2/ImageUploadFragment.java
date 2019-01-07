package com.example.taehyungkim.week2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class ImageUploadFragment extends Fragment{
    ImageButton uploadButton;
    ImageButton downloadButton;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.image_upload_fragment, null);

        uploadButton = (ImageButton) view.findViewById(R.id.uploadButton);
        downloadButton = (ImageButton) view.findViewById(R.id.downloadButton);

        uploadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ImageUploadActivity.class)) ;
            }
        });


        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), ImageDownloadActivity.class)) ;
            }
        });
        return view;
    }

}