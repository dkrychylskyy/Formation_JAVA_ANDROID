package com.lapzap.cgi;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

/**
 * Created by krychylskyy on 09/11/2017.
 */

public class SecondActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_finish;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        bt_finish = (Button) findViewById(R.id.bt_finish);

        bt_finish.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Toast.makeText(this, "C'est l'écran precedent", Toast.LENGTH_SHORT).show();
        finish();
    }
}
