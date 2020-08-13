package com.learn.kawandafood.data.entity;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = {
        @ForeignKey(entity = Client.class, parentColumns = "id", childColumns = "client_id"),
})
public class Product {
    @PrimaryKey(autoGenerate = true)
    public  int id;

    @ColumnInfo(name = "client_id")
    public int client_id;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "quantity")
    public int quantity;

    @ColumnInfo(name = "raw_material")
    public String raw_material;

    public Product() {
    }

    public int getId() {
        return id;
    }

    public int getClient_id() {
        return client_id;
    }

    public Product setClient_id(int client_id) {
        this.client_id = client_id;
        return this;
    }

    public Product setId(int id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public Product setName(String name) {
        this.name = name;
        return this;
    }

    public int getQuantity() {
        return quantity;
    }

    public Product setQuantity(int quantity) {
        this.quantity = quantity;
        return this;
    }

    public String getRaw_material() {
        return raw_material;
    }

    public Product setRaw_material(String raw_material) {
        this.raw_material = raw_material;
        return this;
    }
}
