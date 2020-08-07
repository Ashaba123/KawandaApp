package com.learn.kawandafood.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.User;
import com.learn.kawandafood.data.viewmodel.UserViewModel;
import com.learn.kawandafood.ui.auth.LoginActivity;
import com.learn.kawandafood.ui.process.BrowseProcessActivity;

import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    TextView email;
    TextView username;
    EditText newPassword;
    Button btnSavePassword;
    ProgressBar progressBar;
    UserViewModel userViewModel;
    List<User> userList;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        initUI();
    }

    private void initUI() {

        username = findViewById(R.id.username_profile);
        email = findViewById(R.id.user_email);
        newPassword = findViewById(R.id.new_password);
        btnSavePassword = findViewById(R.id.btnSavePassword);
        progressBar = findViewById(R.id.progressBar);
        userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        userViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                userList = users;
                for (User user : users) {
                    id = user.getUid();
                    username.setText(user.getFullName());
                    email.setText(user.getEmail());

                }
            }
        });


        btnSavePassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editPassword();
            }
        });


        //Bottom navigation
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

                switch (menuItem.getItemId()) {
                    case R.id.menu_item_home:
                        startActivity(new Intent(ProfileActivity.this, MainActivity.class));
                        break;
                    case R.id.menu_item_process:
                        startActivity(new Intent(ProfileActivity.this, BrowseProcessActivity.class));
                        break;
                    case R.id.menu_item_profile:
                        startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));
                        break;

                    case R.id.menu_item_out:
                        startActivity(new Intent(ProfileActivity.this, LoginActivity.class));
                        finish();
                        break;
                }

                return true;
            }
        });
    }

    private void editPassword() {
        progressBar.setVisibility(View.VISIBLE);
        boolean ischecked = true;
        String password = newPassword.getText().toString().trim();

        if (TextUtils.isEmpty(password)) {
            ischecked = false;
            progressBar.setVisibility(View.INVISIBLE);
            btnSavePassword.setVisibility(View.VISIBLE);
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();

            return;
        }

        if (newPassword.getText().toString().length() < 6) {
            newPassword.setError("password minimum contain 6 character");
            newPassword.requestFocus();
            progressBar.setVisibility(View.INVISIBLE);
            btnSavePassword.setVisibility(View.VISIBLE);
            ischecked = false;
            return;
        }
        if (newPassword.getText().toString().equals("")) {
            newPassword.setError("please enter password");
            newPassword.requestFocus();
            progressBar.setVisibility(View.INVISIBLE);
            btnSavePassword.setVisibility(View.VISIBLE);
            ischecked = false;
            return;
        }

        userViewModel.changepassword(id, password);
        btnSavePassword.setVisibility(View.VISIBLE);
        newPassword.setText("");
        Toast.makeText(this, "Successfully changed Password", Toast.LENGTH_LONG).show();

    }
}
