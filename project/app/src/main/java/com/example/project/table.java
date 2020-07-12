package com.example.project;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class table extends Fragment {
View view;
Button a;
TextView t1,t2,t3,t4,t5,t6;
    public table() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view=inflater.inflate(R.layout.fragment_table,container,false);
        t1=view.findViewById(R.id.mon);
        t2=view.findViewById(R.id.tue);
        t3=view.findViewById(R.id.wed);
        t4=view.findViewById(R.id.thu);
        t5=view.findViewById(R.id.fri);
        t6=view.findViewById(R.id.sat);
        t1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getContext(),addsub.class);
                String a= String.valueOf(v.getTag());
                i.putExtra("path",a);
                startActivity(i);
            }
        });
        t2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getContext(),addsub.class);
                String a= String.valueOf(v.getTag());
                i.putExtra("path",a);
                startActivity(i);
            }
        });
        t3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getContext(),addsub.class);
                String a= String.valueOf(v.getTag());
                i.putExtra("path",a);
                startActivity(i);
            }
        });
        t4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getContext(),addsub.class);
                String a= String.valueOf(v.getTag());
                i.putExtra("path",a);
                startActivity(i);
            }
        });
        t5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getContext(),addsub.class);
                String a= String.valueOf(v.getTag());
                i.putExtra("path",a);
                startActivity(i);
            }
        });
        t6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i= new Intent(getContext(),addsub.class);
                String a= String.valueOf(v.getTag());
                i.putExtra("path",a);
                startActivity(i);
            }
        });

        return view;

    }

}
