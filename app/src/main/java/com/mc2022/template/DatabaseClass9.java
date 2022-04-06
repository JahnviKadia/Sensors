package com.mc2022.template;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {LightModel.class}, version = 9)
public abstract class DatabaseClass9 extends RoomDatabase {
    public abstract DaoClass9 getDao9 ( );

    public static DatabaseClass9 myDatabaseinstance;

    public static DatabaseClass9 getInstance (Context context) {

        if (myDatabaseinstance == null) {
            myDatabaseinstance = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseClass9.class,
                    "my_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
        }

        return myDatabaseinstance;
    }
}