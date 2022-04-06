package com.mc2022.template;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Acc")
public class AccModel {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "Acc_X")
    private Float acc_X;

    public Float getAcc_X ( ) {
        return acc_X;
    }

    public Float getAcc_Y ( ) {
        return acc_Y;
    }

    public Float getAcc_Z ( ) {
        return acc_Z;
    }

    @ColumnInfo(name = "Acc_Y")
    private Float acc_Y;

    @ColumnInfo(name = "Acc_Z")
    private Float acc_Z;

    public AccModel (Float acc_X, Float acc_Y, Float acc_Z) {
        this.acc_X = acc_X;
        this.acc_Y = acc_Y;
        this.acc_Z = acc_Z;
    }


    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public Float getAcc_X (int i) {
        return acc_X;
    }

    public void setAcc_X (Float acc_X) {
        this.acc_X = acc_X;
    }

    public Float getAcc_Y (int i) {
        return acc_Y;
    }

    public void setAcc_Y (Float acc_Y) {
        this.acc_Y = acc_Y;
    }

    public Float getAcc_Z (int i) {
        return acc_Z;
    }

    public void setAcc_Z (Float acc_Z) {
        this.acc_Z = acc_Z;
    }

}
