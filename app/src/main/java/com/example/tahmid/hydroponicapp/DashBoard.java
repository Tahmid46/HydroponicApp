package com.example.tahmid.hydroponicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashBoard extends AppCompatActivity {

    private CardView c1;
    private DatabaseReference databaseReference;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        c1=findViewById(R.id.measures);
        databaseReference=FirebaseDatabase.getInstance().getReference("Users");
        user=FirebaseAuth.getInstance().getCurrentUser();
        String email=user.getEmail();
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();

        c1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),SensorValues.class);
                startActivity(intent);
            }
        });

    }
}
