package com.mc2022.template;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "user")
public class UserModel {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "Data")
    private String sensorData;

    public String getTimestamp() {
        return timestamp;
    }

    @ColumnInfo(name = "Timestamp")
    private String timestamp;

    public UserModel(String sensorData, String timestamp){
           // this.id = id;
            this.sensorData = sensorData;
            this.timestamp = timestamp;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSensorData() {
        return sensorData;
    }

    public void setSensorData(String sensorData) {
        this.sensorData = sensorData;
    }

    public String getTimestamp(String time) {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


}
