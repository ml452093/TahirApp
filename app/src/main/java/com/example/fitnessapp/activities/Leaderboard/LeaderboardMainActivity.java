package com.example.fitnessapp.activities.Leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Toast;

import com.example.fitnessapp.FitnessAppAndroidApplication;
import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.DrawerBaseActivity;
import com.example.fitnessapp.databinding.ActivityLeaderboardBinding;
import com.example.fitnessapp.databinding.ActivityLeaderboardMainBinding;
import com.example.fitnessapp.dto.LeaderBoardTO;
import com.example.fitnessapp.dto.UserTO;
import com.example.fitnessapp.model.Filter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderboardMainActivity extends DrawerBaseActivity {

    ActivityLeaderboardMainBinding activityLeaderboardMainBinding;
    BottomNavigationView bottomNavigationView;
    FitnessAppAndroidApplication fitnessApp;
    private ArrayList<UserTO> userList;
    private ListUserAdapter listUserAdapter;
    private Button scoreFilterBT;
    private Button nameFilterBt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLeaderboardMainBinding = ActivityLeaderboardMainBinding.inflate(getLayoutInflater());
        setContentView(activityLeaderboardMainBinding.getRoot());
        this.fitnessApp = (FitnessAppAndroidApplication) getApplication();
        allocateActivityTitle("Hauptleaderboard");

        this.scoreFilterBT = activityLeaderboardMainBinding.scoreFilter;
        this.nameFilterBt = activityLeaderboardMainBinding.nameFilter;

        this.setButtonClicked(scoreFilterBT);

        this.scoreFilterBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonClicked(scoreFilterBT);
                setButtonClickable(nameFilterBt);
                getMainLeaderboard(Filter.SCORE);
            }
        });

        this.nameFilterBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonClicked(nameFilterBt);
                setButtonClickable(scoreFilterBT);
                getMainLeaderboard(Filter.NAME);
            }
        });


        userList = new ArrayList<>();
        listUserAdapter = new ListUserAdapter(userList, this);

        this.initBottomNavigation();
        this.getMainLeaderboard(Filter.SCORE);
        this.initListView();

    }

    private void setButtonClickable(Button button) {
        button.setBackgroundResource(R.color.light_green);
        button.setClickable(true);
    }

    private void setButtonClicked(Button button) {
        button.setBackgroundResource(R.color.brown);
        button.setClickable(false);
    }

    private void getMainLeaderboard(Filter filter) {
        Call<LeaderBoardTO> call = this.fitnessApp.getLeaderBoardService().getMainLeaderBoard(filter, "Bearer " + this.fitnessApp.getJwt());
        System.out.println("Get Joined Leaderboards");
        call.enqueue(new Callback<LeaderBoardTO>() {
            @Override
            public void onResponse(Call<LeaderBoardTO> call, Response<LeaderBoardTO> response) {
                if(response.isSuccessful()) {
                    userList.clear();
                    List<UserTO> users = response.body().getUsers();
                    for(int i = 0; i < users.size(); i++) {
                        userList.add(users.get(i));
                    }
                    listUserAdapter.notifyDataSetChanged();

                } else {
                    showToast("Communication error occured. " + response.message());
                }
            }
            @Override
            public void onFailure(Call<LeaderBoardTO> call, Throwable t) {
                showToast("Communication error occured. Try again!");
                System.out.println(t);
            }
        });
    }

    private void initBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.main);
        bottomNavigationView.setOnItemSelectedListener( item -> {
            switch (item.getItemId()) {
                case R.id.main:
                    return true;
                case R.id.joined:
                    Log.i("Bottom Navigation", item.getTitle().toString() + " item selected");
                    Intent leaderboardJoinedActivityIntent = new Intent(getApplicationContext(), LeaderboardJoinedActivity.class);
                    leaderboardJoinedActivityIntent.setFlags(leaderboardJoinedActivityIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(leaderboardJoinedActivityIntent);
                    overridePendingTransition(0,0);
                    return true;
                case R.id.publicity:
                    Log.i("Bottom Navigation", item.getTitle().toString() + " item selected");
                    Intent leaderboardPublicActivityIntent = new Intent(getApplicationContext(), LeaderboardPublicActivity.class);
                    leaderboardPublicActivityIntent.setFlags(leaderboardPublicActivityIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(leaderboardPublicActivityIntent);
                    overridePendingTransition(0,0);
                    return true;
            }
            return false;
        });
    }

    private void initListView() {
        activityLeaderboardMainBinding.userListview.setAdapter(listUserAdapter);
        activityLeaderboardMainBinding.userListview.setClickable(false);
        /*
        activityLeaderboardMainBinding.userListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //TODO: Hier ein neue Activity/Fragment starten -> und leaderBoardList.get(i) mitgeben;

            }
        });

         */
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.main);
    }
}