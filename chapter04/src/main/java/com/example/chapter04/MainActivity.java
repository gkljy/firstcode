package com.example.chapter04;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("fragment", "new RightFragment() Start");
        RightFragment rightFragment = new RightFragment();
        Log.d("fragment", "new RightFragment() Finished");
        replaceFragment(rightFragment);
        findViewById(R.id.btn_check).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("findFragmentById", String.valueOf(getSupportFragmentManager().findFragmentById(R.id.right_layout) == null));
            }
        });

    }

    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        Log.d("findFragmentById", String.valueOf(fragmentManager.findFragmentById(R.id.right_layout) == null));
        transaction.replace(R.id.right_layout, fragment);
        Log.d("fragment", "fragment replaced");
        transaction.commit();
        Log.d("findFragmentById", String.valueOf(fragmentManager.findFragmentById(R.id.right_layout) == null));
    }
}