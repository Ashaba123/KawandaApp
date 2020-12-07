package com.learn.kawandafood.ui.intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.User;
import com.learn.kawandafood.data.viewmodel.UserViewModel;

import java.util.List;

public class IntroPage extends AppCompatActivity {

    private TextView usernameTxt;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_page);
        intiUi();
    }

    private void intiUi() {
        usernameTxt = findViewById(R.id.username);
        Button next = findViewById(R.id.btnIntoNext);

        //the usernametxt we ask the view model to get us the name of the user who has logged in
        //and set that name
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for (User user : users) {
                    usernameTxt.setText(user.getFullName());
                }
            }
        });

        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IntroPage.this, FirstPage.class));
            }
        });
    }
    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }
}
