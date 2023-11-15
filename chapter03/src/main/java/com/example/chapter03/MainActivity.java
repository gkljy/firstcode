package com.example.chapter03;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;

import com.example.chapter03.adapter.FruitAdapter;
import com.example.chapter03.entity.Fruit;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private List<Fruit> fruitList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initFruitLIst();
        RecyclerView recycler_view = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recycler_view.setLayoutManager(layoutManager);
        FruitAdapter fruitAdapter = new FruitAdapter(fruitList);
        recycler_view.setAdapter(fruitAdapter);
    }

    private void initFruitLIst() {
        for (int i = 0; i < 30; i++) {
            Fruit fruit = new Fruit();
            fruit.imageId = R.drawable.diqiu;
            fruit.name = "地球水果";
            fruitList.add(fruit);
        }
    }
}