package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class Radar_Chart extends AppCompatActivity {

    private Button close;

    private RadarChart radarChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_radar__chart);

        Intent intent = getIntent();
        String[] label = intent.getStringArrayExtra("subjectName");

        close = (Button)findViewById(R.id.close);

        close.setOnClickListener(closeListener);

        radarChart = (RadarChart) findViewById(R.id.RadarChart);

        RadarDataSet dataSet = new RadarDataSet(dataValue(), "");
        dataSet.setColor(Color.BLUE);
        dataSet.setFillColor(Color.BLUE);
        dataSet.setDrawFilled(true);
        dataSet.setValueTextSize(15f);

        XAxis xAxis = radarChart.getXAxis();
        xAxis.setValueFormatter(new IndexAxisValueFormatter(label));
        xAxis.setTextSize(15f);


        YAxis yAxis = radarChart.getYAxis();
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);


        Description description = new Description();
        description.setText("各科成績雷達圖");


        boolean darkmode = intent.getBooleanExtra("darkmode", true);
        Log.d("darkmode", Boolean.toString(darkmode));
        if (darkmode) {
            dataSet.setColor(Color.YELLOW);
            dataSet.setFillColor(Color.YELLOW);
            dataSet.setValueTextColor(Color.WHITE);


            xAxis.setTextColor(Color.WHITE);
            xAxis.setAxisLineColor(Color.WHITE);

            yAxis.setTextColor(Color.WHITE);
            yAxis.setAxisLineColor(Color.WHITE);

            description.setTextColor(Color.WHITE);

            radarChart.setWebColor(Color.WHITE);

        }

        RadarData data = new RadarData();
        data.addDataSet(dataSet);

        radarChart.setData(data);
        radarChart.setDescription(description);
        radarChart.invalidate();

    }

    private ArrayList<RadarEntry> dataValue() {
        ArrayList<RadarEntry> dataVals = new ArrayList<RadarEntry>();
        Intent intent = getIntent();
        double[] values = intent.getDoubleArrayExtra("Score");
        for (int i = 0;i < values.length;i++) {
            dataVals.add(new RadarEntry((float)values[i]));
        }
        return dataVals;
    }


    //Close Button
    private Button.OnClickListener closeListener =
            new Button.OnClickListener() {
                public void onClick(View v) {
                    finish();
                }
            };

}
