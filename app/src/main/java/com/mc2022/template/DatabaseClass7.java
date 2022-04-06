package com.mc2022.template;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {gpsModel.class}, version = 7)
public abstract class DatabaseClass7 extends RoomDatabase {
    public abstract DaoClass7 getDao7 ( );

    public static DatabaseClass7 myDatabaseinstance;

    public static DatabaseClass7 getInstance (Context context) {

        if (myDatabaseinstance == null) {
            myDatabaseinstance = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseClass7.class,
                    "my_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
        }

        return myDatabaseinstance;
    }
}