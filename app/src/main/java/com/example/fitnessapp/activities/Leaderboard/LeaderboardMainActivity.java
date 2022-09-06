package com.example.fitnessapp.activities.Leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.DrawerBaseActivity;
import com.example.fitnessapp.databinding.ActivityLeaderboardBinding;
import com.example.fitnessapp.databinding.ActivityLeaderboardMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LeaderboardMainActivity extends DrawerBaseActivity {

    ActivityLeaderboardMainBinding activityLeaderboardMainBinding;
    BottomNavigationView bottomNavigationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLeaderboardMainBinding = ActivityLeaderboardMainBinding.inflate(getLayoutInflater());
        setContentView(activityLeaderboardMainBinding.getRoot());
        allocateActivityTitle("Hauptleaderboard");

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

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.main);
    }
}