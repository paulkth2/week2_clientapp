package com.example.taehyungkim.week2;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class MyCommentAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> TitleArrayList;

    TextView name2_textView;


    public MyCommentAdapter(Context context, ArrayList<String> TitleArrayList) {
        this.context = context;
        this.TitleArrayList = TitleArrayList;
    }

    @Override
    public int getCount() {
        return this.TitleArrayList.size();
    }

    @Override
    public Object getItem(int position) {
        return this.TitleArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //if(convertView==null)
        convertView = LayoutInflater.from(context).inflate(R.layout.comment,null);
        name2_textView = (TextView)convertView.findViewById(R.id.comment);
       // }
        name2_textView.setText(TitleArrayList.get(position));
        return convertView;
    }

}
