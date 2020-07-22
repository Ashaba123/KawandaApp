package com.learn.kawandafood.ui.products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.entity.Product;
import com.learn.kawandafood.data.viewmodel.ClientViewModel;
import com.learn.kawandafood.data.viewmodel.ProductViewModel;
import com.learn.kawandafood.ui.clients.BrowseClientActivity;

public class AddProductActivity extends AppCompatActivity {

    private EditText productName;
    private EditText productQuantity;
    private EditText productRawMaterial;
    private Button saveProduct;
    private ProgressBar progressBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);
        initUI();
    }

    private void initUI() {
        productName = findViewById(R.id.prdctname);
        productQuantity = findViewById(R.id.prdctquantity);
        productRawMaterial = findViewById(R.id.prdctrawMaterail);
        saveProduct = findViewById(R.id.btnSavePrdct);
        progressBar = findViewById(R.id.progressBar_product);

        saveProduct.setOnClickListener(new View.OnClickListener() {
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
        String raw_material = productRawMaterial.getText().toString();


        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        Product product = new Product();
        product.name =name;
        product.quantity= Integer.parseInt(quantity);
        product.raw_material=raw_material;
        productViewModel.insertProduct(product);

        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(getApplicationContext(), "Saved Product", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), BrowseProductsActivity.class));
    }
}
