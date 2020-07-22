package com.learn.kawandafood.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.LinearLayoutCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.learn.kawandafood.R;
import com.learn.kawandafood.ui.auth.LoginActivity;
import com.learn.kawandafood.ui.clients.BrowseClientActivity;
import com.learn.kawandafood.ui.process.ProcessActivity;
import com.learn.kawandafood.ui.products.BrowseProductsActivity;
import com.learn.kawandafood.ui.reports.ReportsActivity;

public class MainActivity extends AppCompatActivity {

    LinearLayoutCompat productDash,processDash,reportDash,clientDash;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
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
                startActivity(new Intent(MainActivity.this, ProcessActivity.class));

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
                        startActivity(new Intent(MainActivity.this, ProcessActivity.class));
                        break;
                    case R.id.menu_item_profile:
                        startActivity(new Intent(MainActivity.this, ProfileActivity.class));
                        break;

                    case R.id.menu_item_out:
                        startActivity(new Intent(MainActivity.this, LoginActivity.class));
                        finish();
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