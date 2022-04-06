package com.mc2022.template;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {GyroModel.class}, version = 3)
public abstract class DatabaseClass3 extends RoomDatabase {
    public abstract DaoClass3 getDao3();

    public static DatabaseClass3 myDatabaseinstance;

    public static  DatabaseClass3 getInstance(Context context){

        if(myDatabaseinstance == null){
            myDatabaseinstance = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseClass3.class,
                    "my_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
        }

        return myDatabaseinstance;
    }

}