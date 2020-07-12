package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class opening extends AppCompatActivity {
FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_opening);
        auth=FirebaseAuth.getInstance();
        if(auth.getCurrentUser()!=null){
            startActivity(new Intent(getApplicationContext(),homeactivity.class));
            finish();
        }

    }

    public void login(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }

    public void register(View view) {

        startActivity(new Intent(getApplicationContext(),register.class));

    }
}
