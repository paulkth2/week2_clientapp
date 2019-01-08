package com.example.taehyungkim.week2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TitleActivity extends AppCompatActivity {
    ListView title_list;
    MyTripAdapter myTripAdapter;
    ArrayList<String> the_list = new ArrayList<>();

    String url = "http://socrip3.kaist.ac.kr:9180/trips";

    String country;
    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_activity);

        title_list = (ListView) findViewById(R.id.title_listview);

        the_list = getIntent().getStringArrayListExtra("the_list");

        myTripAdapter = new MyTripAdapter(TitleActivity.this, the_list);
        title_list.setAdapter(myTripAdapter);
    }
}
