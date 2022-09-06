package com.example.fitnessapp.activities.Leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.DrawerBaseActivity;
import com.example.fitnessapp.databinding.ActivityLeaderboardBinding;
import com.example.fitnessapp.databinding.ActivityLeaderboardPublicBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class LeaderboardPublicActivity extends DrawerBaseActivity {

    ActivityLeaderboardPublicBinding activityLeaderboardPublicBinding;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLeaderboardPublicBinding = ActivityLeaderboardPublicBinding.inflate(getLayoutInflater());
        setContentView(activityLeaderboardPublicBinding.getRoot());
        allocateActivityTitle("Öffentliche Leaderboards");

        bottomNavigationView = findViewById(R.id.bottom_navigation);

        bottomNavigationView.setSelectedItemId(R.id.publicity);
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
                    return true;
            }
            return false;
        });
    }
    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.publicity);
    }
}