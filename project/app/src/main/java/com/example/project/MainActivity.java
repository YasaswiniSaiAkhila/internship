package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {
EditText email1,pass1;
FirebaseAuth auth;
ProgressBar p;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email1=findViewById(R.id.mail1);
        pass1=findViewById(R.id.pass1);
        p=findViewById(R.id.progress);
        auth=FirebaseAuth.getInstance();
    }

    public void signup(View view) {
        startActivity(new Intent(MainActivity.this,register.class));
        finish();
    }

    public void login(View view) {
       String mail = email1.getText().toString().trim();
        String pass = pass1.getText().toString().trim();
        if(TextUtils.isEmpty(mail)){
            email1.setError("Email is required");
            return;
        }
        if(TextUtils.isEmpty(pass)){
            pass1.setError("password is required");
            return;
        }
        if(pass.length()<6){
            pass1.setError("password must be greater than or equal to 6 characters");
        }
        p.setVisibility(View.VISIBLE);
       auth.signInWithEmailAndPassword(mail,pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
           @Override
           public void onComplete(@NonNull Task<AuthResult> task) {
               if(task.isSuccessful()){
                   startActivity(new Intent(getApplicationContext(),homeactivity.class));
                   finish();
               }else{
                   Toast.makeText(getApplicationContext(),"please enter a valid email amd password",Toast.LENGTH_LONG).show();
                   p.setVisibility(View.INVISIBLE);

               }
           }
       });
    }


    public void forget(View view) {
        AlertDialog.Builder a=new AlertDialog.Builder(this);
        a.setTitle("Recover password");
       final EditText e=new EditText(this);
        e.setHint("Enter the email address");
        e.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        a.setView(e);
        a.setPositiveButton("send", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String mai=e.getText().toString().trim();
                p.setVisibility(View.VISIBLE);
                auth.sendPasswordResetEmail(mai).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if(task.isSuccessful()){
                            p.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(),"recovery mail sent",Toast.LENGTH_LONG).show();
                        }
                        else {
                            p.setVisibility(View.INVISIBLE);
                            Toast.makeText(getApplicationContext(),"recovery mail  not sent",Toast.LENGTH_LONG).show();

                        }
                    }
                });
            }
        });
        a.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        a.show();
       }
}
