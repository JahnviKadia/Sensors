package com.mc2022.template;

import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;

import java.util.ArrayList;

public class ProximityLineChart extends AppCompatActivity {

    LineChart lineChart;
    public ArrayList<String> proValues;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_proximity_line_chart);

        proValues = (ArrayList<String>) getIntent().getSerializableExtra("proChart");

        lineChart = findViewById(R.id.Prolinechart);
        LineDataSet lineDataset1 =  new LineDataSet(dataValues1(), "Data Set 1");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataset1);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate();

    }

    private ArrayList<Entry> dataValues1(){
        ArrayList<Entry> dataVals = new ArrayList<>();
        dataVals.add(new Entry(0, Float.parseFloat(proValues.get(0))));
        dataVals.add(new Entry(1, Float.parseFloat(proValues.get(1))));
        dataVals.add(new Entry(2, Float.parseFloat(proValues.get(2))));
        dataVals.add(new Entry(3, Float.parseFloat(proValues.get(3))));
        dataVals.add(new Entry(4, Float.parseFloat(proValues.get(4))));
        dataVals.add(new Entry(5, Float.parseFloat(proValues.get(5))));
        dataVals.add(new Entry(6, Float.parseFloat(proValues.get(6))));
        dataVals.add(new Entry(7, Float.parseFloat(proValues.get(7))));
        dataVals.add(new Entry(8, Float.parseFloat(proValues.get(8))));
        dataVals.add(new Entry(9, Float.parseFloat(proValues.get(9))));

        Log.i("Provalues", String.valueOf(dataVals));
        return dataVals;
    }
}