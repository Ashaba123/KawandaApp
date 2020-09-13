package com.learn.kawandafood.ui.auth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.User;
import com.learn.kawandafood.data.viewmodel.UserViewModel;
import com.learn.kawandafood.ui.MainActivity;

import java.util.regex.Matcher;

public class SignupActivity extends AppCompatActivity {

    private EditText etfullname;
    private EditText etemail;
    private EditText etpassword;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        intitUI();

    }

    private void intitUI() {
        Button signUpBtn = findViewById(R.id.btnSignUp);
        etfullname = findViewById(R.id.fullnames);
        etemail = findViewById(R.id.email);
        etpassword = findViewById(R.id.password);
        progressBar = findViewById(R.id.progressBar);


        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userSignup();

            }
        });
    }

    private void userSignup() {
        boolean ischecked = true;
        String fullname = etfullname.getText().toString().trim();
        String email = etemail.getText().toString().trim();
        String password = etpassword.getText().toString().trim();

        if (TextUtils.isEmpty(fullname)) {
            //email is empty
            ischecked = false;
            Toast.makeText(this, "Enter username", Toast.LENGTH_SHORT).show();
            return;
        }
        if (etfullname.getText().toString().length() < 3) {
            etfullname.setError("Username should be atleast 3 characters");
            etfullname.requestFocus();
            ischecked = false;
            return;
        }


        if (TextUtils.isEmpty(email)) {
            //email is empty
            ischecked = false;
            Toast.makeText(this, "Enter email", Toast.LENGTH_SHORT).show();
            return;
        }

        if (TextUtils.isEmpty(password)) {
            ischecked = false;
            Toast.makeText(this, "Enter password", Toast.LENGTH_SHORT).show();

            return;
        }

        //Email and Passowrd Validations;
        if (etpassword.getText().toString().length() < 6) {
            etpassword.setError("password minimum contain 6 character");
            etpassword.requestFocus();
            ischecked = false;
            return;
        }
        if (etpassword.getText().toString().equals("")) {
            etpassword.setError("please enter password");
            etpassword.requestFocus();
            ischecked = false;
            return;
        }

        //Email
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etemail.setError("please enter valid email address");
            etemail.requestFocus();
            ischecked = false;
            return;
        }
        if (etemail.getText().toString().equals("")) {
            etemail.setError("please enter email address");
            etemail.requestFocus();
            ischecked = false;
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        if (ischecked) {
            UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
            User user = new User();
            user.fullName = fullname;
            user.email = email;
            user.password = password;
            userViewModel.insertNewUser(user);

            startActivity(new Intent(SignupActivity.this, MainActivity.class));
            finish();
        }else{
            Toast.makeText(getApplicationContext(), "error adding user", Toast.LENGTH_LONG).show();
            etfullname.requestFocus();
            progressBar.setVisibility(View.INVISIBLE);
        }

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
        startActivity(new Intent(SignupActivity.this, LoginActivity.class));


    }
}
