package com.gob.museumapp.activity;

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

import androidx.core.content.ContextCompat;

import com.gob.museumapp.R;

public class CollectionActivity extends Activity implements View.OnClickListener {
    private Button museumBtn = null;
    private Button collectionBtn = null;
    private Button searchBtn = null;
    private ListView collectionList = null;

    private String[] collections = {"Algorithm", "a考研", "dataMining", "gittest", "integrated-design", "Java程序设计", "LaTeX", "MachineLearning", "OOP", "毕设", "并行分布式计算", "测评", "大数据技术基础", "电路与电子技术", "非关系型数据库", "复试"};

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏系统标题栏
        setContentView(R.layout.activity_collection);


        museumBtn = findViewById(R.id.museum_btn);
        collectionBtn = findViewById(R.id.collection_btn);
        searchBtn = findViewById(R.id.search_btn);
        collectionList = findViewById(R.id.collection_list);

        museumBtn.setOnClickListener(this);
        collectionBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);

        // 设置颜色
        museumBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        collectionBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        collectionBtn.setTextColor(R.color.teal_200);
        searchBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));

        // 藏品列表
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(CollectionActivity.this, android.R.layout.simple_list_item_1, collections);
        collectionList.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId){
            case R.id.museum_btn:
                Intent jumpToMuseum = new Intent(CollectionActivity.this, MuseumActivity.class);
                startActivity(jumpToMuseum);
                break;
            case R.id.collection_btn:
                Intent jumpToCollection = new Intent(CollectionActivity.this, CollectionActivity.class);
                startActivity(jumpToCollection);
                break;
            case R.id.search_btn:
                Toast.makeText(CollectionActivity.this, "搜索 button clicked.", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
