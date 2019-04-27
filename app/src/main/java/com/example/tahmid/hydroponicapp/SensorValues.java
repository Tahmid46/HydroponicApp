package com.example.tahmid.hydroponicapp;

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

public class SensorValues extends AppCompatActivity {

    private DatabaseReference databaseReference;
    private FirebaseUser user;
    private TextView humtv,lighttv,phtv,temptv,wleveltv,wtemptv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor_values);

        humtv=findViewById(R.id.humId);
        lighttv=findViewById(R.id.lightId);
        phtv=findViewById(R.id.phId);
        temptv=findViewById(R.id.tempId);
        wleveltv=findViewById(R.id.wlevelId);
        wtemptv=findViewById(R.id.wtempId);

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
