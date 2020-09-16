package com.learn.kawandafood.ui.intro;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.learn.kawandafood.R;
import com.learn.kawandafood.ui.MainActivity;

public class FourthPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fourth_page);
        initUI();
    }

    private void initUI() {
        Button next = findViewById(R.id.btnFourthContinue);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(FourthPage.this, MainActivity.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
