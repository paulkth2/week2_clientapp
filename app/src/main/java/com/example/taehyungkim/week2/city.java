package com.example.taehyungkim.week2;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class city extends AppCompatActivity {

    ArrayList<Integer> selected = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_city);
//        final TextView text_city = findViewById(R.id.city);
//        Intent intent = getIntent();
//        final ArrayList<String> list = (ArrayList<String>) intent.getSerializableExtra("list");
//        ///city
//        final String[] cities = new String[list.size()];
//        text_city.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(city.this);
//                builder.setTitle("Choose a country");
//                int checkedItem = 1;
//                builder.setSingleChoiceItems(list.toArray(cities), checkedItem, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        selected.add(which);
//                    }
//                });
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        text_city.setText(cities[selected.get(0)]);
//                    }
//                });
//                builder.setNegativeButton("Cancel", null);
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });
    }
}
