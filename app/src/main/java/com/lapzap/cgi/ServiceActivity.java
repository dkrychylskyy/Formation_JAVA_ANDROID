package com.lapzap.cgi;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.squareup.otto.Subscribe;

public class ServiceActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_start, bt_stop;
    TextView tv_location;
    int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        bt_start = (Button) findViewById(R.id.bt_start);
        bt_stop = (Button) findViewById(R.id.bt_stop);
        tv_location = (TextView) findViewById(R.id.tv_location);

        bt_start.setOnClickListener(this);
        bt_stop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == bt_start) {
            //Démarre le service
            startService(new Intent(this, MonService.class));

        } else if (v == bt_stop) {
            // stop le service
            stopService(new Intent(this, MonService.class));
        }
    }

    @Subscribe
    public void afficherLocation(Location location) {
        tv_location.setText(location + " count: " + count++);
    }

    @Override
    protected void onStart() {
        super.onStart();
        MyApplication.getEventBus().register(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        MyApplication.getEventBus().unregister(this);
    }
}
