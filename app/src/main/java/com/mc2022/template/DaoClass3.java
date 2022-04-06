package com.mc2022.template;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoClass3 {
    @Insert
    void insert3(GyroModel myModel);

    @Query("select * from Gyro")
    List<GyroModel> getAllData3();
}
