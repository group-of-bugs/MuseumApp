package com.gob.museumapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.gob.museumapp.R;
import com.gob.museumapp.db.DBHelper;

public class RegisterActivity extends Activity implements View.OnClickListener
{
    private EditText phoneEdit=null;
    private EditText nameEdit=null;
    private EditText passwordEdit=null;
    private Button register=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_register);
        phoneEdit = findViewById(R.id.phone);
        nameEdit = findViewById(R.id.name);
        passwordEdit = findViewById(R.id.password);
        register = findViewById(R.id.register);
        register.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
             case R.id.register:
                new Thread(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        DBHelper dbhelper=new DBHelper();
                        String phone=phoneEdit.getText().toString();
                        String name=nameEdit.getText().toString();
                        String password=passwordEdit.getText().toString();
                        String query="INSERT INTO User (user_phone,user_name,user_password) VALUES ('"+phone+"','"+name+"','"+password+"')";                      dbhelper.setSql(query);
                        dbhelper.executeUpdate();
                        Looper.prepare();
                        Toast.makeText(RegisterActivity.this,"register successfully,please login again", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }).start();
        }
    }
}
