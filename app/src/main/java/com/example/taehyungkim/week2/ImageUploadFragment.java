package com.example.taehyungkim.week2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ImageUploadFragment extends Fragment{
    ImageButton uploadButton;
    ImageButton downloadButton;
    String url = "http://socrip3.kaist.ac.kr:9180/images";
    ArrayList<String> imageIDs = new ArrayList<>();



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
                Intent myintent = new Intent(getActivity(), ImageUploadActivity.class);
                startActivityForResult(myintent, 1);
            }
        });

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
                (String)null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        // Do something with response
                        //mTextView.setText(response.toString());

                        // Process the JSON
                        try {
                            JSONArray contact = response;

                            for (int i = 0; i < contact.length(); i++) {
                                JSONObject jObject = contact.getJSONObject(i);

                                String imageName = jObject.getString("imageName");
                                imageIDs.add(imageName);
                                //Log.d("ImageID", imageName);
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Do something when error occurred
                    }
                }


        );

        Volley.newRequestQueue(getContext()).add(jsonArrayRequest);

        downloadButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), ImageDownloadActivity.class);
                myIntent.putExtra("imageIDs", imageIDs);
                startActivity(myIntent) ;
            }
        });
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (1) : {
                if (resultCode == Activity.RESULT_OK) {
                    String returnImageName = data.getStringExtra("imageName");
                    if (returnImageName != "none"){
                        imageIDs.add(returnImageName);
                    }
                }
                break;
            }
        }
    }

}