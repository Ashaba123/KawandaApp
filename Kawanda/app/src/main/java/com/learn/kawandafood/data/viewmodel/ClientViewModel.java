package com.learn.kawandafood.data.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.repository.ClientRepository;

import java.util.List;


public class ClientViewModel extends AndroidViewModel {
    private ClientRepository clientRepository;

    public ClientViewModel(@NonNull Application application) {
        super(application);
        clientRepository = new ClientRepository(application.getApplicationContext());

    }
    public void insertNewUser(Client client){
        clientRepository.addClient(client);
    }

    public LiveData<List<Client>> getClients(){
       return clientRepository.getClients();
    }

    public LiveData<Client> getById(int id){
        return clientRepository.getById(id);
    }
    public  void  deleteClient(Client client){clientRepository.deleteClient(client);}

}
