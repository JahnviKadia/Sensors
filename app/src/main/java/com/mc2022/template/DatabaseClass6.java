package com.mc2022.template;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {MagFieldModel.class}, version = 6)
public abstract class DatabaseClass6 extends RoomDatabase {
    public abstract DaoClass6 getDao6 ( );

    public static DatabaseClass6 myDatabaseinstance;

    public static DatabaseClass6 getInstance (Context context) {

        if (myDatabaseinstance == null) {
            myDatabaseinstance = Room.databaseBuilder(context.getApplicationContext(),
                    DatabaseClass6.class,
                    "my_database")
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries().build();
        }

        return myDatabaseinstance;
    }
}
