package com.mc2022.template;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LocationActivity extends AppCompatActivity {

    public ArrayList<String> locValues;
    public String str1[] = new String[10];
    public String str2[] = new String[10];
    TextView txt1, txt2, txt3;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        txt1 = findViewById(R.id.txt1);
        txt2 = findViewById(R.id.txt2);
        txt3 = findViewById(R.id.txt3);

        Intent i = getIntent();
        locValues = (ArrayList<String>) getIntent().getSerializableExtra("location");

        DatabaseClass7 myDb7 = DatabaseClass7.getInstance(this);
        List<gpsModel> myEntries = myDb7.getDao7().getAllData7();

        txt1.setText("City:" + myEntries.get(0).getName() + " "+ "Address:"+ " "+ myEntries.get(0).getAddress());
        txt2.setText("City:" + myEntries.get(1).getName() + " "+ "Address:"+ " "+ myEntries.get(1).getAddress());
        txt3.setText("City:" + myEntries.get(2).getName() + " "+ "Address:"+ " "+ myEntries.get(2).getAddress());
    }
}