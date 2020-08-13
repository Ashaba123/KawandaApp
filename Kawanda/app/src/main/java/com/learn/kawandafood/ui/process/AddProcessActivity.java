package com.learn.kawandafood.ui.process;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.entity.Process;
import com.learn.kawandafood.data.entity.Product;
import com.learn.kawandafood.data.viewmodel.ClientViewModel;
import com.learn.kawandafood.data.viewmodel.ProcessViewModel;
import com.learn.kawandafood.data.viewmodel.ProductViewModel;
import com.learn.kawandafood.ui.clients.BrowseClientActivity;

import java.util.ArrayList;
import java.util.List;

public class AddProcessActivity extends AppCompatActivity {

    private Button saveProcess;
    private EditText processName;
    private Spinner productSv;
    private int productId;
    private List<Product> products;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_process);
        initUI();
    }

    private void initUI() {
        processName = findViewById(R.id.processname);
        saveProcess = findViewById(R.id.btnSaveProcess);
        progressBar = findViewById(R.id.progressBar_proses);
        productSv = findViewById(R.id.product);

        productSv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent == productSv) {
                    if (position > 0) {
                        productId = products.get(position - 1).id;

                    } else {
                        productId = 0;

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getProducts().observe(this, products1  -> {
            this.products = products1;
            List<String> data = new ArrayList<>();
            data.add("Choose Product");
            for (Product product : products) {
                data.add(product.name.toUpperCase());
            }
            ArrayAdapter spinnerAdpater = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data);
            spinnerAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            productSv.setAdapter(spinnerAdpater);


        });


        saveProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProcess();
            }
        });


    }

    private void addProcess() {
        progressBar.setVisibility(View.VISIBLE);
        String processTitle = processName.getText().toString().trim();

        if(productId ==0){
            processName.setError("Please select a Product");
            return;
        }
        if (processTitle.isEmpty()) {
            processName.setError("Enter Process name");
        }

        ProcessViewModel processViewModel = new ViewModelProvider(this).get(ProcessViewModel.class);
        Process process1 = new Process();
        process1.product_id =productId;
        process1.name = processTitle;
        processViewModel.insertProcess(process1);
        Toast.makeText(getApplicationContext(), "Saved Process", Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.INVISIBLE);
        startActivity(new Intent(getApplicationContext(), BrowseProcessActivity.class));
        finish();
    }
}
