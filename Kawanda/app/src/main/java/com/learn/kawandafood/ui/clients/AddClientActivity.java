package com.learn.kawandafood.ui.clients;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.viewmodel.ClientViewModel;
import com.learn.kawandafood.ui.MainActivity;
import com.learn.kawandafood.ui.ProfileActivity;
import com.learn.kawandafood.ui.auth.LoginActivity;
import com.learn.kawandafood.ui.process.ProcessActivity;

public class AddClientActivity extends AppCompatActivity {

    private EditText clientName;
    private EditText clientNumber;
    private Button saveClient;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_clients);
        initUI();
    }

    private void initUI() {
        clientName = findViewById(R.id.clientname);
        clientNumber = findViewById(R.id.phonenumber);
        saveClient = findViewById(R.id.btnAddClient);
        progressBar = findViewById(R.id.progressBar_client);

        saveClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveClients();
            }
        });

        //Bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_item_home:
                        startActivity(new Intent(AddClientActivity.this, MainActivity.class));
                        break;
                    case R.id.menu_item_process:
                        startActivity(new Intent(AddClientActivity.this, ProcessActivity.class));
                        break;
                    case R.id.menu_item_profile:
                        startActivity(new Intent(AddClientActivity.this, ProfileActivity.class));
                        break;

                    case R.id.menu_item_out:
                        startActivity(new Intent(AddClientActivity.this, LoginActivity.class));
                        finish();
                        break;
                }

                return true;
            }
        });
    }

    private void saveClients() {
        String name = clientName.getText().toString();
        String number = clientNumber.getText().toString();

        progressBar.setVisibility(View.VISIBLE);
        ClientViewModel clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);
        Client client = new Client();
        client.name =name;
        client.phonenumber=number;
        clientViewModel.insertNewUser(client);
        progressBar.setVisibility(View.INVISIBLE);
        Toast.makeText(getApplicationContext(), "Saved", Toast.LENGTH_LONG).show();
        startActivity(new Intent(getApplicationContext(), BrowseClientActivity.class));


    }
}