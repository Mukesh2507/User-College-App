package com.example.usercollegeapp.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.usercollegeapp.MainActivity;
import com.example.usercollegeapp.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {
    private TextView openReg;
    private EditText logEmail,logPass;
    private Button login;
    private  String email,password;
    private FirebaseAuth auth;
    private  TextView openForgetPass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        openForgetPass = findViewById(R.id.openForgetPass);
        auth = FirebaseAuth.getInstance();
        openReg = findViewById(R.id.openReg);
        logEmail = findViewById(R.id.logEmail);
        logPass = findViewById(R.id.logPass);
        login = findViewById(R.id.login);
        openReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegister();
            }

        });
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateData();
            }
        });
        openForgetPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this,ForgetPassActivity.class));
                finish();
            }
        });
    }




    private void validateData() {
        email = logEmail.getText().toString();
        password = logPass.getText().toString();
        if (email.isEmpty() || password.isEmpty()){
            Toast.makeText(this, "Please provide email and password", Toast.LENGTH_SHORT).show();
        }else{
            loginUser();
        }
    }

    private void loginUser() {
        auth.signInWithEmailAndPassword(email ,password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull  Task<AuthResult> task) {
                         if (task.isSuccessful()){
                             openMain();
                         }else{
                             Toast.makeText(LoginActivity.this, "Eror :" +task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                         }
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull  Exception e) {
                Toast.makeText(LoginActivity.this, "Error" +e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void openMain() {
        startActivity(new Intent(LoginActivity.this, MainActivity.class));
        finish();
    }

    private void openRegister() {
        startActivity(new Intent(LoginActivity.this,RegisterActivity.class));
        finish();
    }

}