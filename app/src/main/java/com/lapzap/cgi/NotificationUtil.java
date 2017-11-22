package com.lapzap.cgi;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;

/**
 * Created by krychylskyy on 15/11/2017.
 */

public class NotificationUtil {
    //Envoyer une notification immediate
    public static void createInstantNotification(Context context, String message) {
//Ce qui se passera quand on cliquera sur la notif
        Intent intent = new Intent(context, MainActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 28, intent,
                PendingIntent.FLAG_ONE_SHOT);
        //La notification
        Notification notification = new Notification.Builder(context).setSmallIcon(R.mipmap.ic_launcher).setContentTitle("Le titre")
                .setContentText(message).setContentIntent(pendingIntent).build();
        //Envoyer la notification
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
//ENVOIE
        notificationManager.notify(29, notification);
    }
}
