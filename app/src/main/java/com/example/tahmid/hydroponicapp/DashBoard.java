package com.example.tahmid.hydroponicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class DashBoard extends AppCompatActivity {


    private Button readings,statBtn;
    private DatabaseReference databaseReference;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dash_board);

        readings=findViewById(R.id.readId);
        statBtn=findViewById(R.id.statId);

        databaseReference=FirebaseDatabase.getInstance().getReference("Users");
        user=FirebaseAuth.getInstance().getCurrentUser();
        String email=user.getEmail();
        Toast.makeText(this, email, Toast.LENGTH_SHORT).show();



        readings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AnimatedReadings.class);
                startActivity(intent);
            }
        });

       try{
           statBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent=new Intent(getApplicationContext(),StatActivity.class);
                    startActivity(intent);
                }
            });
        }catch (Exception e){
           Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
       }

    }
}
