package com.mc2022.template;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {LinearAccModel.class}, version = 2)
public abstract class DatabaseClass2 extends RoomDatabase {
    public abstract DaoClass2 getDao2();

    public static DatabaseClass2 myDatabaseinstance;

    public static  DatabaseClass2 getInstance(Context context){

        if(myDatabaseinstance == null){
            myDatabaseinstance = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseClass2.class,
                    "my_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
        }

        return myDatabaseinstance;
    }

}
