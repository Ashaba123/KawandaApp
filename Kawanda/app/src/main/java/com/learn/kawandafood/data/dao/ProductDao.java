package com.learn.kawandafood.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.entity.Product;
import com.learn.kawandafood.data.entity.User;

import java.util.List;

@Dao
public interface ProductDao {
    @Query("SELECT * FROM product")
    LiveData<List<Product>> getAllProducts();

    @Query("SELECT * FROM product WHERE id= :id")
    LiveData<Product> getProduct(int id);


    @Query("SELECT COUNT(*) from product")
    LiveData<Integer> countProducts();

    @Insert
    void insertProduct(Product... products);

    @Delete
    void deleteProduct(Product product);

    @Update
    void editProduct(Product... product);




}
