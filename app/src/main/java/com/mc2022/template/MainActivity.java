package com.mc2022.template;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements SensorEventListener {

    TextView lightTxt, lightvalTxt, timestampTxt;
    TextView linearaccvalTxt, linearaccvalTxt2, linearaccvalTxt3, linearacctimeTxt;
    TextView gyro_X, gyro_Y, gyro_Z, gyrotimeTxt;
    TextView provalTxt,protimeTxt;
    TextView tempvaltxt, temptimeTxt;
    TextView lat, log;
    TextView checkSt;
    TextView txt;
    TextView magField_X, magField_Y, magField_Z, magFieldTxt;

    ToggleButton lightBtn, linearaccBtn, gyroBtn, proBtn, tempBtn, magFieldBtn, accBtn, flipBtn, toogleButton;
    Button btn1, btn2, latlogBtn, btn3;
    private static final String TAG = "MainActivity";
    private SensorManager sensorManager;
    private LocationManager locationManager;
    Sensor light, linear_acc, gyro, pro, temp, magField, accelerometer, light1;
    private final float lightThreshold = 1;
    public List<String> arrayList = new ArrayList<>();
    public List<Float> arrayList2 = new ArrayList<>();
    LocationListener mlocListener;
    private String finalAddress = "";
    private String cityName = "";

   // stationary
    List<List<Float>> l = new ArrayList<>();
    List<Float> l2 = new ArrayList<>();

    public float motionlast= 0.0F;



    // gps add a place
    private String address = "";
    private String city = "";
    private String state = "";
    private String country = "";
    private String pincode = "";
    private String Completeaddress = "";

    public ArrayList<List<Double>> list2 = new ArrayList<>();
    double dist_calc;
    double latitude, longitude;

    // upward & downward
    private Float f1, f2, f3;
    private Float lightData;

    //
    float f4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // light sensor
        lightTxt = findViewById(R.id.lightTxt);
        lightvalTxt = findViewById(R.id.lightvalTxt);
        lightBtn = findViewById(R.id.lightBtn);
        timestampTxt = findViewById(R.id.timestampTxt);

        // linear acceleration
        linearaccvalTxt = findViewById(R.id.linearaccvalTxt);
        linearaccvalTxt2 = findViewById(R.id.linearaccvalTxt2);
        linearaccvalTxt3 = findViewById(R.id.linearaccvalTxt3);
        linearacctimeTxt = findViewById(R.id.linearacctimeTxt);
        linearaccBtn = findViewById(R.id.linearaccBtn);

        // Gyroscope sensor
        gyro_X = findViewById(R.id.gyro_X);
        gyro_Y = findViewById(R.id.gyro_Y);
        gyro_Z = findViewById(R.id.gyro_Z);
        gyrotimeTxt = findViewById(R.id.gyrotimeTxt);
        gyroBtn = findViewById(R.id.gyroBtn);

        //proximity sensor
        provalTxt = findViewById(R.id.provalTxt);
        protimeTxt = findViewById(R.id.protimeTxt);
        proBtn = findViewById(R.id.proBtn);

        //pressure sensor
        tempvaltxt = findViewById(R.id.tempvalTxt);
        temptimeTxt = findViewById(R.id.temptimeTxt);
        tempBtn = findViewById(R.id.tempBtn);

        // magnetic field
        magField_X = findViewById(R.id.magFieldX);
        magField_Y = findViewById(R.id.magFieldY);
        magField_Z = findViewById(R.id.magFieldZ);
        magFieldTxt = findViewById(R.id.magFieldtimeTxt);
        magFieldBtn = findViewById(R.id.magFieldBtn);

        //button
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);

        // gps
        lat = findViewById(R.id.lat);
        log = findViewById(R.id.log);
        latlogBtn = findViewById(R.id.latlogBtn);

        // accelerometer
        accBtn = findViewById(R.id.accBtn);

        // flip
        flipBtn = findViewById(R.id.flipBtn);

        // stationary
        checkSt = findViewById(R.id.checkSt);

        txt = findViewById(R.id.txt);
        toogleButton = findViewById(R.id.toggleButton);

        // proximity line chart
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent i = new Intent(MainActivity.this, ProximityLineChart.class);
                i.putExtra("proChart", (Serializable) arrayList);
                startActivity(i);
            }
        });

        // linear acceleration line chart
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent i = new Intent(MainActivity.this, LinearAccLineChart.class);
                i.putExtra("linearAccChart", (Serializable) arrayList2);
                startActivity(i);
            }
        });

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                Intent i = new Intent(MainActivity.this, LocationActivity.class);
                i.putExtra("location", (Serializable) list2);
                startActivity(i);
            }
        });

        // gps
        latlogBtn.setOnClickListener(new View.OnClickListener() {
            class MyLocationListener implements LocationListener {
                @Override
                public void onLocationChanged (@NonNull Location location) {

                    lat.setText(String.valueOf(location.getLatitude()));
                    log.setText(String.valueOf(location.getLongitude()));

                    Geocoder gcd = new Geocoder(MainActivity.this.getBaseContext(), Locale.getDefault());
                    List<Address> addresses;

                    try {
                        addresses = gcd.getFromLocation(location.getLatitude(), location.getLongitude(), 1);

                        address = addresses.get(0).getAddressLine(0);
                        if (addresses.get(0).getFeatureName() != address) {
                            Completeaddress = addresses.get(0).getFeatureName();
                        }
                        city = addresses.get(0).getLocality();
                        state = addresses.get(0).getAdminArea();
                        country = addresses.get(0).getCountryName();
                        pincode = addresses.get(0).getPostalCode();



                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    finalAddress = address + ", " + city + ", " + state + ", " + country + "- " + pincode + "\n";
                    cityName = city;
                    Log.i(TAG, "onLocationChanged: "+finalAddress+cityName);
                    AddLocationData();
                }

                @Override
                public void onProviderEnabled (@NonNull String provider) {
                    LocationListener.super.onProviderEnabled(provider);
                }

                @Override
                public void onProviderDisabled (@NonNull String provider) {
                    LocationListener.super.onProviderDisabled(provider);
                }
            }

            @Override
            public void onClick (View view) {

                if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 1);
                }
                locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
                mlocListener = new MyLocationListener();
                locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10, 1, mlocListener);
            }
        });

        sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

        lightBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
                    if(light != null){
                        sensorManager.registerListener(MainActivity.this, light, SensorManager.SENSOR_DELAY_NORMAL);
                        Toast.makeText(MainActivity.this, "Light Sensor Started", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        Log.i(TAG, "Not supported");
                    }
                }

                else{
                    Stop(1);

                    Stop(6);
                    Stop(5);
                    Stop(4);
                    Stop(3);
                    Stop(2);
                    sensorManager.unregisterListener(MainActivity.this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT));
                    lightvalTxt.setText("0");
                    timestampTxt.setText("0");
                    Toast.makeText(MainActivity.this, "Light sensor stopped", Toast.LENGTH_SHORT).show();
                }
            }

        });

        linearaccBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    linear_acc = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
                    if(linear_acc != null){
                        sensorManager.registerListener(MainActivity.this, linear_acc, SensorManager.SENSOR_DELAY_NORMAL);
                        Toast.makeText(MainActivity.this, "Linear Acceleration Sensor Started", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        Log.i(TAG, "Not supported");
                    }
                }

                else{
                    Stop(2);
                    linearaccvalTxt.setText("0");
                    linearaccvalTxt2.setText("0");
                    linearaccvalTxt3.setText("0");
                    linearacctimeTxt.setText("0");
                    Toast.makeText(MainActivity.this, "Linear Acceleration sensor stopped", Toast.LENGTH_SHORT).show();
                }
            }

        });


        gyroBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
                    if(gyro != null){
                        sensorManager.registerListener(MainActivity.this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
                        Toast.makeText(MainActivity.this, "Gyroscope Sensor Started", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        Log.i(TAG, "Not supported");
                    }
                }

                else{
                    Stop(3);
                    gyro_X.setText("0");
                    gyro_Y.setText("0");
                    gyro_Z.setText("0");
                    gyrotimeTxt.setText("0");
                    Toast.makeText(MainActivity.this, "Gyroscope sensor stopped", Toast.LENGTH_SHORT).show();
                }
            }

        });

        proBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    pro = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
                    if(pro != null){
                        sensorManager.registerListener(MainActivity.this, pro, SensorManager.SENSOR_DELAY_NORMAL);
                        Toast.makeText(MainActivity.this, "Proximity Sensor Started", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        Log.i(TAG, "Not supported");
                    }
                }

                else{
                    Stop(4);
                    provalTxt.setText("0");
                    protimeTxt.setText("0");
                    Toast.makeText(MainActivity.this, "Proximity sensor stopped", Toast.LENGTH_SHORT).show();
                }
            }

        });

        tempBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    temp = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
                    if(temp != null){
                        sensorManager.registerListener(MainActivity.this, temp, SensorManager.SENSOR_DELAY_NORMAL);
                        Toast.makeText(MainActivity.this, "Temperature Sensor Started", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        Log.i(TAG, "Not supported");
                    }
                }

                else{
                    Stop(5);
                    tempvaltxt.setText("0");
                    temptimeTxt.setText("0");
                    Toast.makeText(MainActivity.this, "Temperature sensor stopped", Toast.LENGTH_SHORT).show();
                }
            }

        });

        magFieldBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    magField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
                    if(magField != null){
                        sensorManager.registerListener(MainActivity.this, magField, SensorManager.SENSOR_DELAY_NORMAL);
                        Toast.makeText(MainActivity.this, "Gyroscope Sensor Started", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        Log.i(TAG, "Not supported");
                    }
                }

                else{
                    Stop(6);
                    magField_X.setText("0");
                    magField_Y.setText("0");
                    magField_Z.setText("0");
                    magFieldTxt.setText("0");
                    Toast.makeText(MainActivity.this, "Magnetic Field sensor stopped", Toast.LENGTH_SHORT).show();
                }
            }

        });

        accBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                    if(accelerometer != null){
                        sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                        checkSt.setText("Device Ready!");
                        Toast.makeText(MainActivity.this, "Accelerometer Sensor Started", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        Log.i(TAG, "Not supported");
                    }
                }

                else{
                    Stop(7);

                    Stop(6);
                    Stop(5);
                    Stop(4);
                    Stop(3);
                    Stop(2);
                    Stop(1);
                    checkSt.setText("Device Ready!");
                    Toast.makeText(MainActivity.this, "Accelerometer sensor stopped", Toast.LENGTH_SHORT).show();

                }
            }

        });



        flipBtn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged (CompoundButton compoundButton, boolean b) {
                if(b){
                    accelerometer = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
                    if(accelerometer != null){
                        sensorManager.registerListener(MainActivity.this, accelerometer, SensorManager.SENSOR_DELAY_NORMAL);
                        Toast.makeText(MainActivity.this, "Accelerometer Sensor Started", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        Log.i(TAG, "Not supported");
                    }
                }

                else{
                    Stop(7);

                    checkSt.setText("Device Ready!");
                }
            }
        });



        toogleButton.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if(b){
                    light1 = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
                    if(light1 != null){
                        sensorManager.registerListener(MainActivity.this, light1, SensorManager.SENSOR_DELAY_NORMAL);
                        Toast.makeText(MainActivity.this, "Temperature Sensor Started", Toast.LENGTH_SHORT).show();
                    }

                    else{
                        Log.i(TAG, "Not supported");
                    }
                }

                else{
                    Stop(8);
                    txt.setText("0");
                    //temptimeTxt.setText("0");
                    Toast.makeText(MainActivity.this, "Temperature sensor stopped", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    private void AddLocationData ( ) {
        DatabaseClass7 myDb7 = DatabaseClass7.getInstance(this);

        gpsModel myModel7 = new gpsModel(finalAddress, cityName, lat.getText().toString(), log.getText().toString() );
        myDb7.getDao7().insert7(myModel7);

        Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

        List<gpsModel> myEntries = myDb7.getDao7().getAllData7();

        String output = "";
        System.out.println("GPS SENSOR TABLE");
        System.out.println("ID" + " " + "ADDRESS"+ " " + "CITY_NAME" + "\n");
        for(gpsModel m : myEntries)
        {
            output += ( m.getId() + " " +m.getAddress() + " " +m.getName()+ " "+ m.getLat()+ " "+ m.getLog() +"\n");
            double a = Double.parseDouble(m.getLat());
            double b = Double.parseDouble(m.getLog());
            List<Double> list1 = new ArrayList<>();
            list1.clear();
            list1.add(Double.valueOf(m.getId()));
            dist_calc = calculate_distance(latitude, longitude, a, b);
            list1.add(dist_calc);
            list2.add(list1);

        }

        Log.i("My Output : ",output);
    }

    private double calculate_distance (double latitude, double longitude, double lat, double log) {
        int radius = 6371;
        double dlat = deg2rad(lat - latitude);
        double dlog = deg2rad(log - longitude);
        double d1 = Math.sin(dlat/2) * Math.sin(deg2rad(dlat/2)) +  Math.cos(latitude) * Math.cos(deg2rad(lat)) *
                Math.sin(dlog/2) * Math.sin(deg2rad(dlog/2));
        double d2 = (2 *  Math.atan2(Math.sqrt(d1), Math.sqrt(1 - d1))) * radius;
        return d2;
    }

    private  double deg2rad(double val){
        double res = val * (Math.PI/180);
        return res;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        Sensor sensor = sensorEvent.sensor;
        if(sensor.getType() == Sensor.TYPE_LIGHT){
            Log.d(TAG, "Light" + sensorEvent.values[0]);
            lightvalTxt.setText(Float.toString(sensorEvent.values[0]));
            timestampTxt.setText(Float.toString(sensorEvent.timestamp));
            lightData = sensorEvent.values[0];
            AddLightData();
            switchModeOn();

        }

        if(sensor.getType() == Sensor.TYPE_LINEAR_ACCELERATION){
            Log.d(TAG, "Linear Acceleration" + sensorEvent.values[0]);
            linearaccvalTxt.setText(Float.toString(sensorEvent.values[0]));
            linearaccvalTxt2.setText(Float.toString(sensorEvent.values[1]));
            linearaccvalTxt3.setText(Float.toString(sensorEvent.values[2]));
            linearacctimeTxt.setText(Float.toString(sensorEvent.timestamp));
            AddLinearAccData();
        }

        else if(sensor.getType() == Sensor.TYPE_GYROSCOPE){
            Log.d(TAG, "Gyroscope" + sensorEvent.values[0]);
            gyro_X.setText(Float.toString(sensorEvent.values[0]));
            gyro_Y.setText(Float.toString(sensorEvent.values[1]));
            gyro_Z.setText(Float.toString(sensorEvent.values[2]));
            gyrotimeTxt.setText(Float.toString(sensorEvent.timestamp));
            AddGyroData();
        }

        else if(sensor.getType() == Sensor.TYPE_PROXIMITY){
            Log.d(TAG, "Proximity" + sensorEvent.values[0]);
            provalTxt.setText(Float.toString(sensorEvent.values[0]));
            protimeTxt.setText(Float.toString(sensorEvent.timestamp));
            f4 = sensorEvent.values[0];
            upwardMobile2();
            AddProximityData();
        }

        else if(sensor.getType() == Sensor.TYPE_PRESSURE){
            Log.d(TAG, "Temperature" + sensorEvent.values[0]);
            tempvaltxt.setText(Float.toString(sensorEvent.values[0]));
            temptimeTxt.setText(Float.toString(sensorEvent.timestamp));
            AddTemperatureData();
        }

        else if(sensor.getType() == Sensor.TYPE_MAGNETIC_FIELD){
            Log.d(TAG, "Magnetic Field" + sensorEvent.values[0]);
            magField_X.setText(Float.toString(sensorEvent.values[0]));
            magField_Y.setText(Float.toString(sensorEvent.values[1]));
            magField_Z.setText(Float.toString(sensorEvent.values[2]));
            magFieldTxt.setText(Float.toString(sensorEvent.timestamp));
            AddMAgFieldData();
        }

        else if(sensor.getType() == Sensor.TYPE_LIGHT){
            Log.d(TAG, "Temperature" + sensorEvent.values[0]);
            txt.setText(Float.toString(sensorEvent.values[0]));
            lightData = sensorEvent.values[0];

            AddLight2Data();
        }

        else if(sensor.getType() == Sensor.TYPE_ACCELEROMETER){
            //l2.clear();
            Log.i(TAG, "Accelerometer" + sensorEvent.values[0] + " " + sensorEvent.values[1] +" " + sensorEvent.values[2]);
            f1 = sensorEvent.values[0];
            f2 = sensorEvent.values[1];
            f3 = sensorEvent.values[2];

           // AddAccData();
            upwardMobile();

            // motion detection
            float motioncurrent = f1 + f2 + f3;
            if(motionlast!=motioncurrent) {
                checkSt.setText("Moving");
                motionlast=motioncurrent;
            }
            else
            {
                checkSt.setText("Not moving");
            }


        }
    }

    private void upwardMobile2 ( ) {
        if(f4 == 0.0){
            Stop(1);
            Stop(2);
            Stop(3);
            Stop(5);
            Stop(6);
        }

        else{
            magField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            temp = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            linear_acc = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
            light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

            if(magField != null || temp != null || pro != null || gyro != null || linear_acc != null || light != null){
                sensorManager.registerListener(MainActivity.this, magField, SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(MainActivity.this, temp, SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(MainActivity.this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(MainActivity.this, linear_acc, SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(MainActivity.this, light, SensorManager.SENSOR_DELAY_NORMAL);
            }
        }
    }

    private void AddLight2Data ( ) {
        DatabaseClass9 myDb9 = DatabaseClass9.getInstance(this);

        LightModel myModel9 = new LightModel(txt.getText().toString());
        myDb9.getDao9().insert9(myModel9);


        List<LightModel> myEntries = myDb9.getDao9().getAllData9();

        String output = "";
        System.out.println("Magnetic Field SENSOR TABLE");
        System.out.println("ID" + " " + "X"+ " " + "Y" + " " + " " + "Z" + " " + "Timestamp" + "\n");
        for(LightModel m : myEntries)
        {
            output += ( m.getId() + " " +m.getLight() + "\n");

        }

        Log.i("My Output : ",output);
    }

    private void AddAccData ( ) {
        DatabaseClass8 myDb8 = DatabaseClass8.getInstance(this);

        AccModel myModel8 = new AccModel(f1, f2, f3);
        myDb8.getDao8().insert8(myModel8);

        //Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

        List<AccModel> myEntries = myDb8.getDao8().getLastVal();
        int len = myEntries.size();
        String output = "";
        List<Float> op = new ArrayList<>();
        System.out.println("Accelerometer SENSOR TABLE");
        System.out.println("ID" + " " + "X"+ " " + "Y" + " " + " " + "Z" + " "  + "\n");

        for(AccModel m : myEntries)
        {
           //output += ( m.getId() + " " +m.getAcc_X(len - 1) + " " +m.getAcc_Y(len - 1)+" "+ m.getAcc_Z(len - 1)+ " " + "\n");
            op.add(m.getAcc_X(len-1));
            op.add(m.getAcc_Y(len-1));
            op.add(m.getAcc_Z(len-1));


        }


        Log.i("My Output : ", " "+ op.size() + " " +(op.get(0)-op.get(3))+ " "+(op.get(1)-op.get(4))+ " " +(op.get(2)-op.get(5)) );

      //  for(int i = 0; i <= op.size(); i ++){

            if(((op.get(0)-op.get(3)) >= 0 && (op.get(0)-op.get(3)) <= 0.8) && ((op.get(1)-op.get(4)) >= 0 && (op.get(1)-op.get(4)) <= 0.8) && ((op.get(2)-op.get(5)) >= 0 && (op.get(2)-op.get(5)) <= 0.8)){
                Log.i("XYZ", "Stable");
                checkSt.setText("Stationary");
            }

            else{
                Log.i("NOT XYZ", "Not stable");
                checkSt.setText("Not Stationary");
            }
       // }

    }


    private void AddMAgFieldData ( ) {
        DatabaseClass6 myDb6 = DatabaseClass6.getInstance(this);

        MagFieldModel myModel6 = new MagFieldModel(magField_X.getText().toString(),magField_Y.getText().toString() ,magField_Z.getText().toString(), magFieldTxt.getText().toString());
        myDb6.getDao6().insert6(myModel6);

        //Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

        List<MagFieldModel> myEntries = myDb6.getDao6().getAllData6();

        String output = "";
        System.out.println("Magnetic Field SENSOR TABLE");
        System.out.println("ID" + " " + "X"+ " " + "Y" + " " + " " + "Z" + " " + "Timestamp" + "\n");
        for(MagFieldModel m : myEntries)
        {
            output += ( m.getId() + " " +m.getMagField_X() + " " +m.getMagField_Y()+" "+ m.getMagField_Z()+" " + m.getTimestamp() + " "  + "\n");

        }

        Log.i("My Output : ",output);
    }

    private void AddTemperatureData ( ) {
        DatabaseClass5 myDb5 = DatabaseClass5.getInstance(this);

        TempModel myModel5 = new TempModel(tempvaltxt.getText().toString(), temptimeTxt.getText().toString());
        myDb5.getDao5().insert5(myModel5);

        //Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

        List<TempModel> myEntries = myDb5.getDao5().getAllData5();

        String output = "";
        System.out.println("TEMPERATURE SENSOR TABLE");
        System.out.println("ID" + " " + "TempData" + " " + "Timestamp" + "\n");
        for(TempModel m : myEntries)
        {
            output += ( m.getId() + " " +m.getTempData() + " " + m.getTimestamp() + " "  + "\n");

        }

        Log.i("My Output : ",output);
    }

    // proximity sensor
    private void AddProximityData ( ) {
        DatabaseClass4 myDb4 = DatabaseClass4.getInstance(this);

        ProModel myModel4 = new ProModel(provalTxt.getText().toString(), protimeTxt.getText().toString());
        myDb4.getDao4().insert4(myModel4);

        //Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();

        List<ProModel> myEntries = myDb4.getDao4().getAllData4();

        String output = "";
        System.out.println("PROXIMITY SENSOR TABLE");
        System.out.println("ID" + " " + "ProximityData" + " " + "Timestamp" + "\n");
        for(ProModel m : myEntries)
        {
            output += ( m.getId() + " " +m.getPro() + " " + m.getTimestamp() + " "  + "\n");

        }

        Log.i("My Output : ",output);
    }

    // gyroscope sensor
    private void AddGyroData ( ) {
        DatabaseClass3 myDb3 = DatabaseClass3.getInstance(this);

        GyroModel myModel3 = new GyroModel(gyro_X.getText().toString(),gyro_Y.getText().toString() ,gyro_Z.getText().toString(), gyrotimeTxt.getText().toString());
        myDb3.getDao3().insert3(myModel3);

        List<GyroModel> myEntries = myDb3.getDao3().getAllData3();

        String output = "";
        System.out.println("Gyroscope SENSOR TABLE");
        System.out.println("ID" + " " + "X"+ " " + "Y" + " " + " " + "Z" + " " + "Timestamp" + "\n");
        for(GyroModel m : myEntries)
        {
            output += ( m.getId() + " " +m.getGyro_X() + " " +m.getGyro_Y()+" "+ m.getGyro_Z()+" " + m.getTimestamp() + " "  + "\n");

        }

        Log.i("My Output : ",output);
    }

    // linear acceleration
    private void AddLinearAccData ( ) {
        DatabaseClass2 myDb2 = DatabaseClass2.getInstance(this);

        LinearAccModel myModel2 = new LinearAccModel(linearaccvalTxt.getText().toString(),linearaccvalTxt2.getText().toString() ,linearaccvalTxt3.getText().toString(), linearacctimeTxt.getText().toString());
        myDb2.getDao2().insert2(myModel2);

        List<LinearAccModel> myEntries = myDb2.getDao2().getAllData2();

        String output = "";
        System.out.println("LINEAR ACCELERATION SENSOR TABLE");
        System.out.println("ID" + " " + "X"+ " " + "Y" + " " + " " + "Z" + " " + "Timestamp" + "\n");
        for(LinearAccModel m : myEntries)
        {
            output += ( m.getId() + " " +m.getLinearAcc_X() + " " +m.getLinearAcc_Y()+" "+ m.getLinearAcc_Z()+" " + m.getTimestamp() + " "  + "\n");

        }

        Log.i("My Output : ",output);
    }


    // light table
    private void AddLightData () {
        DatabaseClass myDb = DatabaseClass.getInstance(this);

        UserModel myModel1 = new UserModel(lightvalTxt.getText().toString(), timestampTxt.getText().toString());
        myDb.getDao().insert(myModel1);

        List<UserModel> myEntries = myDb.getDao().getAllData();

        String output = "";
        System.out.println("LIGHT SENSOR TABLE");
        System.out.println("ID" + " " + "LightData" + " " + "Timestamp" + "\n");
        for(UserModel m : myEntries)
        {
            output += ( m.getId() + " " +m.getSensorData() + " " + m.getTimestamp() + " "  + "\n");

        }

        Log.i("My Output : ",output);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }

    protected  void  Stop(int i){
        if(i == 1){
            sensorManager.unregisterListener(MainActivity.this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT));
        }

        else if(i == 2){
            sensorManager.unregisterListener(MainActivity.this, sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION));
            LinearAccChart();
        }

        else if(i == 3){
            sensorManager.unregisterListener(MainActivity.this, sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE));
        }

        else if(i == 4){
            sensorManager.unregisterListener(MainActivity.this, sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY));
            ProChart();
        }

        else if(i == 5){
            sensorManager.unregisterListener(MainActivity.this, sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE));
        }

        else if(i == 6){
            sensorManager.unregisterListener(MainActivity.this, sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD));
        }

        else if(i == 7){
            sensorManager.unregisterListener(MainActivity.this, sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER));
        }

        else if(i == 8){
            sensorManager.unregisterListener(MainActivity.this, sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT));
        }
    }

    private void switchModeOn ( ) {
        if( lightData < 10  && lightData > 0){
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);

          /*  magField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            temp = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            pro = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            linear_acc = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

            if(magField != null || temp != null || pro != null || gyro != null || linear_acc != null){
                sensorManager.registerListener(MainActivity.this, magField, SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(MainActivity.this, temp, SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(MainActivity.this, pro, SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(MainActivity.this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(MainActivity.this, linear_acc, SensorManager.SENSOR_DELAY_NORMAL);
            }

           */

            Stop(6);
            Stop(5);
            Stop(4);
            Stop(3);
            Stop(2);

        }
        else{
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);


          /*  magField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
            temp = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            pro = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
            gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
            linear_acc = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);

            if(magField != null || temp != null || pro != null || gyro != null || linear_acc != null){
                sensorManager.registerListener(MainActivity.this, magField, SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(MainActivity.this, temp, SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(MainActivity.this, pro, SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(MainActivity.this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
                sensorManager.registerListener(MainActivity.this, linear_acc, SensorManager.SENSOR_DELAY_NORMAL);
            }

           */

           /* Stop(6);
            Stop(5);
            Stop(4);
            Stop(3);
            Stop(2);

            */

        }
    }

    private void upwardMobile ( ) {
       if(f3 >= 9.0 && f3 <= 10.0){
           //Log.i("U&D", "Mobile is in upward direction");

           magField = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);
           temp = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
           pro = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
           gyro = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
           linear_acc = sensorManager.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);
           light = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

           if(magField != null || temp != null || pro != null || gyro != null || linear_acc != null || light != null){
               sensorManager.registerListener(MainActivity.this, magField, SensorManager.SENSOR_DELAY_NORMAL);
               sensorManager.registerListener(MainActivity.this, temp, SensorManager.SENSOR_DELAY_NORMAL);
               sensorManager.registerListener(MainActivity.this, pro, SensorManager.SENSOR_DELAY_NORMAL);
               sensorManager.registerListener(MainActivity.this, gyro, SensorManager.SENSOR_DELAY_NORMAL);
               sensorManager.registerListener(MainActivity.this, linear_acc, SensorManager.SENSOR_DELAY_NORMAL);
               sensorManager.registerListener(MainActivity.this, light, SensorManager.SENSOR_DELAY_NORMAL);
           }

       }

       else if(f3 <= -9.0 && f3 >= -10.0){
           //Log.i("U&D", "Mobile is in downward direction");
           Stop(6);
           Stop(5);
           Stop(4);
           Stop(3);
           Stop(2);
           Stop(1);
       }

       else{
           Log.i("U&D3", "Mobile is neither in upward nor downward direction");
          /* Stop(6);
           Stop(5);
           Stop(4);
           Stop(3);
           Stop(2);
           Stop(1);

           */
       }
    }

    private void LinearAccChart ( ) {
        DatabaseClass2 myDb2 = DatabaseClass2.getInstance(this);
        List<LinearAccModel> myEntries = myDb2.getDao2().LinearAccLineChart();

        for(LinearAccModel m : myEntries)
        {
            Float s =Float.parseFloat(m.getLinearAcc_X())+ Float.parseFloat(m.getLinearAcc_Y())+Float.parseFloat(m.getLinearAcc_Z());
            Float f = s/3;
            arrayList2.add(f);
        }

    }

    private void ProChart(){
        DatabaseClass4 mydb = DatabaseClass4.getInstance(this);
        List<ProModel> proModel = mydb.getDao4().ProLineChart();
        for(ProModel m : proModel)
        {
            arrayList.add(String.valueOf(m.getPro()));

        }
    }

    @Override
    public void onStart() {
        super.onStart();

        Log.i("start", "Activity 1 onStart()");
    }

    @Override
    public void onPause() {
        super.onPause();

        Stop(1);
        Stop(2);
        Stop(3);
        Stop(4);
        Stop(5);
        Stop(6);
        Stop(7);
        Stop(8);

        Log.i("pause", "Activity 1 onPause()");

    }

    @Override
    public void onResume() {
        super.onResume();


        Log.i("resume", "Activity 1 onResume()");
    }

    @Override
    public void onStop() {
        super.onStop();



        Log.i("stop", "Activity 1 onStop()");
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("destroy", "Activity 1 onDestroy()");
    }
}