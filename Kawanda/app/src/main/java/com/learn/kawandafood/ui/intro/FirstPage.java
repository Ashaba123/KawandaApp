package com.learn.kawandafood.ui.intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.User;
import com.learn.kawandafood.data.viewmodel.UserViewModel;

import java.util.List;

public class FirstPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_page);
        initUI();
    }

    private void initUI() {

        Button next = findViewById(R.id.btnFirstNext);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FirstPage.this, SecondPage.class));

            }
        });
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
