package com.example.fitnessapp.activities.Leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;

import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.DrawerBaseActivity;
import com.example.fitnessapp.databinding.ActivityLeaderboardBinding;
import com.example.fitnessapp.databinding.ActivityLeaderboardJoinedBinding;
import com.example.fitnessapp.dto.LeaderBoardTO;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class LeaderboardJoinedActivity extends DrawerBaseActivity {

    ActivityLeaderboardJoinedBinding activityLeaderboardJoinedBinding;
    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLeaderboardJoinedBinding = ActivityLeaderboardJoinedBinding.inflate(getLayoutInflater());
        setContentView(activityLeaderboardJoinedBinding.getRoot());
        allocateActivityTitle("Joined Leaderboards");



        this.initBottomNavigation();
        this.initListView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.joined);
    }

    private void initBottomNavigation() {
        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setSelectedItemId(R.id.joined);
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
        ArrayList<LeaderBoardTO> leaderBoardList = new ArrayList<>(); //TODO: Hier muss die Information vom Server reingeladen werden
        ListLeaderboardAdapter listLeaderboardAdapter = new ListLeaderboardAdapter(LeaderboardJoinedActivity.this, leaderBoardList);

        activityLeaderboardJoinedBinding.leaderboardListview.setAdapter(listLeaderboardAdapter);
        activityLeaderboardJoinedBinding.leaderboardListview.setClickable(true);

        activityLeaderboardJoinedBinding.leaderboardListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //TODO: Hier ein neue Activity/Fragment starten -> und leaderBoardList.get(i) mitgeben;

            }
        });
    }
}