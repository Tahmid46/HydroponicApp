package com.example.tahmid.hydroponicapp;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class AirTempStat extends AppCompatActivity {

    private DatabaseReference databaseReference1;

    private FirebaseUser user;

    private ArrayList<SensorData> sd=new ArrayList<>();
    private LineChart mChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_air_temp_stat);

        user=FirebaseAuth.getInstance().getCurrentUser();
        databaseReference1=FirebaseDatabase.getInstance().getReference("Users/"+user.getUid()+"/"+"Stat");
        mChart=findViewById(R.id.linechar);

        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(true);

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


                    float t5=(float) sd.get(0).getTemp();
                    float t1=(float) sd.get(1).getTemp();
                    float t4=(float) sd.get(2).getTemp();
                    float t2=(float) sd.get(3).getTemp();
                    float t3=(float) sd.get(4).getTemp();

                    ArrayList<Entry> yValues=new ArrayList<>();

                    yValues.add(new Entry(0,t1));
                    yValues.add(new Entry(1,t2));
                    yValues.add(new Entry(2,t3));
                    yValues.add(new Entry(3,t4));
                    yValues.add(new Entry(4,t4));

                    LineDataSet set1=new LineDataSet(yValues,"Air Temp. Stats of last five hours");
                    set1.setFillAlpha(110);

                    set1.setColor(Color.RED);
                    set1.setLineWidth(3f);
                    set1.setValueTextSize(15f);
                    set1.setValueTextColor(Color.BLUE);

                    ArrayList<ILineDataSet> dataSets=new ArrayList<>();
                    dataSets.add(set1);

                    LineData data=new LineData(dataSets);

                    mChart.setData(data);
                    mChart.animateX(5000);

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
