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

    private FirebaseUser user;


    private ArrayList<SensorData>sd=new ArrayList<>();


    private BarChart chart;
    private ArrayList<BarEntry> NoOfEmp = new ArrayList<BarEntry>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_p_hstat);



        chart = findViewById(R.id.barchart);
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

                    float ph5=(float) sd.get(0).getPh();
                    float ph1=(float) sd.get(1).getPh();
                    float ph4=(float) sd.get(2).getPh();
                    float ph2=(float) sd.get(3).getPh();
                    float ph3=(float) sd.get(4).getPh();

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
       // BarData data = new BarData(year, bardataset);
        bardataset.setColors(ColorTemplate.COLORFUL_COLORS);
       // chart.setData(data);
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

    }

}
