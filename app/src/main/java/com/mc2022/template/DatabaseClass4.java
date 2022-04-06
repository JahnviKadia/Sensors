package com.mc2022.template;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {ProModel.class}, version = 4)
public abstract class DatabaseClass4 extends RoomDatabase {
    public abstract DaoClass4 getDao4 ( );

    public static DatabaseClass4 myDatabaseinstance;

    public static DatabaseClass4 getInstance (Context context) {

        if (myDatabaseinstance == null) {
            myDatabaseinstance = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseClass4.class,
                    "my_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
        }

        return myDatabaseinstance;
    }
}
