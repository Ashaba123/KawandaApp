package com.learn.kawandafood.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.User;
import com.learn.kawandafood.data.viewmodel.UserViewModel;
import com.learn.kawandafood.ui.auth.LoginActivity;
import com.learn.kawandafood.ui.clients.BrowseClientActivity;
import com.learn.kawandafood.ui.process.BrowseProcessActivity;
import com.learn.kawandafood.ui.products.BrowseProductsActivity;
import com.learn.kawandafood.ui.reports.ReportsActivity;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    CardView productDash, processDash, reportDash, clientDash;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);
        initUI();
    }

    private void initUI() {
        productDash = findViewById(R.id.dash_product_box);
        processDash = findViewById(R.id.dash_process_box);
        reportDash = findViewById(R.id.dash_reports_box);
        clientDash = findViewById(R.id.dash_client_box);


        productDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BrowseProductsActivity.class));
            }
        });

        processDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BrowseProcessActivity.class));

            }
        });
        reportDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ReportsActivity.class));

            }
        });
        clientDash.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, BrowseClientActivity.class));

            }
        });

        //Bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_item_home:
                        startActivity(new Intent(MainActivity.this, MainActivity.class));
                        break;
                    case R.id.menu_item_process:
                        startActivity(new Intent(MainActivity.this, BrowseProcessActivity.class));
                        break;
                    case R.id.menu_item_profile:
                        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        break;

                }

                return true;
            }
        });


    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
