package com.example.taehyungkim.week2;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;

import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;

public class ProfileSelectActivity extends AppCompatActivity {
    SimpleDraweeView masaka;
    GridView androidGridView;

    Intent resultIntent = new Intent();
    ArrayList<String> imageIDs = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_select);

        //Uri uri = Uri.parse("http://socrip3.kaist.ac.kr:9180/uploads/Desert.jpg");
        //masaka = (SimpleDraweeView) findViewById(R.id.my_image_view);
        //masaka.setImageURI(uri);

        androidGridView = (GridView) findViewById(R.id.select_gridview);
        androidGridView.setAdapter(new ProfileSelectActivity.ImageAdapterGridView(this));


        imageIDs = (ArrayList<String>) getIntent().getSerializableExtra("imageIDs");
        androidGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> parent,
                                    View v, int position, long id) {
                resultIntent.putExtra("imageName", imageIDs.get(position));
                setResult(Activity.RESULT_OK, resultIntent);
                finish();
            }
        });

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
            return (imageIDs == null) ? 0 : imageIDs.size();
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
                DisplayMetrics metrics = getResources().getDisplayMetrics();
                int screenWidth = metrics.widthPixels;
                mImageView = new SimpleDraweeView(mContext);
                mImageView.setLayoutParams(new GridView.LayoutParams(screenWidth/3, screenWidth/3));
                mImageView.setScaleType(SimpleDraweeView.ScaleType.CENTER_CROP);
                mImageView.setPadding(8, 8, 8, 8);
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
