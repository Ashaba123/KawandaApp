package com.learn.kawandafood.ui.clients;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.viewmodel.ClientViewModel;
import com.learn.kawandafood.ui.MainActivity;

import java.util.ArrayList;
import java.util.List;

public class BrowseClientActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ProgressBar progressBar;
    TextView emptyData;
    FloatingActionButton btnAddClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_client);
        initUI();
    }

    private void initUI() {
        recyclerView = findViewById(R.id.list_of_clients);
        progressBar = findViewById(R.id.progressBar_clients);
        emptyData = findViewById(R.id.txtClients);
        btnAddClient = findViewById(R.id.btnAddClient);

        btnAddClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BrowseClientActivity.this, AddClientActivity.class));
            }
        });

        ClientViewModel clientViewModel = new ViewModelProvider(this).get(ClientViewModel.class);

        List<Client> clientList = new ArrayList<>();
        ClientsAdapter clientsAdapter = new ClientsAdapter(clientList);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(clientsAdapter);

        clientViewModel.getClients().observe(this, new Observer<List<Client>>() {
            @Override
            public void onChanged(List<Client> clients) {
                if (clients != null) {
                    progressBar.setVisibility(View.GONE);
                    if (clients.isEmpty()) {
                        emptyData.setVisibility(View.VISIBLE);
                    } else {
                        clientsAdapter.setData(clients);
                        clientsAdapter.notifyDataSetChanged();
                    }
                }
            }
        });
    }
}
