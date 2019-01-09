package com.example.taehyungkim.week2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    Intent intent;

    SimpleDraweeView profile;
    TextView name;
    TextView job;
    TextView country;
    TextView mobile;
    TextView email;
    TextView gender;
    TextView bloodgroup;
    TextView education;
    TextView birthdate;

    ImageView deleteButton;

    String url = "http://socrip3.kaist.ac.kr:9180/api/users/";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_show);

        intent = getIntent();

        profile = (SimpleDraweeView) findViewById(R.id.profile_show);
        name = (TextView) findViewById(R.id.name);
        country = (TextView) findViewById(R.id.location);
        job = (TextView) findViewById(R.id.designation);
        mobile = (TextView) findViewById(R.id.blood_group);
        email = (TextView) findViewById(R.id.education);
        gender = (TextView) findViewById(R.id.occupation);
        bloodgroup = (TextView) findViewById(R.id.mobileNumber);
        education = (TextView) findViewById(R.id.gender);
        birthdate = (TextView) findViewById(R.id.birthdate);


        Uri uri = Uri.parse("http://socrip3.kaist.ac.kr:9180/uploads/"+intent.getStringExtra("image"));
        profile.setImageURI(uri);
        /*
        int checkExistence = ProfileActivity.this.getResources().getIdentifier(intent.getStringExtra("image"), "drawable", ProfileActivity.this.getPackageName());
        if (checkExistence != 0){
            profile.setImageResource(checkExistence);}
        else{
            profile.setImageResource(R.mipmap.ic_launcher);
        }*/
        name.setText(intent.getStringExtra("name"));
        country.setText(intent.getStringExtra("country"));
        job.setText(intent.getStringExtra("job"));
        mobile.setText(intent.getStringExtra("phone number"));
        email.setText(intent.getStringExtra("email"));
        gender.setText(intent.getStringExtra("gender"));
        bloodgroup.setText(intent.getStringExtra("blood group"));
        education.setText(intent.getStringExtra("education"));
        birthdate.setText(intent.getStringExtra("birth date"));

//        deleteButton.setOnClickListener(new View.OnClickListener(){
//            @Override
//            public void onClick(View v) {
//                JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
//                        Request.Method.DELETE,
//                        url+"/"+intent.getStringExtra("name")+"/"+MainActivity.login_email,
//                        (String)null,
//                        new Response.Listener<JSONArray>() {
//                            @Override
//                            public void onResponse(JSONArray response) {
//                                // Do something with response
//                                //mTextView.setText(response.toString());
//                                Toast.makeText(getBaseContext(), intent.getStringExtra("name")+" Deleted", Toast.LENGTH_LONG).show();
//                                finish();
//                            }
//                        },
//                        new Response.ErrorListener() {
//                            @Override
//                            public void onErrorResponse(VolleyError error) {
//                                // Do something when error occurred
//                            }
//                        }
//
//
//                );
//            }
//        });
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

}
