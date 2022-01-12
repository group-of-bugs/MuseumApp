package com.gob.museumapp.activity;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.gob.museumapp.R;
import com.gob.museumapp.db.DBHelper;
import com.gob.museumapp.model.Museum;
import com.gob.museumapp.model.MuseumAdapter;
import com.gob.museumapp.model.Comment;
import com.gob.museumapp.model.CommentAdapter;
import com.gob.museumapp.util.LoadImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MuseumDetailActivity extends Activity implements View.OnClickListener{

    private TextView museumTitle = null;
    private ImageView museumImage = null;
    private TextView museumName = null;
    private TextView museumAddress = null;
    private TextView museumTime = null;
    private TextView museumUrl = null;
    private TextView museumMaster = null;
    private Double Mus_id;
    private ListView museum_commentList = null;
    private Button museum_Comment = null;
    private EditText museum_newComment = null;
    private RatingBar museum_ratingBar = null;
    private Button museum_rate = null;
    private String museum_newContent;
    private Integer user_id = 1;

    private List<Comment> comments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏系统标题栏
        setContentView(R.layout.activity_museum_detail);
        Bundle b= this.getIntent().getBundleExtra("mus_detail");


        museumTitle = findViewById(R.id.museum_title);
        museumTitle.setText("博物馆详情");
        museumImage = findViewById(R.id.museum_img);
        LoadImage loader = new LoadImage(museumImage);
        loader.setBitmap(b.getString("mus_Img"));
        museumName = findViewById(R.id.museum_name);
        museumName.setText(b.getString("mus_Name"));
        museumAddress = findViewById(R.id.museum_address);
        museumTime = findViewById(R.id.museum_time);
        museumUrl = findViewById(R.id.museum_url);
        museumMaster = findViewById(R.id.museum_master);
        museumUrl.setText("博物馆网址: " + b.getString("mus_Url"));
        museumMaster.setText("博物馆馆长: " + b.getString("mus_Master"));
        museumAddress.setText("博物馆地址: " + b.getString("mus_Address"));
        museumTime.setText("开馆时间: " + b.getString("mus_Time"));
        museum_Comment = findViewById(R.id.museum_commitBtn);
        museum_Comment.setOnClickListener(this);
        museum_newComment = findViewById(R.id.museum_editComment);
        Mus_id = b.getDouble("mus_Id");
        museum_commentList = findViewById(R.id.museum_comment_list);

        //评论详情
        initComments();
        ArrayAdapter<Comment> adapter = new CommentAdapter(MuseumDetailActivity.this, R.layout.comment_item, comments);
        museum_commentList.setAdapter(adapter);

        //写入新评论

    }
    @Override
    protected void onResume() {
        super.onResume();
        initComments();
        ArrayAdapter<Comment> adapter = new CommentAdapter(MuseumDetailActivity.this, R.layout.comment_item, comments);
        museum_commentList.setAdapter(adapter);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch (viewId){
            case R.id.museum_commitBtn:
                museum_newContent = museum_newComment.getText().toString();//新评论内容
                Log.d("lyl", "Comment" + museum_newContent);
                insertNewComment();
                Toast.makeText(MuseumDetailActivity.this, "评论成功！", Toast.LENGTH_LONG).show();
                this.onResume();
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
                String StrMus_id = String.valueOf(Mus_id);
//                String sql = "select * from Comment_museum where Mus_id = " + StrMus_id + " limit 50";
                String sql = "insert into Comment_Museum(user_id,mus_id,content) values(" + user_id + "," + Mus_id + ",'" + museum_newContent + "')";
                Log.d("tag" , museum_newContent + user_id.toString());
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
                String StrMus_id = String.valueOf(Mus_id);
                String sql = "select * from Comment_Museum where Mus_id = " + StrMus_id + " limit 50";
                helper.setSql(sql);
                List<Map<String,Object>> rs = helper.executeQuery();
                for(int i=0; i<rs.size(); i++){
                    Comment comment = new Comment();
                    Map<String,Object> c_data = rs.get(i);
                    comment.setCol_id((Double) c_data.get("Mus_id"));
                    comment.setCom_id((Integer) c_data.get("com_id"));
                    comment.setUser_id((Integer) c_data.get("user_id"));
                    DBHelper _helper = new DBHelper();
                    String _sql = "select user_name from User where user_id = " + comment.getUser_id();
                    _helper.setSql(_sql);
                    List<Map<String,Object>>_rs = _helper.executeQuery();
                    comment.setUser_name((String) _rs.get(0).get("user_name"));
                    comment.setContent((String) c_data.get("content"));
                    Log.d("museumActivity", comment.getCol_id() + " " + comment.getUser_id());
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

