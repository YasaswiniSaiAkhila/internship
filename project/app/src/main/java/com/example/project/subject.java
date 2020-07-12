package com.example.project;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class subject extends Fragment {
DatabaseReference r;
String u;
FirebaseAuth a;
newadapter ada;
ArrayList<po> list;
RecyclerView f;
    public subject() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container,
                             Bundle savedInstanceState) {
         View v=inflater.inflate(R.layout.fragment_subject, container, false);
        a=FirebaseAuth.getInstance();

        u=a.getUid();
        list=new ArrayList<>();
        f=v.findViewById(R.id.display);
        r=FirebaseDatabase.getInstance().getReference(u);
        r.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              list.clear();
                for(DataSnapshot dataSnapshot:snapshot.getChildren()) {
                    po p= dataSnapshot.getValue(po.class);
                    list.add(p);
                    f.setLayoutManager(new LinearLayoutManager(getContext()));
                    ada=new newadapter(getActivity(),list,u);
                    f.setAdapter(ada);

                }
                }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getContext(),"value not added",Toast.LENGTH_SHORT).show();

            }
        });


        return v;
    }

}
