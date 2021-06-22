package com.example.usercollegeapp.ui.about;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.example.usercollegeapp.R;

import java.util.ArrayList;
import java.util.List;


public class AboutFragment extends Fragment {
    private ViewPager viewPager;
    private BranchAdapter adapter;
    private List<BranchModel> list;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_about, container, false);
        list = new ArrayList<>();
        list.add(new BranchModel(R.drawable.ic_baseline_computer_24, "Computer Science", "Computer science, " +
                "the study of computers and computing, " +
                "including their theoretical and algorithmic foundations, " +
                "hardware and software, " +
                "and their uses for processing information. " +
                "The discipline of computer science includes the study of algorithms and data structures," +
                " computer and network design, modeling data and information processes, " +
                "and artificial intelligence. Computer science draws some of its foundations from mathematics and engineering " +
                "and therefore incorporates techniques from areas such as queueing theory, " +
                "probability and statistics, and electronic circuit design. " +
                "Computer science also makes heavy use of hypothesis testing and experimentation " +
                "during the conceptualization, design, measurement, and refinement of new algorithms," +
                " information structures, and computer architectures.")
        );
        list.add(new BranchModel(R.drawable.ic_mechanical, "Mechanical Engineering", "Technically, mechanical engineering is the application of the principles and " +
                "problem-solving techniques of engineering from design to " +
                "manufacturing to the marketplace for any object Mechanical " +
                "engineers analyze their work using the principles of motion" +
                " energy and force—ensuring that designs function safely efficiently and reliably, all at a competitive cost"));
        adapter = new BranchAdapter(getContext(), list);
        viewPager = view.findViewById(R.id.viewPager);
        viewPager.setAdapter(adapter);
        ImageView imageView = view.findViewById(R.id.collegeImage);
        Glide.with(getContext()).load("https://firebasestorage.googleapis.com/v0/b/socet-app.appspot.com/o/first.jpg?alt=media&token=9ddf45ab-cd9f-4d20-9c09-c52ffe428947").into(imageView);
        return view;

    }
}