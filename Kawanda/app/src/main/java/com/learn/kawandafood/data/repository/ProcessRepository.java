package com.learn.kawandafood.data.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.learn.kawandafood.AppDatabase;
import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.entity.Process;
import com.learn.kawandafood.data.entity.Product;

import java.util.List;

public class ProcessRepository {
    private Context context;

    public ProcessRepository(Context context) {
        this.context = context.getApplicationContext();
    }

    public void addProcess(Process process) {
        AsyncTask.execute(new Runnable(
        ) {
            @Override
            public void run() {
                AppDatabase.getInstance(context).processDao().insertProcess(process);
            }
        });
    }

    public LiveData<List<Process>> getProcesses() {
        return  AppDatabase.getInstance(context).processDao().getAllProcesses();
    }

    public LiveData<Process> getById(int id) {
        return  AppDatabase.getInstance(context).processDao().getById(id);
    }

    public void deleteProcess(Process process) {
        AsyncTask.execute(() -> AppDatabase.getInstance(context).processDao().deleteProcess(process));
    }


}
