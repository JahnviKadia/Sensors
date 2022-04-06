package com.mc2022.template;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Gyro")
public class GyroModel {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "Gyro_X")
    private String gyro_X;

    @ColumnInfo(name = "Gyro_Y")
    private String gyro_Y;

    @ColumnInfo(name = "Gyro_Z")
    private String gyro_Z;

    @ColumnInfo(name = "Timestamp")
    private String timestamp;


    public GyroModel (String gyro_X, String gyro_Y, String gyro_Z, String timestamp) {
        this.gyro_X = gyro_X;
        this.gyro_Y = gyro_Y;
        this.gyro_Z = gyro_Z;
        this.timestamp = timestamp;
    }

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getGyro_X ( ) {
        return gyro_X;
    }

    public void setGyro_X (String gyro_X) {
        this.gyro_X = gyro_X;
    }

    public String getGyro_Y ( ) {
        return gyro_Y;
    }

    public void setGyro_Y (String gyro_Y) {
        this.gyro_Y = gyro_Y;
    }

    public String getGyro_Z ( ) {
        return gyro_Z;
    }

    public void setGyro_Z (String gyro_Z) {
        this.gyro_Z = gyro_Z;
    }

    public String getTimestamp ( ) {
        return timestamp;
    }

    public void setTimestamp (String timestamp) {
        this.timestamp = timestamp;
    }
}

