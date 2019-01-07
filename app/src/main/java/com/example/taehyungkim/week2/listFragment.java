package com.example.taehyungkim.week2;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import android.support.design.widget.Snackbar;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class listFragment extends Fragment {

    ListView listView;
    MyListAdapter myListAdapter;
    ArrayList<list_item> list_itemArrayList;

    ImageButton sendButton;
    ImageButton recieveButton;
    TextView testText;

    FloatingActionButton fab;
    FloatingActionButton fab1;
    FloatingActionButton fab2;
    FloatingActionButton fab3;
    CoordinatorLayout rootLayout;

    //Save the FAB's active status
    //false -> fab = close
    //true -> fab = open
    boolean FAB_Status = false;

    //Animations
    Animation show_fab_1;
    Animation hide_fab_1;
    Animation show_fab_2;
    Animation hide_fab_2;
    Animation show_fab_3;
    Animation hide_fab_3;


    String url = "http://socrip3.kaist.ac.kr:9180/api/users";


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment,null);

        listView = (ListView)view.findViewById(R.id.my_listview);

        list_itemArrayList = new ArrayList<list_item>();

        sendButton = (ImageButton) view.findViewById(R.id.fab_1);
        recieveButton = (ImageButton) view.findViewById(R.id.fab_2);



        //Floating Action Buttons
        fab = (FloatingActionButton) view.findViewById(R.id.fab);
        fab1 = (FloatingActionButton) view.findViewById(R.id.fab_1);
        fab2 = (FloatingActionButton) view.findViewById(R.id.fab_2);
        fab3 = (FloatingActionButton) view.findViewById(R.id.fab_3);

        //Animations
        show_fab_1 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab1_show);
        hide_fab_1 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab1_hide);
        show_fab_2 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab2_show);
        hide_fab_2 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab2_hide);
        show_fab_3 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab3_show);
        hide_fab_3 = AnimationUtils.loadAnimation(getActivity().getApplication(), R.anim.fab3_hide);

        String json_data = MyJSON.getData(getActivity().getApplicationContext());

            //testView.setText("parsing tried");

        // Initialize a new JsonArrayRequest instance
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(
                Request.Method.GET,
                url,
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

                                String image = jObject.getString("image");
                                String name = jObject.getString("name");
                                String phonenum = jObject.getString("phoneNumber");
                                String email = jObject.getString("email");
                                String job = jObject.getString("job");
                                String country = jObject.getString("country");
                                String gender = jObject.getString("gender");
                                String bloodgroup = jObject.getString("bloodGroup");
                                String education = jObject.getString("education");
                                String birthdate = jObject.getString("birthDate");
                                //Log.d("ObjectValues", image+name+phonenum);
                                int checkExistence = getContext().getResources().getIdentifier(image, "drawable", getContext().getPackageName());

                                if (checkExistence != 0) {
                                    list_itemArrayList.add(new list_item(checkExistence, name, phonenum, email, job, country, gender, bloodgroup, education, birthdate));
                                    //Log.d("addwithExistence", "Yeah");

                                } else {
                                    list_itemArrayList.add(new list_item(R.mipmap.ic_launcher, name, phonenum, email, job, country, gender, bloodgroup, education, birthdate));
                                    //Log.d("addwithExistence", "Nooo");
                                }
                                //Log.d("list_size", String.valueOf(list_itemArrayList.size()));
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


        /*
        Log.d("list_item", String.valueOf(list_itemArrayList.size()));
        myListAdapter = new MyListAdapter(getActivity(),list_itemArrayList);
        listView.setAdapter(myListAdapter);

        */
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (FAB_Status == false) {
                    //Display FAB menu
                    expandFAB();
                    FAB_Status = true;
                } else {
                    //Close FAB menu
                    hideFAB();
                    FAB_Status = false;
                }
            }
        });

        fab2.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                try {
                    //testView.setText("parsing tried");
                    JSONArray contact = new JSONArray(json_data);
                    //testView.setText("Array loading succeed.");
                    for (int i = 0; i < contact.length(); i++) {
                                JSONObject jObject = contact.getJSONObject(i);
                                JsonObjectRequest jsonRequest = new JsonObjectRequest(Request.Method.POST, url, jObject, new Response.Listener<JSONObject>() {
                                    @Override
                                    public void onResponse(JSONObject response) {
                                        //TODO: handle success

                                    }
                                }, new Response.ErrorListener() {
                                    @Override
                                    public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                //TODO: handle failure
                            }
                        });

                        Volley.newRequestQueue(getContext()).add(jsonRequest);
                    }
                } catch (JSONException e){

                    e.printStackTrace();
                }
            }
        });

        fab3.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Log.d("list_item", String.valueOf(list_itemArrayList.size()));

                myListAdapter = new MyListAdapter(getActivity(),list_itemArrayList);
                listView.setAdapter(myListAdapter);
            }
        });

        fab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getActivity(), AddActivity.class);
                startActivity(myIntent);
            }
        });



        return view;
    }


    private void expandFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        layoutParams.rightMargin += (int) (fab1.getWidth() * 1.7);
        layoutParams.bottomMargin += (int) (fab1.getHeight() * 0.25);
        fab1.setLayoutParams(layoutParams);
        fab1.startAnimation(show_fab_1);
        fab1.setClickable(true);

        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams2.rightMargin += (int) (fab2.getWidth() * 1.5);
        layoutParams2.bottomMargin += (int) (fab2.getHeight() * 1.5);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(show_fab_2);
        fab2.setClickable(true);

        //Floating Action Button 3
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
        layoutParams3.rightMargin += (int) (fab3.getWidth() * 0.25);
        layoutParams3.bottomMargin += (int) (fab3.getHeight() * 1.7);
        fab3.setLayoutParams(layoutParams3);
        fab3.startAnimation(show_fab_3);
        fab3.setClickable(true);
    }


    private void hideFAB() {

        //Floating Action Button 1
        FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) fab1.getLayoutParams();
        layoutParams.rightMargin -= (int) (fab1.getWidth() * 1.7);
        layoutParams.bottomMargin -= (int) (fab1.getHeight() * 0.25);
        fab1.setLayoutParams(layoutParams);
        fab1.startAnimation(hide_fab_1);
        fab1.setClickable(false);

        //Floating Action Button 2
        FrameLayout.LayoutParams layoutParams2 = (FrameLayout.LayoutParams) fab2.getLayoutParams();
        layoutParams2.rightMargin -= (int) (fab2.getWidth() * 1.5);
        layoutParams2.bottomMargin -= (int) (fab2.getHeight() * 1.5);
        fab2.setLayoutParams(layoutParams2);
        fab2.startAnimation(hide_fab_2);
        fab2.setClickable(false);

        //Floating Action Button 3
        FrameLayout.LayoutParams layoutParams3 = (FrameLayout.LayoutParams) fab3.getLayoutParams();
        layoutParams3.rightMargin -= (int) (fab3.getWidth() * 0.25);
        layoutParams3.bottomMargin -= (int) (fab3.getHeight() * 1.7);
        fab3.setLayoutParams(layoutParams3);
        fab3.startAnimation(hide_fab_3);
        fab3.setClickable(false);
    }
}
