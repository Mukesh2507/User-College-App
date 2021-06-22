package com.example.usercollegeapp.ui.notice;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.usercollegeapp.FullImageView;
import com.example.usercollegeapp.R;

import java.util.ArrayList;

public class NoticeAdapter extends RecyclerView.Adapter<NoticeAdapter.NoticeViewAdapter> {

  private Context context;
  private ArrayList<NoticeData>list;

    public NoticeAdapter(Context context, ArrayList<NoticeData> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull

    @Override
    public NoticeViewAdapter onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.newsfeed_item_layout,parent,false);
        return new NoticeViewAdapter(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  NoticeViewAdapter holder, int position) {
    //setting data
        //finding current item
        NoticeData currentItem = list.get(position);
        holder.noticeTitle.setText(currentItem.getTitle());
        holder.date.setText(currentItem.getData());
        holder.time.setText(currentItem.time);



        try {
            if (currentItem.getImage() !=null)
            Glide.with(context).load(currentItem.getImage()).into(holder.noticeImage);
        } catch (Exception e) {
            e.printStackTrace();
        }
          holder.noticeImage.setOnClickListener(new View.OnClickListener() {
              @Override
              public void onClick(View v) {
                  Intent intent = new Intent(context, FullImageView.class);
                  intent.putExtra("image",currentItem.getImage());
                  context.startActivity(intent);
              }
          });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class NoticeViewAdapter extends RecyclerView.ViewHolder {


        private TextView noticeTitle,date,time;
        private ImageView noticeImage;

        public NoticeViewAdapter(@NonNull  View itemView) {

            super(itemView);
            noticeTitle = itemView.findViewById(R.id.noticeTitle);
            date = itemView.findViewById(R.id.date);
            time = itemView.findViewById(R.id.time);
            noticeImage =itemView.findViewById(R.id.noticeImage);


        }
    }
}
