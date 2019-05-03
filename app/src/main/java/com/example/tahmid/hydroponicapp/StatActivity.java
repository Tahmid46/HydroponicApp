package com.example.tahmid.hydroponicapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StatActivity extends AppCompatActivity {

    private Button phStat;
    private Button airTempStat;
    private Button watertempStat;
    private Button lightStat;
    private Button humStat;
    private Button wlevelstat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stat);

        phStat=findViewById(R.id.phStatId);
        airTempStat=findViewById(R.id.AirTempStatId);
        watertempStat=findViewById(R.id.WaterTempStatId);
        lightStat=findViewById(R.id.lightStatId);
        humStat=findViewById(R.id.humStatId);
        wlevelstat=findViewById(R.id.WaterLevelStatId);

        phStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),pHStatActivity.class);
                startActivity(intent);
            }
        });

        airTempStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),AirTempStat.class);
                startActivity(intent);
            }
        });

        watertempStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),WaterTempStat.class);
                startActivity(intent);
            }
        });

        lightStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),LightIntensityStat.class);
                startActivity(intent);
            }
        });

        humStat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),HumidityStat.class);
                startActivity(intent);
            }
        });

        wlevelstat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(),WaterLevelStat.class);
                startActivity(intent);
            }
        });

        /////


    }
}
