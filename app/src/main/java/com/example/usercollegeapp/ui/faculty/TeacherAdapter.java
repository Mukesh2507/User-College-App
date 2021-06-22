package com.example.usercollegeapp.ui.faculty;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.usercollegeapp.R;

import java.util.List;

public class TeacherAdapter extends RecyclerView.Adapter<TeacherAdapter.TeacherViewAdapter> {
    private List<TeacherData> list; //adding teachers data into list
    private Context context;
    public TeacherAdapter(List<TeacherData> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull

    @Override
    public TeacherViewAdapter onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.faculty_item_layout,parent,false);
        return new TeacherViewAdapter(view);
    }

    @Override
    public void onBindViewHolder (@NonNull   TeacherViewAdapter holder, int position) {
               TeacherData item =list.get(position);//getting item from list
               holder.name.setText(item.getName());
               holder.email.setText(item.getEmail());
               holder.post.setText(item.getPost());

        try {
            Glide.with(context).load(item.getImage()).placeholder(R.drawable.profile).into(holder.imageView); //ctr+alt+t
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class  TeacherViewAdapter extends RecyclerView.ViewHolder {
        //faculty item layout
        private TextView name,email,post;
        private ImageView imageView;

        public TeacherViewAdapter(@NonNull  View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.teacherName);
            email = itemView.findViewById(R.id.teacherEmail);
            post = itemView.findViewById(R.id.teacherPost);
            imageView = itemView.findViewById(R.id.teacherImage);

        }
    }
}
