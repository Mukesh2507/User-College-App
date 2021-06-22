package com.example.usercollegeapp.ui.about;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.usercollegeapp.R;

import java.util.List;

public class BranchAdapter extends PagerAdapter{
    private Context context;
    private List<BranchModel> list;

    public BranchAdapter(Context context, List<BranchModel> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull  Object object) {
        return  view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull  ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.branch_item_layout,container,false);
        ImageView branchIcon;
        TextView branchTitle,branchDesc;

        branchIcon = view.findViewById(R.id.branchIcon);
        branchTitle = view.findViewById(R.id.branchTitle);
        branchDesc = view.findViewById(R.id.branchDesc);

        branchIcon.setImageResource(list.get(position).getImg());
        branchTitle.setText(list.get(position).getBranchTitle());
        branchDesc.setText(list.get(position).getBranchDesc());
        container.addView(view,0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, Object object) {
        container.removeView((View)object);
    }
}
