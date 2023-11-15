package com.example.chapter08;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

public class SendNotificationActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_notification);
        text1 = findViewById(R.id.tv_text1);
        Button btn_send_notice = findViewById(R.id.btn_send_notice);
        btn_send_notice.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_send_notice) {
            Intent intent = new Intent(this, NotificationActivity.class);
            PendingIntent pi = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE);
            NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder;
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                String channelId = createNotificationChannel("ckn", "normalChannel", manager);
                builder = new NotificationCompat.Builder(this, channelId);
            } else {
                builder = new NotificationCompat.Builder(this);
            }
            Notification notification = builder.setContentTitle("This is long content title")
                    .setStyle(new NotificationCompat.BigTextStyle().bigText("This is long content text. This is long content text. This is long content text. This is long content text."))
//                    .setContentText("This is long content text. This is long content text. This is long content text. This is long content text.")
                    .setWhen(System.currentTimeMillis())
                    .setPriority(NotificationCompat.PRIORITY_MAX)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher))
                    .setContentIntent(pi)
                    .setDefaults(Notification.DEFAULT_SOUND)
                    .setAutoCancel(true)
                    .build();
            manager.notify(1, notification);
            Log.d("notification", "通知发送");
        }
    }

    // SDK>=26时必须创建通知通道
    @RequiresApi(api = Build.VERSION_CODES.O)
    private String createNotificationChannel(String channelId, String channelName, NotificationManager manager) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_DEFAULT);
        manager.createNotificationChannel(channel);
        return channelId;
    }
}