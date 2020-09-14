package com.learn.kawandafood.ui.process;

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

import com.learn.kawandafood.data.entity.Client;
import com.learn.kawandafood.data.entity.Process;
import com.learn.kawandafood.data.viewmodel.ProcessViewModel;
import com.learn.kawandafood.ui.MainActivity;
import com.learn.kawandafood.ui.ProfileActivity;
import com.learn.kawandafood.ui.auth.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class BrowseProcessActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btnAddProcess;
    ProgressBar progressBar;
    TextView emptyData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_process);
        initUI();
    }

    private void initUI() {
        progressBar = findViewById(R.id.progressBar_process);
        emptyData = findViewById(R.id.txtProcess);
        recyclerView = findViewById(R.id.list_of_processes);

        ProcessViewModel processViewModel = new ViewModelProvider(this).get(ProcessViewModel.class);
        List<Process> processList = new ArrayList<>();
        ProcessAdapter processAdapter = new ProcessAdapter(processList);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(processAdapter);

        processViewModel.getProcesses().observe(this, new Observer<List<Process>>() {
            @Override
            public void onChanged(List<Process> processes) {
                if (processes != null) {
                    progressBar.setVisibility(View.GONE);
                    if (processes.isEmpty()) {
                        emptyData.setVisibility(View.VISIBLE);
                    } else {
                        processAdapter.setData(processes);
                        processAdapter.notifyDataSetChanged();
                    }
                }
            }
        });



        //Add a process
        btnAddProcess = findViewById(R.id.btnAddProcess);

        btnAddProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BrowseProcessActivity.this, AddProcessActivity.class));
            }
        });



        //Bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_item_home:
                        startActivity(new Intent(BrowseProcessActivity.this, MainActivity.class));
                        break;
                    case R.id.menu_item_process:
                        startActivity(new Intent(BrowseProcessActivity.this, BrowseProcessActivity.class));
                        break;
                    case R.id.menu_item_profile:
                        startActivity(new Intent(BrowseProcessActivity.this, ProfileActivity.class));
                        break;

                }

                return true;
            }
        });
    }
}
