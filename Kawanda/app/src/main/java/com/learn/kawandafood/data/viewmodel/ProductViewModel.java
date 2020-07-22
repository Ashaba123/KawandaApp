package com.learn.kawandafood.data.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.entity.Product;
import com.learn.kawandafood.data.repository.ClientRepository;
import com.learn.kawandafood.data.repository.ProductRepository;

import java.util.List;


public class ProductViewModel extends AndroidViewModel {
    private ProductRepository productRepository;

    public ProductViewModel(@NonNull Application application) {
        super(application);
         productRepository= new ProductRepository(application.getApplicationContext());

    }

    public void insertProduct(Product product){
        productRepository.addProduct(product);
    }

    public LiveData<List<Product>> getProducts(){
        return productRepository.getProducts();
    }

    public LiveData<Product> getProduct(int id){
        return productRepository.getProduct(id);
    }
    public  void  deleteProduct(Product product){productRepository.deleteProduct(product);}

    public  void  editProduct(Product product){
        productRepository.editProduct(product);}



}
