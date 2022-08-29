package com.example.fitnessapp.activities;

import android.os.Bundle;

import com.example.fitnessapp.databinding.ActivityLeaderboardBinding;

public class LeaderboardActivity extends DrawerBaseActivity {


    ActivityLeaderboardBinding activityLeaderboardBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLeaderboardBinding = ActivityLeaderboardBinding.inflate(getLayoutInflater());
        setContentView(activityLeaderboardBinding.getRoot());
        allocateActivityTitle("Leaderboard");
    }
}