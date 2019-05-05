package com.example.tahmid.hydroponicapp;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.utils.ColorTemplate;

import java.util.ArrayList;

public class NutrientStatActivity extends AppCompatActivity {

    private PieChart pieChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nutrient_stat);

        pieChart=findViewById(R.id.pieId);

        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);
        pieChart.setExtraOffsets(5,10,5,5);
        pieChart.setDragDecelerationFrictionCoef(0.99f);

        pieChart.setDrawHoleEnabled(true);
        pieChart.setHoleColor(Color.WHITE);
        pieChart.setTransparentCircleRadius(60f);

        ArrayList<PieEntry> yValues=new ArrayList<>();

        yValues.add(new PieEntry(11.02f,"Phosphate"));
        yValues.add(new PieEntry(22.68f,"Nitrate"));
        yValues.add(new PieEntry(17.83f,"Calcium"));
        yValues.add(new PieEntry(12.82f,"Sulfate"));
        yValues.add(new PieEntry(3.27f,"Iron"));
        yValues.add(new PieEntry(9.24f,"Manganese"));
        yValues.add(new PieEntry(6.07f,"Boric"));
        yValues.add(new PieEntry(5.02f,"Copper"));
        yValues.add(new PieEntry(2.02f,"Ammonium"));
        yValues.add(new PieEntry(5.03f,"Zinc"));

        pieChart.animateY(1000,Easing.EasingOption.EaseInOutCirc);

        PieDataSet dataSet=new PieDataSet(yValues,"Nutrients");
        dataSet.setSliceSpace(3f);
        dataSet.setSelectionShift(5f);
        dataSet.setColors(ColorTemplate.COLORFUL_COLORS);

        PieData data=new PieData(dataSet);
        data.setValueTextSize(15f);
        data.setValueTextColor(Color.BLUE);

        pieChart.setData(data);



    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left,R.anim.slide_out_right);

    }
}
