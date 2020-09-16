package com.learn.kawandafood.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;

import com.learn.kawandafood.R;
import com.learn.kawandafood.data.entity.User;
import com.learn.kawandafood.data.viewmodel.UserViewModel;
import com.learn.kawandafood.ui.auth.LoginActivity;
import com.learn.kawandafood.ui.intro.IntroPage;

import java.util.ArrayList;
import java.util.List;

public class SplashScreenActivity extends AppCompatActivity {

    private static final long SPLASH_SCREEN_TIME = 1800L;
    private int isloggedIn ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //full screen display of splash screen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        UserViewModel userViewModel = new ViewModelProvider(this).get(UserViewModel.class);
        userViewModel.getUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(List<User> users) {
                for(User user :users){
                    isloggedIn =user.isLoggedIn;
                }
            }
        });

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isloggedIn==0) {
                    startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    finish();
                }
            }
        }, SPLASH_SCREEN_TIME);

    }
}
