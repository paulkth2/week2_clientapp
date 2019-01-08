package com.example.taehyungkim.week2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddActivity extends AppCompatActivity {
    Intent intent;

    SimpleDraweeView profile;
    TextInputLayout name;
    TextInputLayout job;
    TextInputLayout country;
    TextInputLayout mobile;
    TextInputLayout email;
    TextInputLayout gender;
    TextInputLayout birthdate;
    TextInputLayout bloodgroup;
    TextInputLayout education;

    Button submitButton;

    String image_name = "blank image";

    String url = "http://socrip3.kaist.ac.kr:9180/api/users";
    String url2 = "http://socrip3.kaist.ac.kr:9180/images";

    ArrayList<String> imageIDs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_edit);

        intent = getIntent();

        profile = (SimpleDraweeView) findViewById(R.id.profile_edit);
        name = (TextInputLayout) findViewById(R.id.name_input);
        job = (TextInputLayout) findViewById(R.id.job_input);
        country = (TextInputLayout) findViewById(R.id.country_input);
        mobile = (TextInputLayout) findViewById(R.id.mobile_input);
        email = (TextInputLayout) findViewById(R.id.email_input);
        gender = (TextInputLayout) findViewById(R.id.gender_input);
        birthdate = (TextInputLayout) findViewById(R.id.birthdate_input);
        bloodgroup = (TextInputLayout) findViewById(R.id.bloodgroup_input);
        education = (TextInputLayout) findViewById(R.id.education_input);

        submitButton = (Button) findViewById(R.id.submit_button);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url2+"/"+MainActivity.login_email,
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

        Volley.newRequestQueue(AddActivity.this).add(jsonArrayRequest);

        profile.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(AddActivity.this, ProfileSelectActivity.class);
                myintent.putExtra("imageIDs", imageIDs);
                startActivityForResult(myintent, 1);
            }
        });

        submitButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name_string = name.getEditText().getText().toString();
                String job_string = job.getEditText().getText().toString();
                String country_string = country.getEditText().getText().toString();
                String mobile_string = mobile.getEditText().getText().toString();
                String email_string = email.getEditText().getText().toString();
                String gender_string = gender.getEditText().getText().toString();
                String birthdate_string = birthdate.getEditText().getText().toString();
                String bloodgroup_string = bloodgroup.getEditText().getText().toString();
                String education_string = education.getEditText().getText().toString();

                hideKeyboard();

                try {
                    //testView.setText("parsing tried");

                    JSONObject new_contact = new JSONObject();

                    //testView.setText("Array loading succeed.");

                    new_contact.put("image", image_name);
                    new_contact.put("name", name_string);
                    new_contact.put("job", job_string);
                    new_contact.put("country", country_string);
                    new_contact.put("phoneNumber", mobile_string);
                    new_contact.put("email", email_string);
                    new_contact.put("gender", gender_string);
                    new_contact.put("birthDate", birthdate_string);
                    new_contact.put("bloodGroup", bloodgroup_string);
                    new_contact.put("education", education_string);
                    new_contact.put("loginEmail", MainActivity.login_email);



                    JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, new_contact, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                            //testText.setText("succeed"+response.toString());
                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();

                            //testText.setText("fail");
                        }
                    });

                    Volley.newRequestQueue(AddActivity.this).add(jsonRequest);

                } catch (JSONException e){
                    //testView.setText("parsing error");
                    e.printStackTrace();
                }

                //setResult(AddActivity.RESULT_OK);
                finish();
            }
        }) ;

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }
    private void hideKeyboard() {
        View views = getCurrentFocus();
        if (views != null) {
            ((InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE)).
                    hideSoftInputFromWindow(views.getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case (1) : {
                if (resultCode == Activity.RESULT_OK) {
                    String returnImageName = data.getStringExtra("imageName");
                    if (returnImageName != "none"){
                        image_name = returnImageName;
                        Uri uri = Uri.parse("http://socrip3.kaist.ac.kr:9180/uploads/"+image_name);
                        profile.setImageURI(uri);
                    }
                }
                break;
            }
        }
    }
}
