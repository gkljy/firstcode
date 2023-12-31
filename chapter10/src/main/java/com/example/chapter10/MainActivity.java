package com.example.chapter10;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private MyService.DownloadBinder downloadBinder;

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d("MyService", "onServiceConnected executed");
            downloadBinder = (MyService.DownloadBinder) service;
            downloadBinder.startDownload();
            downloadBinder.getProgress();
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d("MyService", "Unbind");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_start_service = findViewById(R.id.btn_start_service);
        Button btn_stop_service = findViewById(R.id.btn_stop_service);
        Button btn_bind_service = findViewById(R.id.btn_bind_service);
        Button btn_unbind_service = findViewById(R.id.btn_unbind_service);
        btn_start_service.setOnClickListener(this);
        btn_stop_service.setOnClickListener(this);
        btn_bind_service.setOnClickListener(this);
        btn_unbind_service.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.btn_start_service) {
            Intent startIntent = new Intent(this, MyService.class);
            startService(startIntent);
        } else if (v.getId() == R.id.btn_stop_service) {
            Intent stopIntent = new Intent(this, MyService.class);
            stopService(stopIntent);
        } else if (v.getId() == R.id.btn_bind_service) {
            Intent bindIntent = new Intent(this, MyService.class);
            bindService(bindIntent, connection, BIND_AUTO_CREATE);
        } else if (v.getId() == R.id.btn_unbind_service) {
            unbindService(connection);
        }
    }
}