package com.learn.kawandafood.data.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.learn.kawandafood.AppDatabase;
import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.entity.User;

import java.util.List;

public class ClientRepository {
    private Context context;

    public ClientRepository(Context context) {
        this.context = context.getApplicationContext();
    }

    public void addClient(final Client client) {
        AsyncTask.execute(new Runnable(
        ) {
            @Override
            public void run() {
                AppDatabase.getInstance(context).clientDao().insertClient(client);
            }
        });
    }

    public LiveData<Integer>  countClients(){
        return AppDatabase.getInstance(context).clientDao().countClients();
    }

    public LiveData<List<Client>> getClients() {
        return  AppDatabase.getInstance(context).clientDao().getAllClients();
    }

    public LiveData<Client> getById(int id) {
        return  AppDatabase.getInstance(context).clientDao().getById(id);
    }

    public void deleteClient(final Client client) {
        AsyncTask.execute(() -> AppDatabase.getInstance(context).clientDao().deleteClient(client));
    }

}
