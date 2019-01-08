package com.example.taehyungkim.week2;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class DemoFragment extends Fragment {
    int i;

    public DemoFragment() {
        // Required empty public constructor
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //just change the fragment_dashboard
        //with the fragment you want to inflate
        //like if the class is HomeFragment it should have R.layout.home_fragment
        //if it is DashboardFragment it should have R.layout.fragment_dashboard
        final HashMap<String, ArrayList<String>> europe = new HashMap<>();
        final ArrayList<Integer> selected = new ArrayList<Integer>();


        ArrayList<String> uk = new ArrayList<String>();
        ArrayList<String> france = new ArrayList<String>();
        ArrayList<String> spain = new ArrayList<String>();
        View view = inflater.inflate(R.layout.fragment_demo, container, false);
        final TextView text_country = view.findViewById(R.id.country);


        final String[] country =  {"uk", "france", "spain"};
        uk.add("london");
        uk.add("oxford");
        france.add("paris");
        france.add("nice");
        spain.add("madrid");
        spain.add("barcelona");
        europe.put("UK", uk);
        europe.put("FRANCE", france);
        europe.put("SPAIN", spain);


        text_country.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Choose a country");
                int checkedItem = 1;
                builder.setSingleChoiceItems(country, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        selected.add(which);
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        text_country.setText(country[selected.get(0)]);
//                        ArrayList<String> tmp = europe.get(country[selected.get(0)]);
//                        Log.d("ASFASDF", Integer.toString(tmp.size()));
//                        Intent intent = new Intent(getContext(), city.class);
//                        intent.putExtra("list", tmp);
//                        startActivity(intent);

                    }
                });
                builder.setNegativeButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        // city
//        final String[] cities = new String[tmp.size()];
//        text_city.setOnClickListener(new View.OnClickListener() {
//
//
//            @Override
//            public void onClick(View v) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
//                builder.setTitle("Choose a country");
//                int checkedItem = 1;
//                builder.setSingleChoiceItems(tmp.toArray(cities), checkedItem, new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//
//                    }
//                });
//                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        text_country.setText("checked");
//                    }
//                });
//                builder.setNegativeButton("Cancel", null);
//                AlertDialog dialog = builder.create();
//                dialog.show();
//            }
//        });

        return view;
    }

}



