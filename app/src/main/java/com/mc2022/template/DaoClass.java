package com.mc2022.template;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface DaoClass {
    @Insert
    void insert(UserModel myModel);

    @Query("select * from user")
    List<UserModel> getAllData();
}
