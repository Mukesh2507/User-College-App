package com.example.usercollegeapp.ui.gallery;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.usercollegeapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class GalleryFragment extends Fragment {
   RecyclerView convocationRecycler,othersRecycler;
   GalleryAdapter  adapter;

   DatabaseReference reference;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_gallery, container, false);

        convocationRecycler = view.findViewById(R.id.convactionRecycler);
        othersRecycler = view.findViewById(R.id.othersRecycler);


        reference = FirebaseDatabase.getInstance().getReference("Gallery");
    getConvoImage();
    getOtherImage();
        return view;
    }

    private void getConvoImage() {
        reference.child("Convocation").addValueEventListener(new ValueEventListener() {
            ArrayList<String> imageList = new ArrayList<>();
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                    String data = String.valueOf(snapshot.getValue());
                    imageList.add(data);
                }
                adapter = new GalleryAdapter(getContext(),imageList);
                convocationRecycler.setLayoutManager(new GridLayoutManager(getContext(),5));
                convocationRecycler.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });



    }


    private  void getOtherImage(){
         reference.child("Other Events").addValueEventListener(new ValueEventListener() {
             ArrayList<String> imageList = new ArrayList<>();
             @Override
             public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    for (DataSnapshot snapshot : dataSnapshot.getChildren()){
                        String data = (String) snapshot.getValue();
                        imageList.add(data);
                    }
                    adapter = new GalleryAdapter(getContext(),imageList);
                    othersRecycler.setLayoutManager(new GridLayoutManager(getContext(),5));
                    othersRecycler.setAdapter(adapter);
             }

             @Override
             public void onCancelled(@NonNull  DatabaseError error) {

             }
         });


    }
}