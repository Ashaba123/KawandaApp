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
import com.learn.kawandafood.data.entity.Process;
import com.learn.kawandafood.data.entity.SubProcess;
import com.learn.kawandafood.data.viewmodel.ProcessViewModel;
import com.learn.kawandafood.data.viewmodel.SubProcessViewModel;
import com.learn.kawandafood.ui.MainActivity;
import com.learn.kawandafood.ui.ProfileActivity;
import com.learn.kawandafood.ui.auth.LoginActivity;

import java.util.ArrayList;
import java.util.List;

public class BrowseSubProcessActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    FloatingActionButton btnAddSubProcess;
    ProgressBar progressBar;
    TextView emptyData;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse_sub_process);
        initUi();

    }

    private void initUi() {
        progressBar = findViewById(R.id.progressBar_sub_process);
        emptyData = findViewById(R.id.txtSubProcess);
        recyclerView = findViewById(R.id.list_of_sub_processes);

        //get process id from the selected process
        Bundle extrasBundle = getIntent().getExtras();
        id = extrasBundle.getInt("id");

        SubProcessViewModel subProcessViewModel = new ViewModelProvider(this).get(SubProcessViewModel.class);
        List<SubProcess> subProcesses = new ArrayList<>();
        SubProcessAdapter subProcessAdapter = new SubProcessAdapter(subProcesses);


        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(subProcessAdapter);

        subProcessViewModel.getSubProcesses(id).observe(this, new Observer<List<SubProcess>>() {
            @Override
            public void onChanged(List<SubProcess> subProcesses) {
                if (subProcesses != null) {
                    progressBar.setVisibility(View.GONE);
                    if (subProcesses.isEmpty()) {
                        emptyData.setVisibility(View.VISIBLE);
                    } else {
                        subProcessAdapter.setData(subProcesses);
                        subProcessAdapter.notifyDataSetChanged();
                    }
                }
            }
        });


        //Add a sub process
        btnAddSubProcess = findViewById(R.id.btnAddSubProcess);

        btnAddSubProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), BrowseSubProcessActivity.class);
                intent.putExtra("id", id);
                startActivity(intent);
                finish();
            }
        });


        //Bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_item_home:
                        startActivity(new Intent(BrowseSubProcessActivity.this, MainActivity.class));
                        break;
                    case R.id.menu_item_process:
                        startActivity(new Intent(BrowseSubProcessActivity.this, BrowseProcessActivity.class));
                        break;
                    case R.id.menu_item_profile:
                        startActivity(new Intent(BrowseSubProcessActivity.this, ProfileActivity.class));
                        break;

                    case R.id.menu_item_out:
                        startActivity(new Intent(BrowseSubProcessActivity.this, LoginActivity.class));
                        finish();
                        break;
                }

                return true;
            }
        });
    }
}
