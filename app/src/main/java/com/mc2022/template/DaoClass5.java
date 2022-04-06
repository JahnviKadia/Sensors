package com.mc2022.template;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoClass5 {
    @Insert
    void insert5(TempModel myModel);

    @Query("select * from Temperature")
    List<TempModel> getAllData5();



}

