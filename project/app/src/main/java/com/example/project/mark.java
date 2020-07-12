package com.example.project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.format.DateFormat;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;

public class mark extends AppCompatActivity {
    FirebaseAuth auth;
    String u,s,m;
    ArrayList<Pojo> list;
    DatabaseReference r1;
RecyclerView f;
adapter ad;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mark);
        auth=FirebaseAuth.getInstance();
        u=auth.getUid();
        list=new ArrayList<>();
f=findViewById(R.id.f);
        Calendar c = Calendar.getInstance();
        int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
        Log.i("day", String.valueOf(dayOfWeek));
        switch (dayOfWeek){
            case 1:
            case 2:s="monday";
            break;
            case 3:s="tuesday";
                break;
                case 4:s="wednesday";
                break;
                case 5:s="thursday";
                break;
                case 6:s="friday";
                break;
            case 7:s="saturday";
                break;
        }
        m=u+s;
        r1= FirebaseDatabase.getInstance().getReference(m);
        r1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    Pojo p= dataSnapshot.getValue(Pojo.class);
                    list.add(p);
                    f.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                    ad=new adapter(getApplicationContext(),list,u);
                    f.setAdapter(ad);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(),"value not added",Toast.LENGTH_SHORT).show();

            }
        });

    }
}
