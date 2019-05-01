package com.example.tahmid.hydroponicapp;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import de.nitri.gauge.Gauge;
import me.itangqi.waveloadingview.WaveLoadingView;

public class AnimatedReadings extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseUser user;
    private TextView humtv,lighttv,phtv,temptv,wleveltv,wtemptv;
    private Thermometer amb_thermometer,w_thermometer;
    private float temperature;
    private Gauge gauge;

    private WaveLoadingView waveLoadingView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_animated_readings);

        humtv=findViewById(R.id.hId);
        lighttv=findViewById(R.id.lId);
        phtv=findViewById(R.id.ph);
        temptv=findViewById(R.id.aTempTid);
        wleveltv=findViewById(R.id.wLevel);
        wtemptv=findViewById(R.id.wTempId);

        amb_thermometer=findViewById(R.id.Athermometer);
        w_thermometer=findViewById(R.id.Wthermometer);
        gauge=findViewById(R.id.gauge);
        waveLoadingView=findViewById(R.id.wlv);

        user=FirebaseAuth.getInstance().getCurrentUser();
        databaseReference=FirebaseDatabase.getInstance().getReference("Users/"+user.getUid()+"/"+"Values");

        databaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String hum=dataSnapshot.child("hum").getValue().toString();
                String light=dataSnapshot.child("light").getValue().toString();
                String ph=dataSnapshot.child("ph").getValue().toString();
                String temp=dataSnapshot.child("temp").getValue().toString();
                String wlevel=dataSnapshot.child("wlevel").getValue().toString();
                String wtemp=dataSnapshot.child("wtemp").getValue().toString();

                humtv.setText(hum);
                lighttv.setText(light);
                phtv.setText(ph);
                temptv.setText(temp);
                wleveltv.setText(wlevel);
                wtemptv.setText(wtemp);

                amb_thermometer.setCurrentTemp(Float.parseFloat(temp));
                w_thermometer.setCurrentTemp(Float.parseFloat(wtemp));
                gauge.moveToValue(Float.parseFloat(hum));

                if(Float.parseFloat(wlevel)<50){
                    waveLoadingView.setBottomTitle(String.format("%d%%",Integer.parseInt(wlevel)));
                    waveLoadingView.setCenterTitle("");
                    waveLoadingView.setTopTitle("");
                }

                if(Float.parseFloat(wlevel)<80){
                    waveLoadingView.setBottomTitle("");
                    waveLoadingView.setCenterTitle(String.format("%d%%",Integer.parseInt(wlevel)));
                    waveLoadingView.setTopTitle("");
                }

                else{
                    waveLoadingView.setBottomTitle("");
                    waveLoadingView.setCenterTitle("");
                    waveLoadingView.setTopTitle(String.format("%d%%",Integer.parseInt(wlevel)));
                }

                Toast.makeText(getApplicationContext(), "Data fetched", Toast.LENGTH_SHORT).show();
                //  Toast.makeText(getApplicationContext(),hum+" "+light+" "+ph+" "+temp+" "+wlevel+" "+wtemp,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "Failed", Toast.LENGTH_SHORT).show();
            }
        });


    }
}
