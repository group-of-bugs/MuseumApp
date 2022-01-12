package com.gob.museumapp.activity;

import androidx.core.content.ContextCompat;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.gob.museumapp.R;
import com.gob.museumapp.db.DBHelper;
import com.gob.museumapp.model.Collection;
import com.gob.museumapp.model.Exhibition;
import com.gob.museumapp.model.ExhibitionAdapter;
import com.gob.museumapp.model.Museum;
import com.gob.museumapp.model.MuseumAdapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ExhibitionActivity extends Activity {


    private ListView exhibitionListView = null;
    private String Mus_id;

    private List<Exhibition> exhibitions = new ArrayList<>();

    @SuppressLint("ResourceAsColor")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏系统标题栏
        setContentView(R.layout.activity_exhibition);
        Bundle b= this.getIntent().getBundleExtra("ExhList");
        Mus_id = b.getString("mus_Id");
        exhibitionListView = findViewById(R.id.exhibition_list);
    }

    @Override
    protected void onResume() {
        super.onResume();

        // 怎么只能这样设置颜色

        initExhibition();
        ExhibitionAdapter adapter = new ExhibitionAdapter(ExhibitionActivity.this, R.layout.exhibition_item, exhibitions);
        exhibitionListView.setAdapter(adapter);
        exhibitionListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Intent jumpToMuseumDetail = new Intent(ExhibitionActivity.this, ExhibitionDetailActivity.class);
                        Exhibition detail = exhibitions.get(position);
                        Bundle bundle = new Bundle();
                        bundle.putDouble("mus_Id",detail.getMusId());
                        bundle.putString("exh_id",detail.getExhId().toString());
                        bundle.putString("exh_name",detail.getExhName());
                        bundle.putString("exh_info",detail.getExhinfo());
                        bundle.putString("mus_name",detail.getMusName());
                        bundle.putString("exh_picture",detail.getImgUrl());
                        bundle.putString("exh_time",detail.getExhTime());
                        jumpToMuseumDetail.putExtra("mus_detail",bundle);
                        startActivity(jumpToMuseumDetail);

                    }
                }
        );
    }



    private void initExhibition(){
        class DBThread extends Thread{
            private List<Exhibition> data = new ArrayList<>();
            public DBThread(){

            }
            @Override
            public void run(){
                DBHelper helper = new DBHelper();
                String sql = "select * from museum.Exhibition where mus_id = "+ Mus_id ;
                helper.setSql(sql);
                List<Map<String,Object>> rs = helper.executeQuery();
                for(int i=0; i<rs.size(); i++){
                    Exhibition exhibition = new Exhibition();
                    Map<String,Object> m_data = rs.get(i);
                    exhibition.setMusId((Integer) m_data.get("mus_id"));
                    exhibition.setExhId((Integer) m_data.get("exh_id"));
                    exhibition.setExhName((String) m_data.get("exh_name"));
                    exhibition.setMusName((String) m_data.get("mus_name"));
                    exhibition.setExhinfo((String) m_data.get("exh_info"));
                    exhibition.setImgUrl((String) m_data.get("exh_picture"));
                    exhibition.setExhTime((String) m_data.get("exh_time"));
                    data.add(exhibition);
                }
            }
            public List<Exhibition> getData(){
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
        exhibitions = dbThread.getData();
    }
}