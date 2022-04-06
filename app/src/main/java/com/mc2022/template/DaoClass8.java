package com.mc2022.template;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoClass8 {
    @Insert
    void insert8(AccModel myModel);

    @Query("select * from Acc")
    List<AccModel> getAllData8();

    @Query("select * from Acc order by id desc limit 2")
    List<AccModel> getLastVal();



}