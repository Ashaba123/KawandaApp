package com.learn.kawandafood.data.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.learn.kawandafood.AppDatabase;
import com.learn.kawandafood.data.entity.User;

import java.util.List;

public class UserRepository {
    private Context context;

    public UserRepository(Context context) {
        this.context = context.getApplicationContext();
    }

    public void addUser(final User user) {
        //AsyncTask.execute(()-> DbDatabase.getInstance(context).dbDao().insertItem(dbTable));
        AsyncTask.execute(new Runnable(
        ) {
            @Override
            public void run() {
                AppDatabase.getInstance(context).userDao().insertUsers(user);
            }
        });
    }

    public LiveData<User> getUser(String name, String password) {
        return  AppDatabase.getInstance(context).userDao().getUser(name, password);
    }
    public LiveData<User> getById(int id) {
        return  AppDatabase.getInstance(context).userDao().getById(id);
    }

    public void deleteItem(final User user) {
        AsyncTask.execute(() -> AppDatabase.getInstance(context).userDao().delete(user));
    }

}
