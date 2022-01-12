package com.gob.museumapp.activity;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import com.gob.museumapp.R;
import com.gob.museumapp.db.DBHelper;
import com.gob.museumapp.model.Collection;
import com.gob.museumapp.model.CollectionAdapter;
import com.gob.museumapp.model.Comment;
import com.gob.museumapp.model.CommentAdapter;
import com.gob.museumapp.util.LoadImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class CollectionDetailActivity extends Activity implements View.OnClickListener{

    private TextView collectionTitle = null;
    private ImageView collectionImage = null;
    private TextView collectionName = null;
    private TextView collectionInfo = null;
    private Double Col_id;
    private ListView commentList = null;
    private Button Comment = null;
    private EditText newComment = null;
    private String newContent;
    private Integer user_id = 1;

    private List<Comment> comments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏系统标题栏
        setContentView(R.layout.activity_collection_detail);
        Bundle b= this.getIntent().getBundleExtra("col_detail");

        collectionTitle = findViewById(R.id.collection_title);
        collectionTitle.setText("藏品详情");
        collectionImage = findViewById(R.id.collection_img);
        LoadImage loader = new LoadImage(collectionImage);
        loader.setBitmap(b.getString("col_Img"));
        collectionName = findViewById(R.id.collection_name);
        collectionName.setText(b.getString("col_Name"));
        collectionInfo = findViewById(R.id.collection_info);
        collectionInfo.setText(b.getString("col_Info"));
        Comment = findViewById(R.id.commitBtn);
        newComment = findViewById(R.id.editComment);
        Col_id = b.getDouble("col_id");
        commentList = findViewById(R.id.comment_list);

        Comment.setOnClickListener(this);
        //评论详情
        initComments();
        ArrayAdapter<Comment> adapter = new CommentAdapter(CollectionDetailActivity.this, R.layout.comment_item, comments);
        commentList.setAdapter(adapter);

        //写入新评论
        newContent = newComment.getText().toString();//新评论内容
        Log.d("CollectionDetailActivity", "Content: " + newContent);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId){
            case R.id.commitBtn:
                newContent = newComment.getText().toString();//新评论内容
                insertNewComment();
                break;
        }
    }

    private void insertNewComment(){
        class DBThread extends Thread {
            private List<Comment> data = new ArrayList<>();

            public DBThread() {

            }

            @Override
            public void run() {
                DBHelper helper = new DBHelper();
                String StrCol_id = String.valueOf(Col_id);
//                String sql = "select * from Comment_Collection where col_id = " + StrCol_id + " limit 50";
                String sql = "insert into Comment_Collection(user_id,col_id,content) values(" + user_id + "," + Col_id + "," + "'" + newContent + "'" + ")";
                Log.d("CollectionDetailActivity", sql);
                helper.setSql(sql);
                helper.executeUpdate();
            }
        }
        DBThread thread = new DBThread();
        thread.start();
    }
    private void initComments(){
        class DBThread extends Thread{
            private List<Comment> data = new ArrayList<>();
            public DBThread(){

            }
            @Override
            public void run(){
                DBHelper helper = new DBHelper();
                String StrCol_id = String.valueOf(Col_id);
                String sql = "select * from Comment_Collection where col_id = " + StrCol_id + " limit 50";
                helper.setSql(sql);
                List<Map<String,Object>> rs = helper.executeQuery();
                for(int i=0; i<rs.size(); i++){
                    Comment comment = new Comment();
                    Map<String,Object> c_data = rs.get(i);
                    comment.setCol_id((Double) c_data.get("col_id"));
                    comment.setCom_id((Integer) c_data.get("com_id"));
                    comment.setUser_id((Integer) c_data.get("user_id"));
                    DBHelper _helper = new DBHelper();
                    String _sql = "select user_name from User where user_id = " + comment.getUser_id();
                    _helper.setSql(_sql);
                    List<Map<String,Object>>_rs = _helper.executeQuery();
                    Log.d("tag" , _rs.get(0).get("user_name").toString());
                    comment.setUser_name((String) _rs.get(0).get("user_name"));
                    comment.setContent((String) c_data.get("content"));
                    Log.d("CollectionActivity", comment.getCol_id() + " " + comment.getUser_id());
                    data.add(comment);
                }
            }
            public List<Comment> getData(){
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
        comments = dbThread.getData();
    }
}
