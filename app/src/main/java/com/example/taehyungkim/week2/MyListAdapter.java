package com.example.taehyungkim.week2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyListAdapter extends BaseAdapter {

    Context context;
    ArrayList<list_item> list_itemArrayList;

    TextView name_textView;
    TextView phonenum_textView;
    TextView email_textView;
    ImageView profile_imageView;

    public MyListAdapter(Context context, ArrayList<list_item> list_itemArrayList) {
        this.context = context;
        this.list_itemArrayList = list_itemArrayList;
    }

    @Override
    public int getCount() {
        return this.list_itemArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.list_itemArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //if(convertView==null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item,null);
            name_textView = (TextView)convertView.findViewById(R.id.name_textview);
            phonenum_textView = (TextView)convertView.findViewById(R.id.phonenum_textview);
            email_textView = (TextView)convertView.findViewById(R.id.email_textview);
            profile_imageView = (ImageView)convertView.findViewById(R.id.profile_imageview);
       // }
        name_textView.setText(list_itemArrayList.get(position).getName());
        phonenum_textView.setText(list_itemArrayList.get(position).getPhonenum());
        email_textView.setText(list_itemArrayList.get(position).getEmail());
        int checkExistence = context.getResources().getIdentifier(list_itemArrayList.get(position).getProfile_image(), "drawable", context.getPackageName());
        if (checkExistence != 0){
            profile_imageView.setImageResource(checkExistence);}
        else{
            profile_imageView.setImageResource(R.mipmap.ic_launcher);
        }

        return convertView;
    }

}
