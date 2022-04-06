package com.mc2022.template;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "linearAcc")
public class LinearAccModel {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "LinearAccData_X")
    private String linearAcc_X;

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getLinearAcc_X ( ) {
        return linearAcc_X;
    }

    public void setLinearAcc_X (String linearAcc_X) {
        this.linearAcc_X = linearAcc_X;
    }

    public String getLinearAcc_Y ( ) {
        return linearAcc_Y;
    }

    public void setLinearAcc_Y (String linearAcc_Y) {
        this.linearAcc_Y = linearAcc_Y;
    }

    public String getLinearAcc_Z ( ) {
        return linearAcc_Z;
    }

    public void setLinearAcc_Z (String linearAcc_Z) {
        this.linearAcc_Z = linearAcc_Z;
    }

    public void setTimestamp (String timestamp) {
        this.timestamp = timestamp;
    }

    @ColumnInfo(name = "LinearAccData_Y")
    private String linearAcc_Y;

    public LinearAccModel (String linearAcc_X, String linearAcc_Y, String linearAcc_Z, String timestamp) {
        this.linearAcc_X = linearAcc_X;
        this.linearAcc_Y = linearAcc_Y;
        this.linearAcc_Z = linearAcc_Z;
        this.timestamp = timestamp;
    }

    @ColumnInfo(name = "LinearAccData_Z")
    private String linearAcc_Z;

    public String getTimestamp() {
        return timestamp;
    }

    @ColumnInfo(name = "Timestamp")
    private String timestamp;
}
