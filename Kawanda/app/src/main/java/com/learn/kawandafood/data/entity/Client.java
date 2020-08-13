package com.learn.kawandafood.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity
public class Client {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "phonenumber")
    public String phonenumber;

    public Client() {
    }

    public Client(int id, String name, String phonenumber) {
        this.id = id;
        this.name = name;
        this.phonenumber = phonenumber;
    }
}
