package com.mc2022.template;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "gps")
public class gpsModel {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "Address")
    private String address;

    @ColumnInfo(name = "Name")
    private String name;

    @ColumnInfo(name = "Latitude")
    private String lat;

    @ColumnInfo(name = "Longitude")
    private String log;

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getAddress ( ) {
        return address;
    }

    public void setAddress (String address) {
        this.address = address;
    }

    public String getName ( ) {
        return name;
    }

    public void setName (String name) {
        this.name = name;
    }

    public gpsModel (String address, String name, String lat, String log) {
        this.address = address;
        this.name = name;
        this.lat = lat;
        this.log = log;
    }

    public String getLat ( ) {
        return lat;
    }

    public void setLat (String lat) {
        this.lat = lat;
    }

    public String getLog ( ) {
        return log;
    }

    public void setLog (String log) {
        this.log = log;
    }
}
