package com.gob.museumapp.activity;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.gob.museumapp.R;
import com.gob.museumapp.util.LoadImage;

public class CollectionDetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏系统标题栏
        setContentView(R.layout.activity_collection_detail);
        Bundle b= this.getIntent().getBundleExtra("col_detail");

        TextView collectionTitle = findViewById(R.id.collection_title);
        collectionTitle.setText("藏品详情");

        ImageView collectionImage = findViewById(R.id.collection_img);
        LoadImage loader = new LoadImage(collectionImage);
        loader.setBitmap(b.getString("col_Img"));

        TextView collectionName = findViewById(R.id.collection_name);
        collectionName.setText(b.getString("col_Name"));

        TextView collectionInfo = findViewById(R.id.collection_info);
        collectionInfo.setText(b.getString("col_Info"));







    }
}