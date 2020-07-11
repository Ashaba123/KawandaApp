package com.learn.kawandafood.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Product {
    @PrimaryKey(autoGenerate = true)
    public  int id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "quantity")
    public int quantity;

    @ColumnInfo(name = "raw_material")
    public String raw_material;

    @ColumnInfo(name = "client")
    public String client;
}
