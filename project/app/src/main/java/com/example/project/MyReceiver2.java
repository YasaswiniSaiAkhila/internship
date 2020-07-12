package com.example.project;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;

import androidx.core.app.NotificationCompat;

public class MyReceiver2 extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager m;
        m= (NotificationManager) context.getSystemService(context.NOTIFICATION_SERVICE);
        NotificationCompat.Builder b=new NotificationCompat.Builder(context,"ya");
        b.setSmallIcon(R.mipmap.onic);
        b.setContentTitle("self-attendence");
        b.setAutoCancel(true);
        b.setContentText("click to mark today attendence");
        Intent i=new Intent(context,mark.class);
        PendingIntent pi=PendingIntent.getActivity(context,0,i,0);
        b.setContentIntent(pi);
        m.notify(0,b.build());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            NotificationChannel c=new NotificationChannel("ya","a", NotificationManager.IMPORTANCE_HIGH);
            c.enableLights(true);
            c.setLightColor(Color.GREEN);
            c.enableVibration(true);
            m.createNotificationChannel(c);
        }


    }
}
