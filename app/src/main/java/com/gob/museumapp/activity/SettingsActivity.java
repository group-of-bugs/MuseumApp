package com.gob.museumapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;

import com.gob.museumapp.R;

public class SettingsActivity extends Activity implements View.OnClickListener
{
    private Button button1=null;
    private Button button2=null;
    private Button button3=null;
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_settings);
        button1=findViewById(R.id.change_password);
        button2=findViewById(R.id.change_account);
        button3=findViewById(R.id.logout);

        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        button3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        switch(v.getId())
        {
            case R.id.change_password:
            {
                Intent intent = new Intent(SettingsActivity.this, ChangePasswordActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.change_account:
            case R.id.logout: {
                Intent intent = new Intent(SettingsActivity.this, LoginActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
