package com.gob.museumapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;

import com.gob.museumapp.R;

public class SearchActivity extends Activity implements View.OnClickListener{

    private Button museumBtn = null;
    private Button collectionBtn = null;
    private Button searchBtn = null;
    private Button mainPageBtn = null;
    private Button rankListBtn = null;
    private Button myPageBtn = null;

    @SuppressLint("ResourceType")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏系统标题栏
        setContentView(R.layout.activity_search);

        museumBtn = findViewById(R.id.museum_btn);
        collectionBtn = findViewById(R.id.collection_btn);
        searchBtn = findViewById(R.id.search_btn);
        mainPageBtn = findViewById(R.id.main_page_btn);
        rankListBtn = findViewById(R.id.rank_list_btn);
        myPageBtn = findViewById(R.id.my_page_btn);

        museumBtn.setOnClickListener(this);
        collectionBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        mainPageBtn.setOnClickListener(this);
        rankListBtn.setOnClickListener(this);
        myPageBtn.setOnClickListener(this);

        museumBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        collectionBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        searchBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        searchBtn.setTextColor(R.color.teal_200);

    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId){
            case R.id.museum_btn:
            case R.id.main_page_btn:
                Intent jumpToMuseum = new Intent(SearchActivity.this, MuseumActivity.class);
                startActivity(jumpToMuseum);
                break;
            case R.id.collection_btn:
                Intent jumpToCollection = new Intent(SearchActivity.this, CollectionActivity.class);
                startActivity(jumpToCollection);
                break;
            case R.id.search_btn:
                Intent jumpToSearch = new Intent(SearchActivity.this, SearchActivity.class);
                startActivity(jumpToSearch);
                break;
            case R.id.rank_list_btn:
                Intent jumpToRankList = new Intent(SearchActivity.this, RankListActivity.class);
                startActivity(jumpToRankList);
                break;
            case R.id.my_page_btn:
                Intent jumpToMyPage = new Intent(SearchActivity.this, MyActivity.class);
                startActivity(jumpToMyPage);
                break;
        }
    }
}