package com.example.chapter08.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import com.example.chapter08.NotificationActivity;
import com.example.chapter08.R;

public class NotificationUtil {
    public static Notification createNotification(Context context) {
        NotificationCompat.Builder builder;
        Intent intent = new Intent(context, NotificationActivity.class);
        PendingIntent pi = PendingIntent.getActivity(context, 0, intent, PendingIntent.FLAG_IMMUTABLE);
        NotificationManager manager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = createNotificationChannel("ckn", "normalChannel", manager);
            builder = new NotificationCompat.Builder(context, channelId);
        } else {
            builder = new NotificationCompat.Builder(context);
        }
        return builder.setContentTitle("This is long content title")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("This is long content text. This is long content text. This is long content text. This is long content text."))
//                    .setContentText("This is long content text. This is long content text. This is long content text. This is long content text.")
                .setWhen(System.currentTimeMillis())
                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setSmallIcon(R.drawable.ic_launcher_foreground)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setContentIntent(pi)
                .setDefaults(Notification.DEFAULT_SOUND)
                .setAutoCancel(true)
                .build();
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private static String createNotificationChannel(String channelId, String channelName, NotificationManager manager) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
        manager.createNotificationChannel(channel);
        return channelId;
    }
}
