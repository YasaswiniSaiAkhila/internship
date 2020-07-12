package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class register extends AppCompatActivity {
EditText name,mail,pass1,pass2;
FirebaseAuth auth;
ProgressBar p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name=findViewById(R.id.rname);
        mail=findViewById(R.id.rmail);
        pass1=findViewById(R.id.rpass1);
        pass2=findViewById(R.id.rpass2);
        auth=FirebaseAuth.getInstance();
        p=findViewById(R.id.progress);
    }

    public void register(View view) {
        String email = mail.getText().toString().trim();
        String password1 = pass1.getText().toString().trim();
        String password2=pass2.getText().toString().trim();
        if(TextUtils.isEmpty(email)){
            mail.setError("Email is required");
            return;
        }
        if(TextUtils.isEmpty(password1)){
            pass1.setError("password is required");
            return;
        }

        if(TextUtils.isEmpty(password2)){
            pass1.setError("conform password is required");
            return;
        }
        if(password1.length()<6){
            pass1.setError("password must be greater than or equal to 6 characters");
        }
        if(password1.equals(password2)){

        }
        else{
            pass2.setError("password and conform password are different");
        }
        p.setVisibility(View.VISIBLE);
        auth.createUserWithEmailAndPassword(email,password1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    startActivity(new Intent(getApplicationContext(),homeactivity.class));
                    finish();
                }else{
                    Toast.makeText(getApplicationContext(),"error in creating account",Toast.LENGTH_LONG).show();
p.setVisibility(View.INVISIBLE);
                }
            }
        });

       }




    public void Log(View view) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
        finish();
    }
}
