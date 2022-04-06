package com.mc2022.template;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Temperature")
public class TempModel {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "TempData")
    private String tempData;

    @ColumnInfo(name = "Timestamp")
    private String timestamp;

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getTempData ( ) {
        return tempData;
    }

    public void setTempData (String tempData) {
        this.tempData = tempData;
    }

    public String getTimestamp ( ) {
        return timestamp;
    }

    public void setTimestamp (String timestamp) {
        this.timestamp = timestamp;
    }

    public TempModel (String tempData, String timestamp) {
        this.tempData = tempData;
        this.timestamp = timestamp;
    }
}
