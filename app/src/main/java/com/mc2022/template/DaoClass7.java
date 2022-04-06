package com.mc2022.template;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoClass7 {
    @Insert
    void insert7(gpsModel myModel);

    @Query("select * from gps")
    List<gpsModel> getAllData7();
}
