package com.mc2022.template;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {TempModel.class}, version = 5)
public abstract class DatabaseClass5 extends RoomDatabase {
    public abstract DaoClass5 getDao5 ( );

    public static DatabaseClass5 myDatabaseinstance;

    public static DatabaseClass5 getInstance (Context context) {

        if (myDatabaseinstance == null) {
            myDatabaseinstance = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseClass5.class,
                    "my_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
        }

        return myDatabaseinstance;
    }
}

