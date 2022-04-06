package com.mc2022.template;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "MagField")
public class MagFieldModel {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "MagField_X")
    private String magField_X;

    @ColumnInfo(name = "MagField_Y")
    private String magField_Y;

    @ColumnInfo(name = "MagField_Z")
    private String magField_Z;

    @ColumnInfo(name = "Timestamp")
    private String timestamp;

    public MagFieldModel (String magField_X, String magField_Y, String magField_Z, String timestamp) {
        this.magField_X = magField_X;
        this.magField_Y = magField_Y;
        this.magField_Z = magField_Z;
        this.timestamp = timestamp;
    }

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getMagField_X ( ) {
        return magField_X;
    }

    public void setMagField_X (String magField_X) {
        this.magField_X = magField_X;
    }

    public String getMagField_Y ( ) {
        return magField_Y;
    }

    public void setMagField_Y (String magField_Y) {
        this.magField_Y = magField_Y;
    }

    public String getMagField_Z ( ) {
        return magField_Z;
    }

    public void setMagField_Z (String magField_Z) {
        this.magField_Z = magField_Z;
    }

    public String getTimestamp ( ) {
        return timestamp;
    }

    public void setTimestamp (String timestamp) {
        this.timestamp = timestamp;
    }
}

