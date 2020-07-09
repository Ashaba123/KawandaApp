package com.learn.kawandafood.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import com.learn.kawandafood.data.entity.User;

import java.nio.charset.CodingErrorAction;
import java.util.List;

import static java.nio.charset.CodingErrorAction.REPLACE;

@Dao
public interface UserDao {
    @Query("SELECT * FROM user")
    LiveData<List<User>> getAllUsers();

    @Query("SELECT * FROM user WHERE uid= :id")
    LiveData<User> getById(int id);


    @Query("SELECT * FROM user where full_name =:name and password =:password")
    LiveData<User> getUser(String name, String password);



    @Query("SELECT COUNT(*) from user")
    LiveData<Integer> countUsers();

    @Insert
    void insertUsers(User... users);

    @Delete
    void delete(User user);

    @Query("UPDATE user SET password = :password  WHERE uid = :id")
    void changepassword(int id, String password);


}
