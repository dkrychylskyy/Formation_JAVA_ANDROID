package com.lapzap.cgi;

import android.Manifest;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.widget.Toast;

/**
 * Created by krychylskyy on 15/11/2017.
 */

public class MonService extends Service implements LocationListener {

    LocationManager locationMgr;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onLocationChanged(Location location) {
        Double latitude = location.getLatitude();
        Double longitude = location.getLongitude();

        MyApplication.getEventBus().post(location);

        Toast.makeText(this, "latitude : " + latitude + " " + "longitude : " + longitude, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {
    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    @Override
    public void onCreate() {

        //Verification les permission pour service
        //Si on a pas la permission on ne s’abonne pas (onCreate)
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            stopSelf();
            return;
        }
        //Minimum (et non égale, c’est Android qui gère) 5 secondes et 200m de difference.(onCreate) //Le this ici représente l’interface « LocationListner » à implémenter MANUELLEMENT
        locationMgr = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (locationMgr.getAllProviders().contains(LocationManager.NETWORK_PROVIDER))
            locationMgr.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 0, this);
        if (locationMgr.getAllProviders().contains(LocationManager.GPS_PROVIDER))
            locationMgr.requestLocationUpdates(LocationManager.GPS_PROVIDER, 5000, 0, this);

        super.onCreate();
        Toast.makeText(this, "MonService á été crée", Toast.LENGTH_SHORT).show();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "Start MonService", Toast.LENGTH_SHORT).show();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        locationMgr.removeUpdates(this);
        Toast.makeText(this, "Destroy MonService", Toast.LENGTH_SHORT).show();
        super.onDestroy();
    }

}
