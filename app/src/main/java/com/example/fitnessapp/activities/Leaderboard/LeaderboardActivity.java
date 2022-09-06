package com.example.fitnessapp.activities.Leaderboard;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;

import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.DrawerBaseActivity;
import com.example.fitnessapp.databinding.ActivityLeaderboardBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.android.material.navigation.NavigationView;

public class LeaderboardActivity extends DrawerBaseActivity {


    ActivityLeaderboardBinding activityLeaderboardBinding;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLeaderboardBinding = ActivityLeaderboardBinding.inflate(getLayoutInflater());
        setContentView(activityLeaderboardBinding.getRoot());
        allocateActivityTitle("Leaderboard");

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.main);
        bottomNavigationView.setOnItemSelectedListener( item -> {
            switch (item.getItemId()) {
                case R.id.main:
                    Log.i("Bottom Navigation", item.getTitle().toString() + " item selected");
                    Intent leaderBoardMainActivityIntent = new Intent(getApplicationContext(), LeaderboardMainActivity.class);
                    leaderBoardMainActivityIntent.setFlags(leaderBoardMainActivityIntent.getFlags() | Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(leaderBoardMainActivityIntent);
                    overridePendingTransition(0,0);
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

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.main);
    }
}