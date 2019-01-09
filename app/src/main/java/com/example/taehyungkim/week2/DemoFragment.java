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
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class DemoFragment extends Fragment {
    int i;
    public static ArrayList<String> cities;
    public static int a = 0;
    public static int country_n = 0;
    public static int city_n = 0;
    public static String selected_city ="";
    public static String selected_country ="";

    String url = "http://socrip3.kaist.ac.kr:9180/trips";

    Button start_button;

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
        ArrayList<String> germany = new ArrayList<String>();
        ArrayList<String> italy = new ArrayList<String>();
        ArrayList<String> the_list = new ArrayList<>();
        View view = inflater.inflate(R.layout.fragment_demo, container, false);
//        final TextView text_country = view.findViewById(R.id.country);
//        final TextView text_city = view.findViewById(R.id.city);
        final Button text_country = view.findViewById(R.id.country);
        final Button text_city = view.findViewById(R.id.city);

        start_button = (Button) view.findViewById(R.id.button2);

        final String[] country =  {"UK", "France", "Spain", "Germany", "Italy"};
        uk.add("London"); uk.add("oxford"); uk.add("Edinburgh"); uk.add("Birmingham");
        france.add("Paris"); france.add("Nice"); france.add("Marseille"); france.add("Lyon");
        spain.add("Madrid"); spain.add("Barcelona"); spain.add("Seville"); spain.add("Valencia");
        germany.add("Berlin"); germany.add("Munchen"); germany.add("Frankfurt"); germany.add("Koln");
        italy.add("Rome"); italy.add("Milan"); italy.add("Venice"); italy.add("Florence");
        europe.put("UK", uk); europe.put("Germany", germany);
        europe.put("France", france); europe.put("Italy", italy);
        europe.put("Spain", spain);


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
                            the_list.clear();
                            JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                                    Request.Method.GET,
                                    url+"/"+selected_country+"/"+selected_city,
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
                                                        the_list.add(jObject.getString("title"));
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

                            Volley.newRequestQueue(getContext()).add(jsonArrayRequest);
                        }
                    });
                    builder.setNegativeButton("Cancel", null);
                    AlertDialog dialog = builder.create();
                    dialog.show();

                }
            }
        });

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), TitleActivity.class);
                myIntent.putExtra("the_list", the_list);
                myIntent.putExtra("country", selected_country);
                myIntent.putExtra("city", selected_city);
                startActivity(myIntent) ;
            }
        });
        return view;
    }

}



