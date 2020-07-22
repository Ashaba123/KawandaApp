package com.learn.kawandafood.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.entity.Product;
import com.learn.kawandafood.data.entity.User;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM product")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM product WHERE id= :id")
    LiveData<Product> getById(int id);


    @Query("SELECT COUNT(*) from product")
    LiveData<Integer> countProducts();

    @Insert
    void insertProduct(Product... products);

    @Delete
    void deleteProduct(Product product);

    @Query("UPDATE product SET name = :name, quantity =:quantiy,raw_material =:raw_material WHERE id = :id")
    void editProduct(int id, String name,int quantiy,String raw_material);


}
