package com.gob.museumapp.activity;

import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.gob.museumapp.R;

import java.util.List;

public class MuseumActivity extends Activity implements View.OnClickListener{
    private Button museumBtn = null;
    private Button collectionBtn = null;
    private Button searchBtn = null;
    private ListView museumList = null;

    private String[] museums = {"Apple", "Banana", "Orange", "Watermelon", "Pear", "Grape", "Pineapple", "Strawberry", "Cherry", "Mongo"}; // ListView 测试数据

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏系统标题栏
        setContentView(R.layout.activity_museum);


        museumBtn = findViewById(R.id.museum_btn);
        collectionBtn = findViewById(R.id.collection_btn);
        searchBtn = findViewById(R.id.search_btn);
        museumList = findViewById(R.id.museum_list);

        museumBtn.setOnClickListener(this);
        collectionBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);

        // 怎么只能这样设置颜色
        museumBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        museumBtn.setTextColor(R.color.teal_200);
        collectionBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        searchBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));

        // 博物馆列表
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MuseumActivity.this, android.R.layout.simple_list_item_1, museums);
        museumList.setAdapter(adapter);

    }


    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId){
            case R.id.museum_btn:
                Intent jumpToMuseum = new Intent(MuseumActivity.this, MuseumActivity.class);
                startActivity(jumpToMuseum);
                break;
            case R.id.collection_btn:
                Intent jumpToCollection = new Intent(MuseumActivity.this, CollectionActivity.class);
                startActivity(jumpToCollection);
                break;
            case R.id.search_btn:
                Toast.makeText(MuseumActivity.this, "搜索 button clicked.", Toast.LENGTH_SHORT).show();

                break;
        }
    }
}