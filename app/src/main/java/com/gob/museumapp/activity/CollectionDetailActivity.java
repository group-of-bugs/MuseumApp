package com.gob.museumapp.activity;


import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.gob.museumapp.R;
import com.gob.museumapp.util.LoadImage;

public class CollectionDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏系统标题栏
        setContentView(R.layout.activity_collection_detail);
        Bundle b= this.getIntent().getBundleExtra("col_detail");
        Log.d("tag",b.getString("col_Img"));
        ImageView collectionImage = findViewById(R.id.collection_img);
        TextView collectionName = findViewById(R.id.collection_name);
        TextView collectionTitle = findViewById(R.id.collection_title);
        LoadImage loader = new LoadImage(collectionImage);
        collectionName.setText(b.getString("col_Name"));
        collectionTitle.setText("藏品详情");
        loader.setBitmap(b.getString("col_Img"));

    }
}