package com.gob.museumapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.gob.museumapp.R;

public class MineActivity extends Activity implements View.OnClickListener{

    private Button mainPageBtn = null;
    private Button rankListBtn = null;
    private Button myPageBtn = null;
    private ImageView img=null;
    private TextView textview=null;
    private Button button1=null;
    private Button button2=null;
    private Button button3=null;
    private Button button4=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏系统标题栏
        setContentView(R.layout.activity_mine);

        mainPageBtn = findViewById(R.id.main_page_btn);
        rankListBtn = findViewById(R.id.rank_list_btn);
        myPageBtn = findViewById(R.id.my_page_btn);
        img=findViewById(R.id.userimg);
        textview=findViewById(R.id.userid);
        button1=findViewById(R.id.history);
        button2=findViewById(R.id.favourite);
        button3=findViewById(R.id.comment);
        button4=findViewById(R.id.settings);

        mainPageBtn.setOnClickListener(this);
        rankListBtn.setOnClickListener(this);
        myPageBtn.setOnClickListener(this);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
        button4.setOnClickListener(this);

        Intent receive_intent=getIntent();
        String user_id=receive_intent.getStringExtra("User_ID");

        SharedPreferences pref=getSharedPreferences("user_data",MODE_PRIVATE);
        String user_name=pref.getString("name","");

        textview.setText(user_name);
        textview.setTextSize(30);
        Log.d("username", user_id);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch(viewId){
            case R.id.main_page_btn:
                Intent jumpToMuseum = new Intent(MineActivity.this, MuseumActivity.class);
                startActivity(jumpToMuseum);
                break;
            case R.id.rank_list_btn:
                Intent jumpToRankList = new Intent(MineActivity.this, RankListActivity.class);
                startActivity(jumpToRankList);
                break;
            case R.id.my_page_btn:
                Intent jumpToMyPage = new Intent(MineActivity.this, MineActivity.class);
                startActivity(jumpToMyPage);
                break;
            case R.id.settings:
            {
                Intent intent = new Intent(MineActivity.this,SettingsActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.comment:
            {
                Intent intent = new Intent(MineActivity.this,MyCommentActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}