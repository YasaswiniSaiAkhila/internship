package com.example.project;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;

public class adapter extends RecyclerView.Adapter<adapter.ViewHolder> {
    Context c;
    ArrayList<Pojo> list;
    String a;
    DatabaseReference ref;
    public adapter(Context applicationContext, ArrayList<Pojo> list, String u) {
        c=applicationContext;
        this.list=list;
        a=u;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        adapter.ViewHolder v=new adapter.ViewHolder(LayoutInflater.from(c).inflate(R.layout.row3,parent,false));


        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
        holder.t.setText(list.get(position).getName());
        holder.p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.v.setText("present");
                final HashMap<String,Object> map=new HashMap<>();
                ref= FirebaseDatabase.getInstance().getReference();
                Query query=ref.child(a).orderByChild("name").equalTo(list.get(position).getName());
                Log.i("value", "present");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                            Log.i("value", String.valueOf(dataSnapshot));
                            String present= (String) dataSnapshot.child("present").getValue();
                            String total= (String) dataSnapshot.child("total").getValue();
                            Log.i("value", present);
                            Integer presentr=Integer.parseInt(present);
                            Integer totalr=Integer.parseInt(total);
                            presentr+=1;
                            totalr+=1;
                            map.put("present",String.valueOf(presentr));
                            map.put("total",String.valueOf(totalr));
                              dataSnapshot.getRef().updateChildren(map);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        holder.a.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.v.setText("absent");
                final HashMap<String,Object> map=new HashMap<>();
                ref= FirebaseDatabase.getInstance().getReference();
                Query query=ref.child(a).orderByChild("name").equalTo(list.get(position).getName());
                Log.i("value", "present");

                query.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                            Log.i("value", String.valueOf(dataSnapshot));
                            String present= (String) dataSnapshot.child("present").getValue();
                            String total= (String) dataSnapshot.child("total").getValue();
                            Log.i("value", present);
                            Integer presentr=Integer.parseInt(present);
                            Integer totalr=Integer.parseInt(total);
                            totalr+=1;
                            map.put("present",String.valueOf(presentr));
                            map.put("total",String.valueOf(totalr));
                            dataSnapshot.getRef().updateChildren(map);
                        }

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        holder.c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.v.setText("cancelled");
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t,v;
        Button p,c,a;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t=itemView.findViewById(R.id.tex);
            v=itemView.findViewById(R.id.view);
            p=itemView.findViewById(R.id.present);
            a=itemView.findViewById(R.id.absent);
            c=itemView.findViewById(R.id.cancelled);
        }
    }
}
