package com.gob.museumapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Window;
import android.widget.ListView;

import com.gob.museumapp.R;
import com.gob.museumapp.db.DBHelper;
import com.gob.museumapp.model.Comment;
import com.gob.museumapp.model.MyCommentAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyCommentActivity extends Activity
{
    private ListView myCommentListView = null;
    private List<Comment> comments = new ArrayList<>();
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
}