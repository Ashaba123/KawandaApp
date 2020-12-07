package com.learn.kawandafood.data.viewmodel;

import android.app.Application;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learn.kawandafood.AppDatabase;
import com.learn.kawandafood.data.entity.User;
import com.learn.kawandafood.data.repository.UserRepository;

import java.util.List;


public class UserViewModel extends AndroidViewModel {
    private UserRepository userRepository;
    private LiveData<User> user;

    public UserViewModel(@NonNull Application application) {
        super(application);
        userRepository = new UserRepository(application.getApplicationContext());

    }

    //asks the repository how many users do we have
    public LiveData<Integer> countUsers() {
        return userRepository.countUsers();
    }

    public void changepassword(int id, String password) {
        userRepository.changepassword(id, password);
    }
    public void loginStatus(int id, int isLoggedIn){
        userRepository.loginStatus(id,isLoggedIn);
    }

    //get all users added
    public LiveData<List<User>> getUsers() {
        return userRepository.getUsers();
    }

    //add a user
    public void insertNewUser(User user) {
        userRepository.addUser(user);
    }

    //get a single user
    public LiveData<User> getUser(String name, String password) {
        return userRepository.getUser(name, password);
    }

    public LiveData<User> getById(int id) {
        return userRepository.getById(id);
    }

}
