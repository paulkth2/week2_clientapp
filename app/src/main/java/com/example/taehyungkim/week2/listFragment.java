package com.example.taehyungkim.week2;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import android.support.design.widget.Snackbar;
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

        sendButton = (ImageButton) view.findViewById(R.id.sendButton);
        recieveButton = (ImageButton) view.findViewById(R.id.recieveButton);
        testText = (TextView) view.findViewById(R.id.test_text);


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
                        testText.setText("error");
                    }
                }
        );


        Volley.newRequestQueue(getContext()).add(jsonArrayRequest);
        /*
        Log.d("list_item", String.valueOf(list_itemArrayList.size()));
        myListAdapter = new MyListAdapter(getActivity(),list_itemArrayList);
        listView.setAdapter(myListAdapter);

        */

        sendButton.setOnClickListener(new ImageButton.OnClickListener(){
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
                                //testText.setText("succeed"+response.toString());
                            }
                        }, new Response.ErrorListener() {
                            @Override
                            public void onErrorResponse(VolleyError error) {
                                error.printStackTrace();
                                //TODO: handle failure
                                //testText.setText("fail");
                            }
                        });

                        Volley.newRequestQueue(getContext()).add(jsonRequest);
                    }
                } catch (JSONException e){

                    e.printStackTrace();
                }
            }
        });

        recieveButton.setOnClickListener(new ImageButton.OnClickListener(){
            @Override
            public void onClick(View view) {
                //Log.d("list_item", String.valueOf(list_itemArrayList.size()));
                myListAdapter = new MyListAdapter(getActivity(),list_itemArrayList);
                listView.setAdapter(myListAdapter);
            }
        });




        return view;
    }

}
