package com.mc2022.template;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoClass6 {
    @Insert
    void insert6(MagFieldModel myModel);

    @Query("select * from MagField")
    List<MagFieldModel> getAllData6();
}

