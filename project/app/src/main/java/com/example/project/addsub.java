package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class addsub extends AppCompatActivity {
Button a;
String s,u,m;
FirebaseAuth auth;
int ft=1;
adapt adapte;
public static final String name="name";
ArrayList<Pojo> list;
RecyclerView rv;
DatabaseReference r1,r2,r3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsub);
    auth=FirebaseAuth.getInstance();
    s=getIntent().getStringExtra("path");
    u=auth.getUid();
    list=new ArrayList<>();
rv=findViewById(R.id.rc);
m=u+s;

r1=FirebaseDatabase.getInstance().getReference(m);

        r1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Pojo pojo=dataSnapshot.getValue(Pojo.class);
                    list.add(pojo);
                    adapte =new adapt(addsub.this,list,m);
                    rv.setAdapter(adapte);
                    rv.setLayoutManager(new LinearLayoutManager(addsub.this));
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

                Toast.makeText(getApplicationContext(),"value not added",Toast.LENGTH_SHORT).show();
            }
        });

      }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.adds,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.addsub):
                AlertDialog.Builder b=new AlertDialog.Builder(addsub.this);
                b.setTitle("Do you want to add a subject ?");
                final EditText e=new EditText(addsub.this);
                e.setHint("Enter the subject name");
                e.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                b.setView(e);
                b.setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        final String name=e.getText().toString().trim();
                        String id=r1.push().getKey();
                        Pojo p=new Pojo(name);
                        r1.child(id).setValue(p);
                        DatabaseReference r2=FirebaseDatabase.getInstance().getReference(u);
                        r2.orderByChild("name").equalTo(name).addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                if(snapshot.exists()){
                                    //already exits
                                }
                                else{
                                    po pd=new po(name,"0","0");
                                    r3=FirebaseDatabase.getInstance().getReference(u);
                                    String i=r3.push().getKey();
                                    r3.child(i).setValue(pd);
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                            }
                        });
                        Log.i("ft value", String.valueOf(ft));
                    }
                });
                b.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                b.show();


        }
        return super.onOptionsItemSelected(item);
    }


}
