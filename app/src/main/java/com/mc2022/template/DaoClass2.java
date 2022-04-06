package com.mc2022.template;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoClass2 {
    @Insert
    void insert2(LinearAccModel myModel);

    @Query("select * from linearAcc")
    List<LinearAccModel> getAllData2();

    @Query("select * from linearAcc order by id desc limit 10")
    List<LinearAccModel> LinearAccLineChart();
}
