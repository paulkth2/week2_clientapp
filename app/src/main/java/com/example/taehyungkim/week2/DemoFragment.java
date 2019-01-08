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
    public static ArrayList<String> cities;
    public static int a = 0;
    public static int country_n = 0;
    public static int city_n = 0;
    public static String selected_city;
    public static String selected_country;

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
//        final ArrayList<Integer> selected = new ArrayList<Integer>();


        ArrayList<String> uk = new ArrayList<String>();
        ArrayList<String> france = new ArrayList<String>();
        ArrayList<String> spain = new ArrayList<String>();
        View view = inflater.inflate(R.layout.fragment_demo, container, false);
        final TextView text_country = view.findViewById(R.id.country);
        final TextView text_city = view.findViewById(R.id.city);


        final String[] country =  {"uk", "france", "spain"};
        uk.add("london");
        uk.add("oxford");
        france.add("paris");
        france.add("nice");
        spain.add("madrid");
        spain.add("barcelona");
        europe.put("uk", uk);
        europe.put("france", france);
        europe.put("spain", spain);


        text_country.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Choose a country");
                int checkedItem = 1;
                builder.setSingleChoiceItems(country, checkedItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        country_n = which;
                    }
                });
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        selected_country = country[country_n];
                        text_country.setText(selected_country);
                        text_city.setText("City");
                        cities = europe.get(country[country_n]);
                        a = 1;
//                        Log.d("ASFASDF", Integer.toString(tmp.size()));
//                        Intent intent = new Intent(getContext(), city.class);
//                        intent.putExtra("list", cities);
//                        startActivity(intent);

                    }
                });
                builder.setNegativeButton("Cancel", null);
                AlertDialog dialog = builder.create();
                dialog.show();

            }
        });

        // city

        text_city.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Choose a city");
                int checkedItem = 1;
                if(a == 1) {
                    final String[] tmp = new String[cities.size()];
                    builder.setSingleChoiceItems(cities.toArray(tmp), checkedItem, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            city_n = which;
                        }
                    });
                    builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            selected_city = tmp[city_n];
                            text_city.setText(selected_city);
                        }
                    });
                    builder.setNegativeButton("Cancel", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
            }
        });


        return view;
    }

}



