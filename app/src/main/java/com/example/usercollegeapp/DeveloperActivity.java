package com.example.usercollegeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;

public class DeveloperActivity extends AppCompatActivity {
    private LinearLayout developer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_developer);
        developer = findViewById(R.id.developer);
    }
}