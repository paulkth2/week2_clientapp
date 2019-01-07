package com.example.taehyungkim.week2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ProfileActivity extends AppCompatActivity {
    Intent intent;

    CircleImageView profile;
    TextView name;
    TextView job;
    TextView country;
    TextView mobile;
    TextView email;
    TextView gender;
    TextView bloodgroup;
    TextView education;
    TextView birthdate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_show);

        intent = getIntent();

        profile = (CircleImageView) findViewById(R.id.profile_show);
        name = (TextView) findViewById(R.id.name);
        country = (TextView) findViewById(R.id.location);
        job = (TextView) findViewById(R.id.designation);
        mobile = (TextView) findViewById(R.id.blood_group);
        email = (TextView) findViewById(R.id.education);
        gender = (TextView) findViewById(R.id.occupation);
        bloodgroup = (TextView) findViewById(R.id.mobileNumber);
        education = (TextView) findViewById(R.id.gender);
        birthdate = (TextView) findViewById(R.id.birthdate);

        int checkExistence = ProfileActivity.this.getResources().getIdentifier(intent.getStringExtra("image"), "drawable", ProfileActivity.this.getPackageName());
        if (checkExistence != 0){
            profile.setImageResource(checkExistence);}
        else{
            profile.setImageResource(R.mipmap.ic_launcher);
        }
        name.setText(intent.getStringExtra("name"));
        country.setText(intent.getStringExtra("country"));
        job.setText(intent.getStringExtra("job"));
        mobile.setText(intent.getStringExtra("phone number"));
        email.setText(intent.getStringExtra("email"));
        gender.setText(intent.getStringExtra("gender"));
        bloodgroup.setText(intent.getStringExtra("blood group"));
        education.setText(intent.getStringExtra("education"));
        birthdate.setText(intent.getStringExtra("birth date"));

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
