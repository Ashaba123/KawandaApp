package com.learn.kawandafood.ui.products;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.Product;
import com.learn.kawandafood.data.viewmodel.ProductViewModel;

public class EditProductActivity extends AppCompatActivity {

    private EditText productName;
    private EditText productQuantity;
    private EditText productRawMaterial;
    private ProgressBar progressBar;
    private ProductViewModel productViewModel;
    private Bundle extrasBundle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_product);
        initUI();
    }

    private void initUI() {
        productName = findViewById(R.id.edit_product_name);
        productQuantity = findViewById(R.id.edit_quantity);
        productRawMaterial = findViewById(R.id.edit_rawmaterials);
        Button btnsaveProduct = findViewById(R.id.btnEditPrdct);
        progressBar = findViewById(R.id.progressBar_product);

        extrasBundle = getIntent().getExtras();
        //set
        productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        productViewModel.getProduct(extrasBundle.getInt("id")).observe(this, new Observer<Product>() {
            @Override
            public void onChanged(Product product) {
                productName.setText(product.name);
                productQuantity.setText(String.valueOf(product.quantity));
                productRawMaterial.setText(product.raw_material);
            }
        });



        btnsaveProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProducts();
            }
        });
    }

    private void updateProducts() {
        progressBar.setVisibility(View.VISIBLE);
        String name = productName.getText().toString();
        String quantity = productQuantity.getText().toString();
        String raw_material = productRawMaterial.getText().toString();

        int id = extrasBundle.getInt("id");
        Product product = new Product();
        product.id =id;
        product.name =name;
        product.quantity= Integer.parseInt(quantity);
        product.raw_material=raw_material;

        productViewModel.editProduct(product);

        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(getApplicationContext(), " Product Updated", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), BrowseProductsActivity.class));
    }
}
