package com.example.taehyungkim.week2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ContentActivity extends AppCompatActivity {
    TextView title;
    TextView content;
    EditText comment;
    ListView comments;

    ArrayList<contents> c_list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content);

        title = (TextView) findViewById(R.id.title_content);
        content = (TextView) findViewById(R.id.textView3);
        comment = (EditText) findViewById(R.id.comment_input);
        comments = (ListView) findViewById(R.id.comment_list);






    }
}
