package com.mc2022.template;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "Light")
public class LightModel {
    @PrimaryKey(autoGenerate = true)
    private int id = 0;

    @ColumnInfo(name = "LightData")
    private String light;

    public int getId ( ) {
        return id;
    }

    public void setId (int id) {
        this.id = id;
    }

    public String getLight ( ) {
        return light;
    }

    public void setLight (String light) {
        this.light = light;
    }

    public LightModel (String light) {
        this.light = light;
    }
}
