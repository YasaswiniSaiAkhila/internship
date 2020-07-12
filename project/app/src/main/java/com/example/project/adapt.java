package com.example.project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.Adapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import static com.example.project.addsub.name;

public class adapt extends Adapter<adapt.ViewHolder> {
    Context c;
    String path;
    DatabaseReference ref;
    ArrayList<Pojo> list;
    public adapt(addsub addsub, ArrayList<Pojo> list, String m) {
        c=addsub;
        this.list=list;
        path=m;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder v=new ViewHolder(LayoutInflater.from(c).inflate(R.layout.row,parent,false));

        return (v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
holder.t.setText(list.get(position).getName());
holder.b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
           ref=FirebaseDatabase.getInstance().getReference();
           Query query=ref.child(path).orderByChild("name").equalTo(list.get(position).getName());
           query.addListenerForSingleValueEvent(new ValueEventListener() {
               @Override
               public void onDataChange(@NonNull DataSnapshot snapshot) {
                   for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                       dataSnapshot.getRef().removeValue();
                   }
               }

               @Override
               public void onCancelled(@NonNull DatabaseError error) {

               }
           });
    }
});
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView t;
        Button b;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t=itemView.findViewById(R.id.t);
            b=itemView.findViewById(R.id.b);
        }
    }
}
