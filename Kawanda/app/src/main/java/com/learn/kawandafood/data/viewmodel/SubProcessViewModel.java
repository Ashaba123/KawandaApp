package com.learn.kawandafood.data.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.learn.kawandafood.data.repository.ProductRepository;
import com.learn.kawandafood.data.repository.SubProcessRepository;


public class SubProcessViewModel extends AndroidViewModel {
    private SubProcessRepository subProcessRepository;


    public SubProcessViewModel(@NonNull Application application) {
        super(application);
         subProcessRepository= new SubProcessRepository(application.getApplicationContext());

    }


}
