package com.gob.museumapp.receiver;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.gob.museumapp.activity.CollectorActivity;
import com.gob.museumapp.activity.LoginActivity;

public class ForceOfflineReceiver extends BroadcastReceiver
{

    @Override
    public void onReceive(final Context context, Intent intent)
    {
        AlertDialog.Builder dialogBuilder=new AlertDialog.Builder(context);
        dialogBuilder.setTitle("Warning");
        dialogBuilder.setMessage("OFFLINE");
        dialogBuilder.setCancelable(false);
        dialogBuilder.setPositiveButton
                (
                        "OK",
                        new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int which)
                            {
                                CollectorActivity.finishAll();
                                Intent intent=new Intent(context, LoginActivity.class);
                                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                                context.startActivity(intent);
                            }
                        }

                );
        AlertDialog alertDialog=dialogBuilder.create();
    }
}
