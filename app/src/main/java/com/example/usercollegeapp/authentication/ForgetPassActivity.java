package com.example.usercollegeapp.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.usercollegeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassActivity extends AppCompatActivity {
    private Button forgetBtn;
    private EditText forEmail;
    private  String email;
    private FirebaseAuth auth;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_pass);
        auth = FirebaseAuth.getInstance();
        forEmail = findViewById(R.id.forEmail);
        forgetBtn = findViewById(R.id.forgetBtn);
        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
    }

    private void validateData() {
        email = forEmail.getText().toString();
        if (email.isEmpty()){
            forEmail.setError("Email required");
        }else{
            forgetPassword();
        }
    }

    private void forgetPassword() {
        auth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull  Task<Void> task) {
                 if (task.isSuccessful()){
                     Toast.makeText(ForgetPassActivity.this, "Check Your Email", Toast.LENGTH_SHORT).show();
                     startActivity(new Intent(ForgetPassActivity.this,LoginActivity.class));
                     finish();
                 }else{
                     Toast.makeText(ForgetPassActivity.this, "Error :" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                 }
            }
        });
    }
}