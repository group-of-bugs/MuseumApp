package com.gob.museumapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.gob.museumapp.R;
import com.gob.museumapp.db.DBHelper;
import com.gob.museumapp.model.Comment;
import com.gob.museumapp.model.CommentAdapter;
import com.gob.museumapp.util.LoadImage;

import java.util.ArrayList;
import java.util.List;

public class ExhibitionDetailActivity extends Activity {

    private TextView exhibitionTitle = null;
    private ImageView exhibitionImage = null;
    private TextView exhibitionName = null;
    private TextView exhibitionInfo = null;
    private TextView exhibitionTime = null;
    private Double Mus_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE); //隐藏系统标题栏
        setContentView(R.layout.activity_exhibition_detail);
        Bundle b= this.getIntent().getBundleExtra("mus_detail");

        exhibitionTitle = findViewById(R.id.exhibition_title);
        exhibitionTitle.setText("展览详情");
        exhibitionImage = findViewById(R.id.exhibition_img);
        LoadImage loader = new LoadImage(exhibitionImage);
        loader.setBitmap(b.getString("exh_picture"));
        exhibitionName = findViewById(R.id.exhibition_name);
        exhibitionName.setText(b.getString("exh_name"));
        exhibitionTime = findViewById(R.id.exhibition_time);
        exhibitionInfo = findViewById(R.id.exhibition_info);
        exhibitionInfo.setText("展览详情: " + b.getString("exh_info"));
        exhibitionTime = findViewById(R.id.exhibition_time);
        exhibitionTime.setText("展览时间: " + b.getString("exh_time"));
        Mus_id = b.getDouble("mus_Id");


    }

}