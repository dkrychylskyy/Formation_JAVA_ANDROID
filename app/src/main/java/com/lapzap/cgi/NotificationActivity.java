package com.lapzap.cgi;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class NotificationActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_immediat, bt_5sec;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        bt_immediat = (Button) findViewById(R.id.bt_immediat);
        bt_5sec = (Button) findViewById(R.id.bt_5sec);

        bt_immediat.setOnClickListener(this);
        bt_5sec.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == bt_immediat) {
            NotificationUtil.createInstantNotification(this, "Hello");
        }
    }

}
