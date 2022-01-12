package com.gob.museumapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gob.museumapp.R;
import com.gob.museumapp.db.DBHelper;

import java.util.List;
import java.util.Map;


public class LoginActivity extends Activity implements View.OnClickListener
{
    private EditText phoneEdit=null;
    private EditText passwordEdit=null;

    private Button login=null;
    private Button register=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_login);
        phoneEdit = findViewById(R.id.phone);
        passwordEdit = findViewById(R.id.password);
        login = findViewById(R.id.login);
        register = findViewById(R.id.register);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.login:
                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        DBHelper dbhelper=new DBHelper();
                        String phone=phoneEdit.getText().toString();
                        String password=passwordEdit.getText().toString();
                        String query="SELECT * FROM User WHERE user_phone="+"'"+phone+"'"+"and user_password="+"'"+password+"'";
                        dbhelper.setSql(query);
                        List<Map<String,Object>> result=dbhelper.executeQuery();
                        if(result.size()!=0)
                        {
                            SharedPreferences.Editor editor = getSharedPreferences("user_data",MODE_PRIVATE).edit();
                            String UserName= (String) result.get(0).get("user_name");
                            int UserId= (int) result.get(0).get("user_id");
                            editor.putString("name",UserName);
                            editor.putString("phone",phone);
                            editor.putString("password",password);
                            editor.putInt("id",UserId);

                            editor.commit();

                            Intent intent = new Intent(LoginActivity.this, MineActivity.class);
                            intent.putExtra("User_ID",UserName);
                            startActivity(intent);
                            Log.d("NEWLoginActivity", "跳转成功");
                        }
                        else
                        {
                            Looper.prepare();
                            Toast.makeText(LoginActivity.this,"account or password is wrong", Toast.LENGTH_LONG).show();
                        }
                    }
                }).start();
                break;
            case R.id.register:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
        }
    }
}
