package com.example.taehyungkim.week2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class ContentActivity extends AppCompatActivity {
    TextView title;
    TextView content;
    EditText comment;
    ListView comments;
    Button addButton;

    MyCommentAdapter myCommentAdapter;

    String url = "http://socrip3.kaist.ac.kr:9180/trips";

    ArrayList<contents> c_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        title = (TextView) findViewById(R.id.title_content);
        content = (TextView) findViewById(R.id.textView3);
        comment = (EditText) findViewById(R.id.comment_input);
        comments = (ListView) findViewById(R.id.comment_list);
        addButton = (Button) findViewById(R.id.button3);

        comment.bringToFront();
        String ctitle = getIntent().getStringExtra("title");
        String ccontent = getIntent().getStringExtra("content");
        ArrayList<String> ccomment = getIntent().getStringArrayListExtra("comment");

        title.setText(ctitle);
        content.setText(ccontent);

        myCommentAdapter = new MyCommentAdapter(ContentActivity.this, ccomment);
        comments.setAdapter(myCommentAdapter);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String newcomment = comment.getText().toString();
                comment.setText("");

                ccomment.add(newcomment);
                myCommentAdapter = new MyCommentAdapter(ContentActivity.this, ccomment);
                comments.setAdapter(myCommentAdapter);

                try {
                    // add new data into DB
                    JSONObject jObject = new JSONObject();

                    jObject.put("country", getIntent().getStringExtra("country"));
                    jObject.put("city", getIntent().getStringExtra("city"));
                    jObject.put("title", ctitle);
                    jObject.put("contents", "");
                    jObject.put("comments", newcomment);
                    jObject.put("loginEmail", MainActivity.login_email);

                    JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jObject, new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(JSONObject response) {

                        }
                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            error.printStackTrace();
                        }
                    });

                    Volley.newRequestQueue(ContentActivity.this).add(jsonRequest);
                } catch (JSONException e){
                    e.printStackTrace();
                }
            }
        });

    }
}
