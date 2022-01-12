package com.gob.museumapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.gob.museumapp.R;
import com.gob.museumapp.db.DBHelper;
import com.gob.museumapp.model.Collection;
import com.gob.museumapp.model.RankCollectionAdapter;
import com.gob.museumapp.model.Score;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class RankListActivity extends Activity implements View.OnClickListener{

    private Button mainPageBtn = null;
    private Button rankListBtn = null;
    private Button myPageBtn = null;
    private ListView collectionListView = null;
    private List<Collection> collections = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏系统标题栏
        setContentView(R.layout.activity_rank_list);

        mainPageBtn = findViewById(R.id.main_page_btn);
        rankListBtn = findViewById(R.id.rank_list_btn);
        myPageBtn = findViewById(R.id.my_page_btn);
        collectionListView = findViewById(R.id.collection_list);

        mainPageBtn.setOnClickListener(this);
        rankListBtn.setOnClickListener(this);
        myPageBtn.setOnClickListener(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        initRankList();
        RankCollectionAdapter adapter = new RankCollectionAdapter(RankListActivity.this, R.layout.rank_collection_item, collections);
        collectionListView.setAdapter(adapter);
        collectionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Collection collection = collections.get(i);
                Intent jumpToDetail = new Intent(RankListActivity.this, CollectionDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putDouble("col_id",collection.getColId());
                bundle.putString("col_Name",collection.getColName());
                bundle.putString("col_Img",collection.getImgUrl());
                bundle.putString("col_Era",collection.getColEra());
                bundle.putString("col_Info",collection.getCol_info());
                bundle.putString("col_MusName",collection.getMusName());
                jumpToDetail.putExtra("col_detail", bundle);
                startActivity(jumpToDetail);
            }
        });
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch(viewId){
            case R.id.main_page_btn:
                Intent jumpToMuseum = new Intent(RankListActivity.this, MuseumActivity.class);
                startActivity(jumpToMuseum);
                break;
            case R.id.rank_list_btn:
                Intent jumpToRankList = new Intent(RankListActivity.this, RankListActivity.class);
                startActivity(jumpToRankList);
                break;
            case R.id.my_page_btn:
                Intent jumpToMyPage = new Intent(RankListActivity.this, MyActivity.class);
                startActivity(jumpToMyPage);
                break;
        }
    }

    public void initRankList(){
        class DBThread extends Thread{
            private List<Collection> data = new ArrayList<>();

            @Override
            public void run() {
                DBHelper helper = new DBHelper();
                String sql = "select col_id, AVG(score) as avg_score from Score_Collection GROUP BY col_id ORDER BY avg_score DESC;";
                helper.setSql(sql);
                List<Map<String, Object>> rs = helper.executeQuery();
                for(int i=0; i<rs.size(); i++){
                    Map<String, Object> s_data = rs.get(i);
                    Double col_id = (Double) s_data.get("col_id");
                    Double avg_score = (Double) s_data.get("avg_score");
                    DBHelper helper1 = new DBHelper();
                    String col_sql = "select * from Collection where col_id = " + col_id + ";";
                    helper1.setSql(col_sql);
                    List<Map<String, Object>> rs1 = helper1.executeQuery();
                    Map<String, Object> c_data = rs1.get(0);
                    Collection collection = new Collection();
                    collection.setImgUrl((String) c_data.get("col_picture"));
                    collection.setColName((String) c_data.get("col_name"));
                    collection.setCol_info((String) c_data.get("col_info"));
                    collection.setColEra((String) c_data.get("col_era"));
                    collection.setColId((Double) c_data.get("col_id"));
                    collection.setMusId((Integer) c_data.get("mus_id"));
                    collection.setMusName((String) c_data.get("mus_name"));
                    collection.setColScore(avg_score);
                    data.add(collection);
                }
            }

            public List<Collection> getData(){
                return this.data;
            }
        }

        DBThread thread = new DBThread();
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        collections = thread.getData();
    }
}