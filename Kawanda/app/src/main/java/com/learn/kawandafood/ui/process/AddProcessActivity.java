package com.learn.kawandafood.ui.process;

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
import com.learn.kawandafood.data.entity.Process;
import com.learn.kawandafood.data.viewmodel.ProcessViewModel;
import com.learn.kawandafood.ui.clients.BrowseClientActivity;

public class AddProcessActivity extends AppCompatActivity {

    private Button saveProcess;
    private EditText processName;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_process);
        initUI();
    }

    private void initUI() {
        processName = findViewById(R.id.processname);
        saveProcess = findViewById(R.id.btnSaveProcess);
        progressBar = findViewById(R.id.progressBar_proses);


        saveProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addProcess();
            }
        });


    }

    private void addProcess() {
        progressBar.setVisibility(View.VISIBLE);
        String processTitle = processName.getText().toString().trim();

        ProcessViewModel processViewModel = new ViewModelProvider(this).get(ProcessViewModel.class);
        Process process1 = new Process();
        process1.name = processTitle;
        processViewModel.insertProcess(process1);
        Toast.makeText(getApplicationContext(), "Saved Process", Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.INVISIBLE);
        startActivity(new Intent(getApplicationContext(), BrowseProcessActivity.class));
        finish();
    }
}
