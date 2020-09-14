package com.learn.kawandafood.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.User;
import com.learn.kawandafood.data.viewmodel.UserViewModel;
import com.learn.kawandafood.ui.MainActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnLoginIn;
    private EditText usernameEt;
    private EditText passwordEt;
    private TextView signUp;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);
        intitUI();

    }

    private void intitUI() {
        btnLoginIn = findViewById(R.id.btnSignIn);
        usernameEt = findViewById(R.id.username);
        passwordEt = findViewById(R.id.password);
        signUp = findViewById(R.id.textViewSignUp);
        progressBar = findViewById(R.id.progressBar_login);
        btnLoginIn.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == btnLoginIn) {
            userLogin();
        }

        if (v == signUp) {
            startActivity(new Intent(this, SignupActivity.class));
            finish();
        }

    }

    private void userLogin() {
        boolean ischecked = true;
        String name = usernameEt.getText().toString().trim();
        String password = passwordEt.getText().toString().trim();


        if (TextUtils.isEmpty(name)) {
            //email is empty
            ischecked = false;
            Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show();

            //stop executing futher
            return;
        }
        if (TextUtils.isEmpty(password)) {
            ischecked = false;
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();

            return;
        }


        //loading
        progressBar.setVisibility(View.VISIBLE);
        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);

        if (ischecked) {
            userViewModel.getUser(name, password).observe(this, new Observer<User>() {
                @Override
                public void onChanged(User user) {
                    if (user != null) {
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                        Toast.makeText(getApplicationContext(), "Welcome", Toast.LENGTH_LONG).show();
                        finish();

                    } else {
                        Toast.makeText(getApplicationContext(), "Invalid email or password", Toast.LENGTH_LONG).show();
                        usernameEt.requestFocus();
                        progressBar.setVisibility(View.INVISIBLE);
                    }
                }
            });


        }


    }
}
