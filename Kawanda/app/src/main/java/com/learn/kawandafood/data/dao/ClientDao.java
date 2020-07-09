package com.learn.kawandafood.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;


import com.learn.kawandafood.data.entity.Client;

import java.util.List;

@Dao
public interface ClientDao {
    @Query("SELECT * FROM client")
    LiveData<List<Client>> getAllClients();

    @Query("SELECT * FROM client WHERE id= :id")
    LiveData<Client> getById(int id);


    @Query("SELECT COUNT(*) from client")
    LiveData<Integer> countClients();

    @Insert
    void insertClient(Client... clients);

    @Delete
    void deleteClient(Client client);


}
