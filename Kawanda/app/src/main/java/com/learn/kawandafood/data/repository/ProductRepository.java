package com.learn.kawandafood.data.repository;

import android.content.Context;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.learn.kawandafood.AppDatabase;
import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.entity.Product;

import java.util.List;

public class ProductRepository {
    private Context context;

    public ProductRepository(Context context) {
        this.context = context.getApplicationContext();
    }

    public void addProduct(final Product product) {
        AsyncTask.execute(new Runnable(
        ) {
            @Override
            public void run() {
                AppDatabase.getInstance(context).productDao().insertProduct(product);
            }
        });
    }

    public LiveData<List<Product>> getProducts() {
        return AppDatabase.getInstance(context).productDao().getAllProducts();
    }

    public LiveData<Product> getById(int id) {
        return AppDatabase.getInstance(context).productDao().getById(id);
    }

    public void deleteProduct(final Product product) {
        AsyncTask.execute(() -> AppDatabase.getInstance(context).productDao().deleteProduct(product));
    }

    public void editProduct(int id, String name, int quantiy, String raw_material) {
        AsyncTask.execute(() -> AppDatabase.getInstance(
                context).productDao().editProduct(id, name, quantiy, raw_material));
    }


}
