package com.learn.kawandafood.ui.reports;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.learn.kawandafood.R;
import com.learn.kawandafood.data.viewmodel.ClientViewModel;
import com.learn.kawandafood.data.viewmodel.ProductViewModel;
import com.learn.kawandafood.data.viewmodel.UserViewModel;
import com.learn.kawandafood.ui.MainActivity;
import com.learn.kawandafood.ui.ProfileActivity;
import com.learn.kawandafood.ui.auth.LoginActivity;
import com.learn.kawandafood.ui.process.BrowseProcessActivity;

public class ReportsActivity extends AppCompatActivity {

    TextView clientCount, productCount, userCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reports);
        initUI();

    }

    private void initUI() {
        clientCount = findViewById(R.id.client_count);
        productCount = findViewById(R.id.product_count);
        userCount = findViewById(R.id.users_count);

        ClientViewModel clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        ProductViewModel productViewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        clientViewModel.countClients().observe(this, clients -> clientCount.setText(String.valueOf(clients)));

        productViewModel.countProducts().observe(this,products -> {
            productCount.setText(String.valueOf(products));
        });
        userViewModel.countUsers().observe(this,users -> {
            userCount.setText(String.valueOf(users));
        });




        //Bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_item_home:
                        startActivity(new Intent(ReportsActivity.this, MainActivity.class));
                        break;
                    case R.id.menu_item_process:
                        startActivity(new Intent(ReportsActivity.this, BrowseProcessActivity.class));
                        break;
                    case R.id.menu_item_profile:
                        startActivity(new Intent(ReportsActivity.this, ProfileActivity.class));
                        break;
                }

                return true;
            }
        });

    }
}
