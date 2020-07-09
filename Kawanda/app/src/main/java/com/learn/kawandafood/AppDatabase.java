package com.learn.kawandafood;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.learn.kawandafood.data.dao.ClientDao;
import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.entity.Product;
import com.learn.kawandafood.data.entity.SubProcess;
import com.learn.kawandafood.data.entity.User;
import com.learn.kawandafood.data.dao.UserDao;

@Database(
        entities = {User.class, Client.class},
        version = 1
)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase dbDatabaseInstance;
    public abstract UserDao userDao();
    public abstract ClientDao clientDao();



    public static synchronized AppDatabase getInstance(Context context) {
        if(dbDatabaseInstance ==null){
            dbDatabaseInstance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "kawandaDb").build();
        }

        return dbDatabaseInstance;

    }


}
