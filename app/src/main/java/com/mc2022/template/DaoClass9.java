package com.mc2022.template;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoClass9 {
    @Insert
    void insert9(LightModel myModel);

    @Query("select * from Light")
    List<LightModel> getAllData9();


}