package com.learn.kawandafood.ui.products;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.Product;
import com.learn.kawandafood.data.viewmodel.ProductViewModel;
import com.learn.kawandafood.ui.MainActivity;
import com.learn.kawandafood.ui.ProfileActivity;
import com.learn.kawandafood.ui.auth.LoginActivity;
import com.learn.kawandafood.ui.process.BrowseProcessActivity;

import java.util.ArrayList;
import java.util.List;

public class BrowseProductsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView emptyData;
    FloatingActionButton btnAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_products);
        initUI();
    }

    private void initUI() {

        recyclerView = findViewById(R.id.list_of_products);
        progressBar = findViewById(R.id.progressBar_products);
        emptyData = findViewById(R.id.txtProducts);
        btnAddProduct = findViewById(R.id.btnAddProduct);

        btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BrowseProductsActivity.this, AddProductActivity.class));
            }
        });

        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);

        List<Product> productList = new ArrayList<>();
        ProductsAdapter productsAdapter = new ProductsAdapter(productList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(productsAdapter);

        productViewModel.getProducts().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(List<Product> products) {
                if (products != null) {
                    progressBar.setVisibility(View.GONE);
                    if (products.isEmpty()) {
                        emptyData.setVisibility(View.VISIBLE);
                    } else {
                        productsAdapter.setData(products);
                        productsAdapter.notifyDataSetChanged();
                    }
                }
            }
        });


        //Bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_item_home:
                        startActivity(new Intent(BrowseProductsActivity.this, MainActivity.class));
                        break;
                    case R.id.menu_item_process:
                        startActivity(new Intent(BrowseProductsActivity.this, BrowseProcessActivity.class));
                        break;
                    case R.id.menu_item_profile:
                        startActivity(new Intent(BrowseProductsActivity.this, ProfileActivity.class));
                        break;

                    case R.id.menu_item_out:
                        startActivity(new Intent(BrowseProductsActivity.this, LoginActivity.class));
                        finish();
                        break;
                }

                return true;
            }
        });
    }
}
