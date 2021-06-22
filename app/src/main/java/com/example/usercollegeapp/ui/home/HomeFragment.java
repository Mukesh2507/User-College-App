package com.example.usercollegeapp.ui.home;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.example.usercollegeapp.R;
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

public class HomeFragment extends Fragment {
    private int[] images;
    private String[] text;
    private SliderAdapter adapter;
    private SliderView sliderView;
  private ImageView map;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =inflater.inflate(R.layout.fragment_home, container, false);

        sliderView = view.findViewById(R.id.sliderView);
        images =new int[]{R.drawable.s1,R.drawable.s2,R.drawable.s3,R.drawable.s4,R.drawable.s5};
        text = new String[]{"Silveroak Campus", "Stars", "Industrial visit","Sports Events","Kabil"};
        adapter = new SliderAdapter(images, text);
        sliderView.setSliderAdapter(adapter);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.setIndicatorAnimation(IndicatorAnimationType.DROP);
        sliderView.startAutoCycle();
        map = view.findViewById(R.id.map);
        map.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMap();
            }
        });
        return view;
    }

    private void openMap() {
        Uri uri =Uri.parse("geo:0,0?q=Silveroak University Ahemadbad Gujarat India");
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        intent.setPackage("com.google.android.apps.maps");
        startActivity(intent);
    }
}

