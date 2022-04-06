package com.mc2022.template;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoClass4 {
    @Insert
    void insert4(ProModel myModel);

    @Query("select * from Pro")
    List<ProModel> getAllData4();

    @Query("select * from Pro order by id desc limit 10")
    List<ProModel> ProLineChart();



}
