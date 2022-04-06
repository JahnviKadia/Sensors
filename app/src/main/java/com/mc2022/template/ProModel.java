package com.mc2022.template;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Pro")
public class ProModel {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "ProData")
    private String pro;

    @ColumnInfo(name = "Timestamp")
    private String timestamp;

    public ProModel (String pro, String timestamp) {
        this.pro = pro;
        this.timestamp = timestamp;
    }

    public String getPro ( ) {
        return pro;
    }

    public void setPro (String pro) {
        this.pro = pro;
    }

    public String getTimestamp ( ) {
        return timestamp;
    }

    public void setTimestamp (String timestamp) {
        this.timestamp = timestamp;
    }

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }
}
