package com.example.project;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.FragmentActivity;
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
import java.util.HashMap;

public class newadapter extends Adapter<newadapter.ViewHolder> {
    Context c;
    String path,ud,percent;
ArrayList<po> list;
    DatabaseReference ref;
    String totalr,presentr,test;

Float k,iper;
    public newadapter( Context context, ArrayList<po> list, String u) {
        c=context;
        this.list=list;
        path=u;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ViewHolder v=new ViewHolder(LayoutInflater.from(c).inflate(R.layout.row2,parent,false));
        return v;
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, final int position) {
holder.t.setText(list.get(position).getName());
Float itotal=Float.parseFloat(list.get(position).getTotal());
Float ipresent=Float.parseFloat(list.get(position).getPresent());
        Log.i("iper", String.valueOf(itotal));
        Log.i("iper", String.valueOf(ipresent));
holder.to.setText( String.valueOf(itotal));
        holder.po.setText( String.valueOf(ipresent));
if(itotal==0){
    test= String.valueOf(Float.valueOf(0));

    Log.i("if", String.valueOf(iper));
}
else{
    iper=(ipresent/itotal)*100;
    float f =iper;
     test = String.format("%.02f", f);
    Log.i("else", String.valueOf(iper));
}
holder.p.setText(test+" %");
holder.d.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        ref= FirebaseDatabase.getInstance().getReference();
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

holder.b.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        AlertDialog.Builder a=new AlertDialog.Builder(c);
       a.setTitle("Enter your total number of classes");
        final EditText total=new EditText(c);
        total.setInputType(InputType.TYPE_CLASS_NUMBER);
        a.setView(total);
a.setPositiveButton("next", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        totalr=total.getText().toString().trim();
holder.to.setText(totalr);
        AlertDialog.Builder b=new AlertDialog.Builder(c);
   b.setTitle("Enter your presents");
        final EditText present=new EditText(c);

        present.setInputType(InputType.TYPE_CLASS_NUMBER);
        b.setView(present);
        b.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //saving to data base
                presentr=present.getText().toString().trim();
                holder.po.setText(presentr);
                Integer p=Integer.parseInt(presentr);
                Integer t=Integer.parseInt(totalr);
                if(p>t){
                    Toast.makeText(c,"enter correct values",Toast.LENGTH_LONG).show();
                }
                else{
final HashMap<String,Object>  map=new HashMap<>();
                ref=FirebaseDatabase.getInstance().getReference();
               Query query=ref.child(path).orderByChild("name").equalTo(list.get(position).getName());
               query.addListenerForSingleValueEvent(new ValueEventListener() {
                   @Override
                   public void onDataChange(@NonNull DataSnapshot snapshot) {
                       for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                           map.get("present");
                           Log.i("value", String.valueOf(map.get("present")));
                           map.put("present",presentr);
                           map.put("total",totalr);
                           Log.i("value", String.valueOf(map.get("present")));

                           dataSnapshot.getRef().updateChildren(map);
                       }

                   }

                   @Override
                   public void onCancelled(@NonNull DatabaseError error) {

                   }
               });
            }}
        });

        b.setNegativeButton("cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        b.show();

    }
});
a.setNegativeButton("canel", new DialogInterface.OnClickListener() {
    @Override
    public void onClick(DialogInterface dialog, int which) {
        dialog.dismiss();
    }
});
        a.show();
    }
});


        }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
       TextView t;
       Button b,d;
       TextView p,po,to;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            t=itemView.findViewById(R.id.subj);
            b=itemView.findViewById(R.id.button);
            d=itemView.findViewById(R.id.button1);
            to=itemView.findViewById(R.id.to);
            po=itemView.findViewById(R.id.pr);

            p=itemView.findViewById(R.id.percent);
        }
    }
}
