package com.example.fitnessapp.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import com.example.fitnessapp.FitnessAppAndroidApplication;
import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.Leaderboard.LeaderboardActivity;
import com.example.fitnessapp.databinding.ActivityLeaderboardJoinedBinding;
import com.example.fitnessapp.databinding.ActivityMainBinding;
import com.example.fitnessapp.dto.UserTO;
import com.example.fitnessapp.model.Role;
import com.google.android.material.navigation.NavigationView;

import java.io.IOException;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private Button loginButton;
    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());
        //setContentView(R.layout.activity_main);

        activityMainBinding.loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //TODO: Register Methode einbinden anstatt das Intent zu starten
                //register();

                Intent i = new Intent(getApplicationContext(), LeaderboardActivity.class);
                i.setFlags(i.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                startActivity(i);


            }
        });
    }

    public void register () {

        UserTO userTO = new UserTO();
        userTO.setHeight(202);
        userTO.setEmail("Ingo13@gmail.com");
        userTO.setPublic(true);
        userTO.setName("IngoAdmin");
        userTO.setRole(Role.MEMBER);
        userTO.setWeight(90);

        final FitnessAppAndroidApplication myApp = (FitnessAppAndroidApplication) getApplication();

        Call<ResponseBody> call = myApp.getUserService().register(userTO);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    try {
                        String jwt = response.body().string();
                        myApp.setJwt(jwt);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    Intent intent = new Intent(getApplicationContext(), LeaderboardActivity.class);
                    intent.setFlags(intent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);

                } else {
                    showToast("Login failed: Check Credentials");
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showToast("Communication error occured");
                Log.e("SignInActivity", "Communication error: " + t.getMessage());
            }
        });
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

}