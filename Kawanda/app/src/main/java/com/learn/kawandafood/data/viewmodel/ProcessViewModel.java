package com.learn.kawandafood.data.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import com.learn.kawandafood.data.repository.ProcessRepository;
import com.learn.kawandafood.data.repository.ProductRepository;


public class ProcessViewModel extends AndroidViewModel {
    private ProcessRepository processRepository;


    public ProcessViewModel(@NonNull Application application) {
        super(application);
         processRepository= new ProcessRepository(application.getApplicationContext());

    }


}
