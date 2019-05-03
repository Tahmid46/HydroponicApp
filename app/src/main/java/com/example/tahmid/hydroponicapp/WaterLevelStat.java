package com.example.tahmid.hydroponicapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class WaterLevelStat extends AppCompatActivity {

    private DatabaseReference databaseReference1;

    private FirebaseUser user;


    private ArrayList<SensorData> sd=new ArrayList<>();


    private BarChart chart;
    private ArrayList<BarEntry> NoOfEmp = new ArrayList<BarEntry>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_water_level_stat);

        chart = findViewById(R.id.barchart4);
        chart.getDescription().setEnabled(false);

        user=FirebaseAuth.getInstance().getCurrentUser();
        databaseReference1=FirebaseDatabase.getInstance().getReference("Users/"+user.getUid()+"/"+"Stat");

        //Getting stat value for last five hours
        databaseReference1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                try{

                    for(DataSnapshot data:dataSnapshot.getChildren()){
                        SensorData value=data.getValue(SensorData.class);
                        sd.add(value);
                        //Toast.makeText(getApplicationContext(),value.getPh().toString(),Toast.LENGTH_SHORT).show();

                    }

                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
                }


                try {

                    //

                    float ph5=(float) sd.get(0).getWlevel();
                    float ph1=(float) sd.get(1).getWlevel();
                    float ph4=(float) sd.get(2).getWlevel();
                    float ph2=(float) sd.get(3).getWlevel();
                    float ph3=(float) sd.get(4).getWlevel();

                    ArrayList<BarEntry> yVals=new ArrayList<>();

                    yVals.add(new BarEntry(0,ph1));
                    yVals.add(new BarEntry(1,ph2));
                    yVals.add(new BarEntry(2,ph3));
                    yVals.add(new BarEntry(3,ph4));
                    yVals.add(new BarEntry(4,ph5));

                    BarDataSet set=new BarDataSet(yVals,"First, Second, Third, Fourth, Fifth");
                    set.setColors(ColorTemplate.COLORFUL_COLORS);
                    set.setDrawValues(true);

                    BarData data=new BarData(set);

                    chart.setData(data);
                    chart.invalidate();
                    chart.animateY(5000);
                    chart.setFitBars(true);

                }
                catch (Exception e){
                    Toast.makeText(getApplicationContext(),e.getMessage().toString(),Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
