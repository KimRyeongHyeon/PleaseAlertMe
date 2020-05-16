package com.myandroid.pleasealertme;

import android.app.AlarmManager;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import java.util.Date;

public class AlarmReceiver7 extends BroadcastReceiver {
    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public void onReceive(Context context, Intent intent) {
        NotificationManager notificationManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        Intent notificationIntent = new Intent(context, MainActivity.class);

        notificationIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);

        PendingIntent pendingI = PendingIntent.getActivity(context, 0, notificationIntent, 0);

        NotificationCompat.Builder  builder = new NotificationCompat.Builder(context, "default");

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            builder.setSmallIcon(R.drawable.ic_launcher_foreground);

            String channerName = "매일 알람 채널";
            String description = "매일 정해진 시간에 알람합니다.";
            int importance = NotificationManager.IMPORTANCE_HIGH;

            NotificationChannel channel = new NotificationChannel("default", channerName, importance);
            channel.setDescription(description);

            if(notificationManager != null) {
                notificationManager.createNotificationChannel(channel);
            }
        }  else {
            builder.setSmallIcon(R.mipmap.ic_launcher_round);
        }

        builder.setAutoCancel(true).setDefaults(NotificationCompat.DEFAULT_ALL)
                .setContentTitle("3시간 후 알람을 알려드립니다.")
                .setContentText("3시간 마다 알람이 울립니다.")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("매일 정해진 시간에 알람이 울립니다."))
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingI);

        if(notificationManager != null) {
            notificationManager.notify(1234, builder.build());
        }

        System.out.println(new Date().toString());

        AlarmManager alarmManager = (AlarmManager) context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, AlarmReceiver7.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(context, 70, i, PendingIntent.FLAG_UPDATE_CURRENT);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            alarmManager.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (60 * 1000 * 60 * 3), pendingIntent);
        } else {
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, System.currentTimeMillis() + (60 * 1000 * 60 * 3), pendingIntent);
        }
    }
}
