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
        //runs in the background as you insert a new user
        AsyncTask.execute(new Runnable(
        ) {
            @Override
            public void run() {
                AppDatabase.getInstance(context).userDao().insertUsers(user);
            }
        });
    }

    public LiveData<Integer> countUsers() {
        //counts how many users
        return AppDatabase.getInstance(context).userDao().countUsers();
    }

    public void changepassword(int id, String password) {
        //if you forgot password you can change it
        AsyncTask.execute(() -> {
            AppDatabase.getInstance(context).userDao().changepassword(id, password);
        });

    }
    //checks if you are logged in or not
    public void loginStatus(int id, int isLoggedIn){
        AsyncTask.execute(() -> {
            AppDatabase.getInstance(context).userDao().loginStatus(id,isLoggedIn);
        });
    }

    public LiveData<User> getUser(String name, String password) {
        return AppDatabase.getInstance(context).userDao().getUser(name, password);
    }

    public LiveData<User> getById(int id) {
        return AppDatabase.getInstance(context).userDao().getById(id);
    }

    public LiveData<List<User>> getUsers() {
        //dbDatabaseInstance is out database returned.userDao go to function getUsers
        return AppDatabase.getInstance(context).userDao().getUsers();
    }

    public void deleteUser(final User user) {
        AsyncTask.execute(() -> AppDatabase.getInstance(context).userDao().delete(user));
    }

}
