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

public class LinearAccLineChart extends AppCompatActivity {

    LineChart lineChart;
    public ArrayList<Float> linearAccValues;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_linear_acc_line_chart);

        linearAccValues = (ArrayList<Float>) getIntent().getSerializableExtra("linearAccChart");

        lineChart = findViewById(R.id.linearAcclinechart);
        LineDataSet lineDataset2 =  new LineDataSet(dataValues1(), "Data Set 2");
        ArrayList<ILineDataSet> dataSets = new ArrayList<>();
        dataSets.add(lineDataset2);

        LineData data = new LineData(dataSets);
        lineChart.setData(data);
        lineChart.invalidate();

    }

    private ArrayList<Entry> dataValues1(){
        ArrayList<Entry> dataVals = new ArrayList<Entry>();
        dataVals.add(new Entry(0, Float.parseFloat(String.valueOf(linearAccValues.get(0)))));
        dataVals.add(new Entry(1, Float.parseFloat(String.valueOf(linearAccValues.get(1)))));
        dataVals.add(new Entry(2, Float.parseFloat(String.valueOf(linearAccValues.get(2)))));
        dataVals.add(new Entry(3, Float.parseFloat(String.valueOf(linearAccValues.get(3)))));
        dataVals.add(new Entry(4, Float.parseFloat(String.valueOf(linearAccValues.get(4)))));
        dataVals.add(new Entry(5, Float.parseFloat(String.valueOf(linearAccValues.get(5)))));
        dataVals.add(new Entry(6, Float.parseFloat(String.valueOf(linearAccValues.get(6)))));
        dataVals.add(new Entry(7, Float.parseFloat(String.valueOf(linearAccValues.get(7)))));
        dataVals.add(new Entry(8, Float.parseFloat(String.valueOf(linearAccValues.get(8)))));
        dataVals.add(new Entry(9, Float.parseFloat(String.valueOf(linearAccValues.get(9)))));

        Log.i("linearAccValues", String.valueOf(dataVals));
        return dataVals;
    }
    }
