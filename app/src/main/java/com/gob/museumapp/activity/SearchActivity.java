package com.gob.museumapp.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.gob.museumapp.R;
import com.gob.museumapp.db.DBHelper;
import com.gob.museumapp.model.Collection;
import com.gob.museumapp.model.CollectionAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SearchActivity extends Activity implements View.OnClickListener{

    private Button museumBtn = null;
    private Button collectionBtn = null;
    private Button searchBtn = null;
    private Button mainPageBtn = null;
    private Button rankListBtn = null;
    private Button myPageBtn = null;
    private EditText searchTextView = null;
    private ListView searchListView = null;
    private Button searchConfirm = null;

    private List<Collection> collections = new ArrayList<>();

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
        searchListView = findViewById(R.id.search_list);
        searchConfirm = findViewById(R.id.search_confirm);
        searchTextView = findViewById(R.id.search_text);

        museumBtn.setOnClickListener(this);
        collectionBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        mainPageBtn.setOnClickListener(this);
        rankListBtn.setOnClickListener(this);
        myPageBtn.setOnClickListener(this);
        searchConfirm.setOnClickListener(this);

        museumBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        collectionBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        searchBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        searchBtn.setTextColor(R.color.teal_200);

    }

    @Override
    protected void onResume() {
        super.onResume();

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
            case R.id.search_confirm:
                initCollections();
                ArrayAdapter<Collection> adapter = new CollectionAdapter(SearchActivity.this, R.layout.collection_item, collections);
                searchListView.setAdapter(adapter);
                break;
        }
    }

    public void initCollections(){
        String search_text = searchTextView.getText().toString();
        Log.d("SearchActivity", search_text);
        class DBThread extends Thread{
            private List<Collection> data = new ArrayList<>();
            public DBThread(){

            }
            @Override
            public void run(){
                DBHelper helper = new DBHelper();
                String sql = "select * from Collection where col_name like '%" + search_text + "%'";
                Log.d("SearchActivity", sql);
                helper.setSql(sql);
                List<Map<String,Object>> rs = helper.executeQuery();
                for(int i=0; i<rs.size(); i++){
                    Collection collection = new Collection();
                    Map<String,Object> c_data = rs.get(i);
                    collection.setImgUrl((String) c_data.get("col_picture"));
                    collection.setColName((String) c_data.get("col_name"));
                    collection.setCol_info((String) c_data.get("col_info"));
                    collection.setColEra((String) c_data.get("col_era"));
                    collection.setColId((Double) c_data.get("col_id"));
                    collection.setMusId((Integer) c_data.get("mus_id"));
                    collection.setMusName((String) c_data.get("mus_name"));
                    Log.d("CollectionActivity", collection.getColName() + " " + collection.getImgUrl());
                    data.add(collection);
                }
            }
            public List<Collection> getData(){
                return data;
            }
        }
        DBThread dbThread = new DBThread();
        dbThread.start();
        try {
            dbThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        collections = dbThread.getData();
    }
}