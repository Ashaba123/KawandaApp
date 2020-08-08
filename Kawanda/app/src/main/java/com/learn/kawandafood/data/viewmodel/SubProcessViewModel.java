package com.learn.kawandafood.data.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learn.kawandafood.AppDatabase;
import com.learn.kawandafood.data.entity.Process;
import com.learn.kawandafood.data.entity.SubProcess;
import com.learn.kawandafood.data.repository.ProductRepository;
import com.learn.kawandafood.data.repository.SubProcessRepository;

import java.util.List;


public class SubProcessViewModel extends AndroidViewModel {
    private SubProcessRepository subProcessRepository;


    public SubProcessViewModel(@NonNull Application application) {
        super(application);
        subProcessRepository = new SubProcessRepository(application.getApplicationContext());

    }

    public void insertSubProcess(SubProcess subProcess) {
        subProcessRepository.addSubProcess(subProcess);
    }

    public LiveData<List<SubProcess>> getAllSubProcess() {
        return subProcessRepository.getAllSubProcesses();
    }

    public LiveData<SubProcess> getById(int id) {
        return subProcessRepository.getById(id);
    }

    public LiveData<List<SubProcess>> getSubProcesses(int processId) {
        return subProcessRepository.getSubProcesses(processId);
    }

    public LiveData<Integer> countSubProcesses() {
        return subProcessRepository.countSubProcesses();
    }

    public void deleteSubProcess(SubProcess subProcess) {
        subProcessRepository.deleteProcess(subProcess);
    }


}
