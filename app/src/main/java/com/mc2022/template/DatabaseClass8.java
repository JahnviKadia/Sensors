package com.mc2022.template;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {AccModel.class}, version = 8)
public abstract class DatabaseClass8 extends RoomDatabase {
    public abstract DaoClass8 getDao8 ( );

    public static DatabaseClass8 myDatabaseinstance;

    public static DatabaseClass8 getInstance (Context context) {

        if (myDatabaseinstance == null) {
            myDatabaseinstance = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseClass8.class,
                    "my_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
        }

        return myDatabaseinstance;
    }
}
