package com.learn.kawandafood.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.entity.Process;

import java.util.List;

@Dao
public interface ProcessDao {

    @Query("SELECT * FROM process")
    LiveData<List<Process>> getAllProcesses();

    @Query("SELECT * FROM process WHERE id= :id")
    LiveData<Process> getById(int id);


    @Query("SELECT COUNT(*) from process")
    LiveData<Integer> countProcesses();

    @Insert
    void insertProcess(Process... process);

    @Delete
    void deleteProcess(Process  process);


}
