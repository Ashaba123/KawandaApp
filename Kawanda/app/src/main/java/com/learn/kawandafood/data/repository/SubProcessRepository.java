package com.learn.kawandafood.data.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.learn.kawandafood.AppDatabase;
import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.entity.Process;
import com.learn.kawandafood.data.entity.SubProcess;

import java.util.List;

public class SubProcessRepository {
    private Context context;

    public SubProcessRepository(Context context) {
        this.context = context.getApplicationContext();
    }

    public void addSubProcess( final SubProcess subProcess) {
        AsyncTask.execute(new Runnable(
        ) {
            @Override
            public void run() {
                AppDatabase.getInstance(context).subProcessDao().insertSubProcess(subProcess);
            }
        });
    }

    public LiveData<List<SubProcess>> getSubProcesses() {
        return  AppDatabase.getInstance(context).subProcessDao().getAllSubProcesses();
    }

    public LiveData<SubProcess> getById(int id) {
        return  AppDatabase.getInstance(context).subProcessDao().getById(id);
    }

    public void deleteProcess(SubProcess subProcess) {
        AsyncTask.execute(() -> AppDatabase.getInstance(context).subProcessDao().deleteSubProcess(subProcess));
    }


}
