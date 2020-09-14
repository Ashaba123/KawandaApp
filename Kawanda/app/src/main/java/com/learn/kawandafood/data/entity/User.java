package com.learn.kawandafood.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class User {
    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "full_name")
    public String fullName;

    @ColumnInfo(name = "email")
    public String email;

    @ColumnInfo(name = "password")
    public String password;

    @ColumnInfo(name = "isLoggedIn")
    public int isLoggedIn;

    public User(){}

    public User(int uid, String fullName, String email, String password, int isLoggedIn) {
        this.uid = uid;
        this.fullName = fullName;
        this.email = email;
        this.password = password;
        this.isLoggedIn = isLoggedIn;
    }

    public int getUid() {
        return uid;
    }


    public String getFullName() {
        return fullName;
    }


    public String getEmail() {
        return email;
    }


    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }

    public int isLoggedIn() {
        return isLoggedIn;
    }

    public User setLoggedIn(int loggedIn) {
        isLoggedIn = loggedIn;
        return this;
    }
}
