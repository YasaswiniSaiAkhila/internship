package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.android.material.tabs.TabLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class homeactivity extends AppCompatActivity {
ViewPager v;
TabLayout t;
Button a;
FirebaseAuth auth;
TextView l,overall;
String u,test;
Integer fp=0,ft=0;
DatabaseReference ref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homeactivity);
          v=findViewById(R.id.view);
          t=findViewById(R.id.tab);
          overall=findViewById(R.id.overall);
          auth=FirebaseAuth.getInstance();
          v.setAdapter(new MyAdapter(getSupportFragmentManager()));
t.setupWithViewPager(v);
u=auth.getUid();
        ref= FirebaseDatabase.getInstance().getReference(u);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                fp=0;ft=0;
                for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                    String present= (String) dataSnapshot.child("present").getValue();
                    String total= (String) dataSnapshot.child("total").getValue();
Log.i("present",present);
                    Log.i("total",total);
                    Integer p=Integer.parseInt(present);
                    Integer t=Integer.parseInt(total);
                    fp=fp+p;
                    ft=ft+t;
                }
                Log.i("p",String.valueOf(ft));
                if(ft==0){
                    test="0.0";
                }else {
                    float f = (fp * 100) / ft;
                    Log.i("p", String.valueOf(f));

                    test = String.format("%.02f", f);
                }
                overall.setText(test+" %");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
      @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case (R.id.settings):
                startActivity(new Intent(getApplicationContext(),settings.class));
        }
        return super.onOptionsItemSelected(item);
    }

    public class MyAdapter extends FragmentStatePagerAdapter{

        public MyAdapter(@NonNull FragmentManager fm) {
            super(fm);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:return new table();
                case 1:return new subject();

            }
            return null;
        }

        @Override
        public int getCount() {
            return 2;
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            switch (position){
                case 0:return "Time Table";
                case 1:return "subjects";
            }
            return super.getPageTitle(position);
        }
    }
   }
