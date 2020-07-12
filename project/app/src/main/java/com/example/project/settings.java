package com.example.project;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SearchRecentSuggestionsProvider;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;

import com.google.firebase.auth.FirebaseAuth;

import java.util.Calendar;

public class settings extends AppCompatActivity {
    Switch s;
    Button a;
    FirebaseAuth auth;
    String h,m;
    TextView d;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        auth = FirebaseAuth.getInstance();
        s = findViewById(R.id.notify);
        a = findViewById(R.id.a);
        d = findViewById(R.id.w);
        s.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    a.setVisibility(View.VISIBLE);
                    d.setVisibility(View.VISIBLE);
                    Log.i("app", "swicth is clecked");
                } else {
                    a.setVisibility(View.GONE);
                    d.setVisibility(View.GONE);
                    //cancel the notification
                    AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                    Intent myIntent = new Intent(getApplicationContext(), MyReceiver2.class);
                    PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, myIntent, 0);
                      manager.cancel(pendingIntent);
                }
            }
        });

    }

    public void time(View view) {
        TimePickerDialog a=new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                if(hourOfDay<10){
                    h="0"+hourOfDay;
                    // Log.i("hour",h);
                }else {
                    h = "" + hourOfDay;
                } if(minute<10){
                    m="0"+minute;
                    //Log.i("hour",h);
                }else {
                    m= "" + minute;
                }
                d.setText("notification time is set at"+" "+h+" : "+m);
                Calendar c=Calendar.getInstance();
                c.set(Calendar.HOUR_OF_DAY,hourOfDay);
                c.set(Calendar.MINUTE,minute);
                c.set(Calendar.SECOND,0);
                AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
                Intent myIntent = new Intent(getApplicationContext(), MyReceiver2.class);
                PendingIntent pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, myIntent, 0);
                manager.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(), pendingIntent);

            }
        },0,0,true);
        a.show();
    }
@RequiresApi(api = Build.VERSION_CODES.KITKAT)

    public void signout(View view) {
        auth.signOut();
        startActivity(new Intent(getApplicationContext(),opening.class));
        finish();
    }
}