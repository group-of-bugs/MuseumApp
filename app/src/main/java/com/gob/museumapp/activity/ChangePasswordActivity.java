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

public class ChangePasswordActivity extends Activity implements View.OnClickListener
{
    private EditText phoneEdit=null;
    private EditText passwordEdit=null;
    private Button confirm=null;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_change_password);
        phoneEdit = findViewById(R.id.phone);
        passwordEdit = findViewById(R.id.password);
        confirm = findViewById(R.id.change_confirm);
        confirm.setOnClickListener(this);
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
                        String phone=phoneEdit.getText().toString();
                        String password=passwordEdit.getText().toString();
                        String query="UPDATE User SET user_password='"+password+"' WHERE user_phone='"+phone+"'";
                        dbhelper.setSql(query);
                        dbhelper.executeUpdate();
                        Looper.prepare();
                        Toast.makeText(ChangePasswordActivity.this,"Change successfully", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(ChangePasswordActivity.this, LoginActivity.class);
                        startActivity(intent);
                    }
                }).start();

        }
    }
}
