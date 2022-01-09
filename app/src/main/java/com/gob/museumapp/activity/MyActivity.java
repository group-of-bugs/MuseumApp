package com.gob.museumapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.gob.museumapp.R;

public class MyActivity extends Activity implements View.OnClickListener{

    private Button mainPageBtn = null;
    private Button rankListBtn = null;
    private Button myPageBtn = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏系统标题栏
        setContentView(R.layout.activity_my);

        mainPageBtn = findViewById(R.id.main_page_btn);
        rankListBtn = findViewById(R.id.rank_list_btn);
        myPageBtn = findViewById(R.id.my_page_btn);

        mainPageBtn.setOnClickListener(this);
        rankListBtn.setOnClickListener(this);
        myPageBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        int viewId = view.getId();
        switch(viewId){
            case R.id.main_page_btn:
                Intent jumpToMuseum = new Intent(MyActivity.this, MuseumActivity.class);
                startActivity(jumpToMuseum);
                break;
            case R.id.rank_list_btn:
                Intent jumpToRankList = new Intent(MyActivity.this, RankListActivity.class);
                startActivity(jumpToRankList);
                break;
            case R.id.my_page_btn:
                Intent jumpToMyPage = new Intent(MyActivity.this, MyActivity.class);
                startActivity(jumpToMyPage);
                break;
        }
    }
}