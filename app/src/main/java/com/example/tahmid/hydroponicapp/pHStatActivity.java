package com.example.tahmid.hydroponicapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class pHStatActivity extends AppCompatActivity {

    private DatabaseReference databaseReference1;
    private DatabaseReference databaseReference2;
    private DatabaseReference databaseReference3;
    private DatabaseReference databaseReference4;
    private DatabaseReference databaseReference5;
    private FirebaseUser user;



    /*String hum1,light1,temp1,wlevel1,ph1,wtemp1;
    private String hum2,light2,temp2,wlevel2,ph2,wtemp2;
    private String hum3,light3,temp3,wlevel3,ph3,wtemp3;
    private String hum4,light4,temp4,wlevel4,ph4,wtemp4;
    private String hum5,light5,temp5,wlevel5,ph5,wtemp5;*/

    private BarChart chart;
    private ArrayList<BarEntry> NoOfEmp = new ArrayList<BarEntry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_hstat);

        chart = findViewById(R.id.barchart);

        ///////
        //final ArrayList<String>pHvalues=new ArrayList<>();


        user=FirebaseAuth.getInstance().getCurrentUser();
        databaseReference1=FirebaseDatabase.getInstance().getReference("Users/"+user.getUid()+"/"+"Stat/"+"First");
        databaseReference2=FirebaseDatabase.getInstance().getReference("Users/"+user.getUid()+"/"+"Stat/"+"Second");
        databaseReference3=FirebaseDatabase.getInstance().getReference("Users/"+user.getUid()+"/"+"Stat/"+"Third");
        databaseReference4=FirebaseDatabase.getInstance().getReference("Users/"+user.getUid()+"/"+"Stat/"+"Fourth");
        databaseReference5=FirebaseDatabase.getInstance().getReference("Users/"+user.getUid()+"/"+"Stat/"+"Fifth");

        //Getting stat value for last five hours
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               String hum1=dataSnapshot.child("hum").getValue().toString();
               String light1=dataSnapshot.child("light").getValue().toString();
               String ph1=dataSnapshot.child("ph").getValue().toString();
               String temp1=dataSnapshot.child("temp").getValue().toString();
               String wlevel1=dataSnapshot.child("wlevel").getValue().toString();
               String wtemp1=dataSnapshot.child("wtemp").getValue().toString();


                //NoOfEmp.add(new BarEntry(Float.parseFloat(ph1), 0));


                //Toast.makeText(getApplicationContext(),ph1,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String hum2=dataSnapshot.child("hum").getValue().toString();
                String light2=dataSnapshot.child("light").getValue().toString();
                String  ph2=dataSnapshot.child("ph").getValue().toString();
                String  temp2=dataSnapshot.child("temp").getValue().toString();
                String  wlevel2=dataSnapshot.child("wlevel").getValue().toString();
                String wtemp2=dataSnapshot.child("wtemp").getValue().toString();


               // NoOfEmp.add(new BarEntry(Float.parseFloat(ph2), 1));

               // Toast.makeText(getApplicationContext(),ph2,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference3.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String  hum3=dataSnapshot.child("hum").getValue().toString();
                String light3=dataSnapshot.child("light").getValue().toString();
                String  ph3=dataSnapshot.child("ph").getValue().toString();
                String temp3=dataSnapshot.child("temp").getValue().toString();
                String wlevel3=dataSnapshot.child("wlevel").getValue().toString();
                String wtemp3=dataSnapshot.child("wtemp").getValue().toString();


               // NoOfEmp.add(new BarEntry(Float.parseFloat(ph3), 2));

               // Toast.makeText(getApplicationContext(),ph3,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference4.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String hum4=dataSnapshot.child("hum").getValue().toString();
                String light4=dataSnapshot.child("light").getValue().toString();
                String  ph4=dataSnapshot.child("ph").getValue().toString();
                String temp4=dataSnapshot.child("temp").getValue().toString();
                String  wlevel4=dataSnapshot.child("wlevel").getValue().toString();
                String wtemp4=dataSnapshot.child("wtemp").getValue().toString();


                //NoOfEmp.add(new BarEntry(Float.parseFloat(ph4), 3));



                //Toast.makeText(getApplicationContext(),ph4,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        databaseReference5.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                String hum5=dataSnapshot.child("hum").getValue().toString();
                String light5=dataSnapshot.child("light").getValue().toString();
                String  ph5=dataSnapshot.child("ph").getValue().toString();
                String temp5=dataSnapshot.child("temp").getValue().toString();
                String wlevel5=dataSnapshot.child("wlevel").getValue().toString();
                String wtemp5=dataSnapshot.child("wtemp").getValue().toString();


               // NoOfEmp.add(new BarEntry(Float.parseFloat(ph5), 4));

               // Toast.makeText(getApplicationContext(),ph5,Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

       /* ArrayList<String> year = new ArrayList<String>();

        year.add("First");
        year.add("Second");
        year.add("Third");
        year.add("Fourth");
        year.add("Fifth");*/

       /* BarDataSet bardataset = new BarDataSet(NoOfEmp, "No Of Employee");
        chart.animateY(5000);
        BarData data = new BarData(year, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);*/


        //Function to create bar chart
        createBarChartpH();
        //Toast.makeText(this,ph1+"  "+ph2+"  "+ph3+"  "+ph4+"  "+ph5,Toast.LENGTH_LONG).show();

    }



    void createBarChartpH()
    {
        ArrayList<BarEntry> NoOfEmp = new ArrayList<BarEntry>();

        NoOfEmp.add(new BarEntry(6.2f, 0));
        NoOfEmp.add(new BarEntry(6.7f, 1));
        NoOfEmp.add(new BarEntry(6.8f, 2));
        NoOfEmp.add(new BarEntry(7.0f, 3));
        NoOfEmp.add(new BarEntry(6.4f, 4));


        ArrayList<String> year = new ArrayList<String>();

        year.add("First");
        year.add("Second");
        year.add("Third");
        year.add("Fourth");
        year.add("Fifth");

        BarDataSet bardataset = new BarDataSet(NoOfEmp, "No Of Employee");
        chart.animateY(5000);
        BarData data = new BarData(year, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
        chart.setData(data);
    }



}
