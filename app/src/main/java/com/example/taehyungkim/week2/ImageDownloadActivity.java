package com.example.taehyungkim.week2;

import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;


import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.facebook.drawee.view.SimpleDraweeView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.net.URI;
import java.util.ArrayList;

public class ImageDownloadActivity extends AppCompatActivity {
    SimpleDraweeView masaka;
    GridView androidGridView;


    ArrayList<String> imageIDs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image_download_activity);

        //Uri uri = Uri.parse("http://socrip3.kaist.ac.kr:9180/uploads/Desert.jpg");
        //masaka = (SimpleDraweeView) findViewById(R.id.my_image_view);
        //masaka.setImageURI(uri);

        androidGridView = (GridView) findViewById(R.id.gridview_android_example);
        androidGridView.setAdapter(new ImageAdapterGridView(this));

        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {
                Toast.makeText(getBaseContext(), "Grid Item " + (position + 1) + " Selected", Toast.LENGTH_LONG).show();
            }
        });
        imageIDs = (ArrayList<String>) getIntent().getSerializableExtra("imageIDs");
        //Log.d("Is there Image?", imageIDs.get(0));
 /*

*/
    }

    public class ImageAdapterGridView extends BaseAdapter {
        private Context mContext;

        public ImageAdapterGridView(Context c) {
            mContext = c;
        }

        public int getCount() {
            return imageIDs.size();
        }

        public Object getItem(int position) {
            return null;
        }

        public long getItemId(int position) {
            return 0;
        }

        public View getView(int position, View convertView, ViewGroup parent) {
            SimpleDraweeView mImageView;

            if (convertView == null) {
                //mImageView = new ImageView(mContext);
                mImageView = new SimpleDraweeView(mContext);
                mImageView.setLayoutParams(new GridView.LayoutParams(500, 500));
                mImageView.setScaleType(SimpleDraweeView.ScaleType.CENTER_CROP);
                mImageView.setPadding(16, 16, 16, 16);
            } else {
                mImageView = (SimpleDraweeView) convertView;
            }

            Uri uri = Uri.parse("http://socrip3.kaist.ac.kr:9180/uploads/"+imageIDs.get(position));
            mImageView.setImageURI(uri);
            return mImageView;

            //mImageView.setImageResource(R.drawable.brian_may);
            //return mImageView;
        }
    }
}
