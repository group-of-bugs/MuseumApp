package com.gob.museumapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gob.museumapp.R;
import com.gob.museumapp.db.DBHelper;

import java.util.List;
import java.util.Map;

public class ChangePasswordActivity extends Activity implements View.OnClickListener
{
    private EditText oldPwd=null;
    private EditText newPwd=null;
    private Button confirm=null;
    private int user_id = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_change_password);
        oldPwd = findViewById(R.id.old_pwd);
        newPwd = findViewById(R.id.new_pwd);
        confirm = findViewById(R.id.change_confirm);
        confirm.setOnClickListener(this);
        SharedPreferences pref=getSharedPreferences("user_data",MODE_PRIVATE);
        user_id = pref.getInt("id", 1);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.change_confirm:
                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        DBHelper dbhelper=new DBHelper();
                        String oldValue=oldPwd.getText().toString();
                        String newValue=newPwd.getText().toString();
                        String query="select * from User where user_id = " + user_id + " and user_password = '" + oldValue + "'";
                        dbhelper.setSql(query);
                        List<Map<String, Object>> rs = dbhelper.executeQuery();
                        if(rs.size() > 0){
                            DBHelper helper1 = new DBHelper();
                            String sql1 = "update User set user_password = '" + newValue + "' where user_id = " + user_id;
                            helper1.setSql(sql1);
                            int r = helper1.executeUpdate();
                            Looper.prepare();
                            if(r == 1){
                                Toast.makeText(ChangePasswordActivity.this,"Change successfully", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                                startActivity(intent);
                            }
                            else{
                                Toast.makeText(ChangePasswordActivity.this,"Change Failed. Please retry later", Toast.LENGTH_LONG).show();
                            }
                        }
                        else{
                            Looper.prepare();
                            Toast.makeText(ChangePasswordActivity.this,"Wrong old password", Toast.LENGTH_LONG).show();
                            oldPwd.setText("");
                        }
                    }
                }).start();

        }
    }
}
