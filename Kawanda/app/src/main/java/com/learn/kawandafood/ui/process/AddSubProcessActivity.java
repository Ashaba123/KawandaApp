package com.learn.kawandafood.ui.process;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.Process;
import com.learn.kawandafood.data.entity.SubProcess;
import com.learn.kawandafood.data.viewmodel.ProcessViewModel;
import com.learn.kawandafood.data.viewmodel.SubProcessViewModel;

public class AddSubProcessActivity extends AppCompatActivity {

    private Button saveSubProcess;
    private EditText subProcessName;
    private ProgressBar progressBar;
    private int id;
    private CheckBox chked;
    private boolean isChecked;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_sub_process);
        initUI();
    }

    private void initUI() {
        subProcessName = findViewById(R.id.sub_processname);
        saveSubProcess = findViewById(R.id.btnSaveSubProcess);
        progressBar = findViewById(R.id.progressBar_proses);

        if (chked.isChecked()) {
           isChecked= true;
        } else {
            isChecked =false;
        }


        Bundle extrasBundle = getIntent().getExtras();
        if (extrasBundle != null) {
            id = extrasBundle.getInt("id");
        }
        saveSubProcess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSubProcess();
            }
        });
    }

    private void addSubProcess() {
        progressBar.setVisibility(View.VISIBLE);
        String subProcessTitle = subProcessName.getText().toString().trim();

        SubProcessViewModel subProcessViewModel = new ViewModelProvider(this).get(SubProcessViewModel.class);
        SubProcess subProcess = new SubProcess();
        subProcess.process_id = id;
        subProcess.checked =isChecked;
        subProcess.name = subProcessTitle;
        subProcessViewModel.insertSubProcess(subProcess);
        Toast.makeText(getApplicationContext(), "Saved Sub Process Successfully", Toast.LENGTH_LONG).show();
        progressBar.setVisibility(View.INVISIBLE);
        startActivity(new Intent(getApplicationContext(), BrowseProcessActivity.class));
        finish();
    }
}
