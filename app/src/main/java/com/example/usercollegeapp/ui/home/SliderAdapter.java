package com.example.usercollegeapp.ui.home;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.usercollegeapp.R;
import com.smarteist.autoimageslider.SliderViewAdapter;

public class SliderAdapter extends SliderViewAdapter<SliderAdapter.SliderViewHolder> {
   private int[] images;
   private String [] text;

    public SliderAdapter(int[] images, String[] text) {
        this.images = images;
        this.text = text;
    }

    //private String[] images;
    @Override
    public SliderViewHolder onCreateViewHolder(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.image_slider_layout_item,null);
        
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(SliderViewHolder viewHolder, int position) {
       viewHolder.imageView.setImageResource(images[position]);//here u have to use glide
        viewHolder.textView.setText(text[position]);
        viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    @Override
    public int getCount() {
        return images.length;
    }

    public class SliderViewHolder extends SliderViewAdapter.ViewHolder {
   private ImageView imageView;
   private TextView textView;

        public SliderViewHolder(View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.image);
            textView =itemView.findViewById(R.id.textDesc);
        }
    }
    
    
}
