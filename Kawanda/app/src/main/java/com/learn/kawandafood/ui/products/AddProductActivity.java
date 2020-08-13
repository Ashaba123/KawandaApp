package com.learn.kawandafood.ui.products;

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
import com.learn.kawandafood.data.entity.Product;
import com.learn.kawandafood.data.viewmodel.ClientViewModel;
import com.learn.kawandafood.data.viewmodel.ProductViewModel;

import java.util.ArrayList;
import java.util.List;

public class AddProductActivity extends AppCompatActivity {

    private EditText productName;
    private EditText productQuantity;
    private Button btnsaveProduct;
    private Spinner clientSv;
    private ProgressBar progressBar;
    private List<Client> clients;
    private int clientId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initUI();
    }

    private void initUI() {
        productName = findViewById(R.id.prdctname);
        productQuantity = findViewById(R.id.prdctquantity);
        progressBar = findViewById(R.id.progressBar_product);
        btnsaveProduct = findViewById(R.id.btnSavePrdct);
        clientSv = findViewById(R.id.add_client);


        clientSv.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent == clientSv) {
                    if (position > 0) {
                        clientId = clients.get(position - 1).id;

                    } else {
                        clientId = 0;

                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        ClientViewModel clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        clientViewModel.getClients().observe(this, clients1 -> {
            this.clients = clients1;
            List<String> data = new ArrayList<>();
            data.add("Choose Client");
            for (Client client : clients) {
                data.add(client.name.toUpperCase());
            }
            ArrayAdapter spinnerAdpater = new ArrayAdapter(this, android.R.layout.simple_spinner_item, data);
            spinnerAdpater.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
            clientSv.setAdapter(spinnerAdpater);


        });


        btnsaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveProducts();
            }
        });
    }

    private void saveProducts() {
        progressBar.setVisibility(View.VISIBLE);
        String name = productName.getText().toString();
        String quantity = productQuantity.getText().toString();

        if(clientId ==0){
            productName.setError("Please select a Client");
            return;
        }
        if(name.isEmpty()){
            productName.setError("Please enter the product name");
            return;
        }
        if(quantity.isEmpty()){
            productQuantity.setError("Please enter the product quantity");
            return;
        }



        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        Product product = new Product();
        product.client_id = clientId;
        product.name = name;
        product.quantity = Integer.parseInt(quantity);
        productViewModel.insertProduct(product);

        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(getApplicationContext(), "Saved Product", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), BrowseProductsActivity.class));
        finish();
    }
}
