package com.example.chapter03.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chapter03.R;
import com.example.chapter03.entity.Fruit;

import java.util.List;

public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> mFruitList;

    public FruitAdapter(List<Fruit> fruitList) {
        this.mFruitList = fruitList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_fruit, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Fruit fruit = mFruitList.get(position);
        holder.iv_fruit_image.setImageResource(fruit.imageId);
        holder.tv_fruit_name.setText(fruit.name);
    }

    @Override
    public int getItemCount() {
        return mFruitList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_fruit_image;
        TextView tv_fruit_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_fruit_image = itemView.findViewById(R.id.iv_fruit_image);
            tv_fruit_name = itemView.findViewById(R.id.tv_fruit_name);
        }
    }
}
