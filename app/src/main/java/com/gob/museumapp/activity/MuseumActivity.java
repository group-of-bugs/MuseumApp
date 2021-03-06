package com.gob.museumapp.activity;

import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.gob.museumapp.R;
import com.gob.museumapp.db.DBHelper;
import com.gob.museumapp.model.Museum;
import com.gob.museumapp.model.MuseumAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MuseumActivity extends Activity implements View.OnClickListener{

    private Button museumBtn = null;
    private Button collectionBtn = null;
    private Button searchBtn = null;
    private ListView museumListView = null;
    private Button mainPageBtn = null;
    private Button rankListBtn = null;
    private Button myPageBtn = null;
    private static Object lock = new Object();

    private List<Museum> museums = new ArrayList<>();

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏系统标题栏
        setContentView(R.layout.activity_museum);

        museumBtn = findViewById(R.id.museum_btn);
        collectionBtn = findViewById(R.id.collection_btn);
        searchBtn = findViewById(R.id.search_btn);
        museumListView = findViewById(R.id.museum_list);
        mainPageBtn = findViewById(R.id.main_page_btn);
        rankListBtn = findViewById(R.id.rank_list_btn);
        myPageBtn = findViewById(R.id.my_page_btn);

        museumBtn.setOnClickListener(this);
        collectionBtn.setOnClickListener(this);
        searchBtn.setOnClickListener(this);
        mainPageBtn.setOnClickListener(this);
        rankListBtn.setOnClickListener(this);
        myPageBtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 怎么只能这样设置颜色
        museumBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        collectionBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));
        searchBtn.setBackgroundColor(ContextCompat.getColor(this, R.color.white));

        initMuseums();
        MuseumAdapter adapter = new MuseumAdapter(MuseumActivity.this, R.layout.museum_item, museums);
        museumListView.setAdapter(adapter);
        museumListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent jumpToMuseumDetail = new Intent(MuseumActivity.this, MuseumDetailActivity.class);
                        Museum detail = museums.get(position);
                        Log.d("藏品主页",detail.getImgUrl());
                        Bundle bundle = new Bundle();
                        bundle.putDouble("mus_Id",detail.getMid());
                        bundle.putString("mus_Url",detail.getRemark());
                        bundle.putString("mus_Name",detail.getMuseumName());
                        bundle.putString("mus_Img",detail.getImgUrl());
                        bundle.putString("mus_Time",detail.getOpenTime());
                        bundle.putString("mus_Address",detail.getAddress());
                        bundle.putString("mus_Phone",detail.getPhone());
                        bundle.putString("mus_Master",detail.getMaster());
                        jumpToMuseumDetail.putExtra("mus_detail",bundle);
                        startActivity(jumpToMuseumDetail);

                    }
                }
        );
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId){
            case R.id.museum_btn:
            case R.id.main_page_btn:
                Intent jumpToMuseum = new Intent(MuseumActivity.this, MuseumActivity.class);
                startActivity(jumpToMuseum);
                break;
            case R.id.collection_btn:
                Intent jumpToCollection = new Intent(MuseumActivity.this, CollectionActivity.class);

                startActivity(jumpToCollection);
                break;
            case R.id.search_btn:
                Intent jumpToSearch = new Intent(MuseumActivity.this, SearchActivity.class);
                startActivity(jumpToSearch);
                break;
            case R.id.rank_list_btn:
                Intent jumpToRankList = new Intent(MuseumActivity.this, RankListActivity.class);
                startActivity(jumpToRankList);
                break;
            case R.id.my_page_btn:
                Intent jumpToMyPage = new Intent(MuseumActivity.this, MineActivity.class);
                startActivity(jumpToMyPage);
                break;
        }
    }

    private void initMuseums(){
        class DBThread extends Thread{
            private List<Museum> data = new ArrayList<>();
            public DBThread(){

            }
            @Override
            public void run(){
                DBHelper helper = new DBHelper();
                String sql = "select * from museum.Museum where mus_id = 1101 or mus_id = 1107 or mus_id = 1111";
                helper.setSql(sql);
                List<Map<String,Object>> rs = helper.executeQuery();
                for(int i=0; i<rs.size(); i++){
                    Museum museum = new Museum();
                    Map<String,Object> m_data = rs.get(i);
                    museum.setMid((Integer) m_data.get("mus_id"));
                    museum.setImgUrl((String) m_data.get("mus_picture"));
                    museum.setMuseumName((String) m_data.get("mus_name"));
                    museum.setAddress((String) m_data.get("mus_address"));
                    museum.setOpenTime((String) m_data.get("mus_time"));
                    museum.setRemark((String) m_data.get("mus_remark"));
                    museum.setMaster((String) m_data.get("mus_master"));
                    museum.setPhone((String) m_data.get("mus_phone"));
                    museum.setGrade((Double) m_data.get("mus_grade"));
                    data.add(museum);
                }
            }
            public List<Museum> getData(){
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
        museums = dbThread.getData();
    }
}


