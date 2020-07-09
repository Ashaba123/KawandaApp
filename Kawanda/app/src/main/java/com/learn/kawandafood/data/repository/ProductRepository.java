package com.learn.kawandafood.data.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.learn.kawandafood.AppDatabase;
import com.learn.kawandafood.data.entity.Client;

import java.util.List;

public class ProductRepository {
    private Context context;

    public ProductRepository(Context context) {
        this.context = context.getApplicationContext();
    }

    public void addClient(final Client client) {

    }


}
