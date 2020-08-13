package com.learn.kawandafood.data.entity;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Product.class, parentColumns = "id", childColumns = "product_id"),
})
public class Process {

    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "product_id")
    public int product_id;

    @ColumnInfo(name = "name")
    public String name;

    public Process() {
    }

    public Process(int id, int product_id, String name) {
        this.id = id;
        this.product_id = product_id;
        this.name = name;
    }
}
