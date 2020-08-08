package com.learn.kawandafood.data.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.learn.kawandafood.data.entity.Process;
import com.learn.kawandafood.data.entity.SubProcess;

import java.util.List;

@Dao
public interface SubProcessDao {

    @Query("SELECT * FROM subprocess")
    LiveData<List<SubProcess>> getAllSubProcesses();

    @Query("SELECT * FROM subprocess WHERE process_id =:processId")
    LiveData<List<SubProcess>> getSubProcesses(int processId);

    @Query("SELECT * FROM subprocess WHERE id= :id")
    LiveData<SubProcess> getById(int id);


    @Query("SELECT COUNT(*) from subprocess")
    LiveData<Integer> countSubProcesses();

    @Insert
    void insertSubProcess(SubProcess... subProcesses);

    @Delete
    void deleteSubProcess(SubProcess subProcess);


}
