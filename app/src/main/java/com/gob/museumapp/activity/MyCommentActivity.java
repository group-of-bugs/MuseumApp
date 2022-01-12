package com.gob.museumapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ListView;

import com.gob.museumapp.R;
import com.gob.museumapp.db.DBHelper;
import com.gob.museumapp.model.Collection;
import com.gob.museumapp.model.Comment;
import com.gob.museumapp.model.MyCommentAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyCommentActivity extends Activity
{
    private ListView myCommentListView = null;
    private List<Comment> comments = new ArrayList<>();
    private List<Collection> collections = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_comment);

        myCommentListView = findViewById(R.id.comment_id);

    }

    @Override
    protected void onResume() {
        super.onResume();
        initCommentList();
        MyCommentAdapter adapter = new MyCommentAdapter(MyCommentActivity.this, R.layout.comment_item, comments);
        myCommentListView.setAdapter(adapter);
        myCommentListView.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent jumpToCollectionDetail = new Intent(MyCommentActivity.this, CollectionDetailActivity.class);
                Collection detail = initCollections(comments.get(position).getCol_id());
                Log.d("藏品主页",detail.getImgUrl());
                Bundle bundle = new Bundle();
                bundle.putDouble("col_id",detail.getColId());
                bundle.putString("col_Name",detail.getColName());
                bundle.putString("col_Img",detail.getImgUrl());
                bundle.putString("col_Era",detail.getColEra());
                bundle.putString("col_Info",detail.getCol_info());
                bundle.putString("col_MusName",detail.getMusName());
                Log.d("藏品主页",detail.getImgUrl());
                jumpToCollectionDetail.putExtra("col_detail",bundle);
                startActivity(jumpToCollectionDetail);

            }
        });

    }

    public void initCommentList()
    {
        class DBThread extends Thread
        {
            private List<Comment> data = new ArrayList<>();

            public DBThread()
            {

            }

            @Override
            public void run()
            {
                DBHelper helper = new DBHelper();
                SharedPreferences pref=getSharedPreferences("user_data",MODE_PRIVATE);
                int user_id=(int) pref.getInt("id",0);
                String sql = "select * from Comment_Collection where user_id = "+user_id;

                helper.setSql(sql);
                List<Map<String,Object>> rs = helper.executeQuery();
                for(int i=0; i<rs.size(); i++)
                {
                    Comment comment = new Comment();
                    Map<String, Object> c_data = rs.get(i);
                    comment.setCol_id((Double) c_data.get("col_id"));
                    comment.setContent((String) c_data.get("content"));
                    data.add(comment);
                }
            }

            public List<Comment> getData(){
                return data;
            }
        }

        DBThread thread = new DBThread();
        thread.start();

        try {
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        comments = thread.getData();
    }

    public Collection initCollections(Double Col_id){


        class DBThread extends Thread{
            private Collection data = new Collection();
            public DBThread(){

            }
            @Override
            public void run(){
                DBHelper helper = new DBHelper();
                String sql = "select * from Collection where col_id =" + Col_id ;
                Log.d("SearchActivity", sql);
                helper.setSql(sql);
                List<Map<String,Object>> rs = helper.executeQuery();
                Collection collection = new Collection();
                Map<String,Object> c_data = rs.get(0);
                collection.setImgUrl((String) c_data.get("col_picture"));
                collection.setColName((String) c_data.get("col_name"));
                collection.setCol_info((String) c_data.get("col_info"));
                collection.setColEra((String) c_data.get("col_era"));
                collection.setColId((Double) c_data.get("col_id"));
                collection.setMusId((Integer) c_data.get("mus_id"));
                collection.setMusName((String) c_data.get("mus_name"));
                Log.d("CollectionActivity", collection.getColName() + " " + collection.getImgUrl());
                data = collection;


            }

            public Collection getData(){
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
        return dbThread.getData();
    }
}