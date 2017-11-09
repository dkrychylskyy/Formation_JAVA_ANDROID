package com.lapzap.cgi;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

/**
 * Created by krychylskyy on 09/11/2017.
 */

public class MyBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        boolean isAirplaneModeOn = intent.getBooleanExtra("state", false);

        if (isAirplaneModeOn) {

            Toast.makeText(context, "AIRPLANE_MODE ON", Toast.LENGTH_SHORT).show();

        } else {

            Toast.makeText(context, "AIRPLANE_MODE OFF", Toast.LENGTH_SHORT).show();

        }

    }
}
