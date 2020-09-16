package com.learn.kawandafood.data.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learn.kawandafood.AppDatabase;
import com.learn.kawandafood.data.entity.Process;
import com.learn.kawandafood.data.entity.Product;
import com.learn.kawandafood.data.repository.ProcessRepository;
import com.learn.kawandafood.data.repository.ProductRepository;

import java.util.List;


public class ProcessViewModel extends AndroidViewModel {
    private ProcessRepository processRepository;


    public ProcessViewModel(@NonNull Application application) {
        super(application);
        processRepository = new ProcessRepository(application.getApplicationContext());
    }

    public void insertProcess(Process process) {
        processRepository.addProcess(process);
    }

    public LiveData<List<Process>> getProcesses() {
        return processRepository.getProcesses();
    }

    public  LiveData<Integer>countProcesses(){
        return processRepository.countProcesses();
    }

    public LiveData<Process> getById(int id) {
        return processRepository.getById(id);
    }

    public void deleteProcess(Process process) {
        processRepository.deleteProcess(process);
    }


}
