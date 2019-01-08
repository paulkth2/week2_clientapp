package com.example.taehyungkim.week2;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.afollestad.materialdialogs.DialogAction;
import com.afollestad.materialdialogs.MaterialDialog;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.github.javiersantos.materialstyleddialogs.MaterialStyledDialog;
import com.rengwuxian.materialedittext.MaterialEditText;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class TitleActivity extends AppCompatActivity {
    ListView title_list;
    MyTripAdapter myTripAdapter;
    ArrayList<String> the_list = new ArrayList<>();
    ArrayList<contents> c_list = new ArrayList<>();

    String url = "http://socrip3.kaist.ac.kr:9180/trips";

    String country;
    String city;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.title_activity);

        title_list = (ListView) findViewById(R.id.title_listview);
        FloatingActionButton fab = findViewById(R.id.fab);

        the_list = getIntent().getStringArrayListExtra("the_list");

        myTripAdapter = new MyTripAdapter(TitleActivity.this, the_list);
        title_list.setAdapter(myTripAdapter);

        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url+"/"+getIntent().getStringExtra("country")+"/"+getIntent().getStringExtra("city"),
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
                                //Log.d("Object", "ha");
                                if(!jObject.getString("contents").isEmpty()) {
                                    String title = jObject.getString("title");
                                    String content = jObject.getString("contents");
                                    c_list.add(new contents(title, content));
                                    //Log.d("Title", jObject.getString("title"));
                                }
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

        title_list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent myIntent = new Intent(TitleActivity.this, ContentActivity.class);
                myIntent.putExtra("list", c_list);
                startActivity(myIntent);
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final View register_layout = LayoutInflater.from(TitleActivity.this)
                        .inflate(R.layout.register_layout, null);
                new MaterialStyledDialog.Builder(TitleActivity.this)
                        .setIcon(R.drawable.ic_home_black_24dp)
                        .setTitle("NEW CONTENT")
                        .setCustomView(register_layout)
                        .setNegativeText("CANCEL")
                        .onNegative(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveText("ADD")
                        .setDescription("Please fill all fields")
                        .onPositive(new MaterialDialog.SingleButtonCallback() {
                            @Override
                            public void onClick(@NonNull MaterialDialog dialog, @NonNull DialogAction which) {
                                MaterialEditText edit_title = register_layout.findViewById(R.id.title);
                                MaterialEditText edit_content = register_layout.findViewById(R.id.content);

                                if(TextUtils.isEmpty(edit_title.getText().toString())) {
                                    Toast.makeText(TitleActivity.this, "Title cannot be null or empty", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                if(TextUtils.isEmpty(edit_content.getText().toString())) {
                                    Toast.makeText(TitleActivity.this, "Content cannot be null or empty", Toast.LENGTH_SHORT).show();
                                    return;
                                }

                                // add new data into DB
                            }
                        }).show();

            }
        });


    }
}
