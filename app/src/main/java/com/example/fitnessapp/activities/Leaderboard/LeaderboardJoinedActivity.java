package com.example.fitnessapp.activities.Leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.Toast;

import com.example.fitnessapp.FitnessAppAndroidApplication;
import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.DrawerBaseActivity;
import com.example.fitnessapp.databinding.ActivityLeaderboardBinding;
import com.example.fitnessapp.databinding.ActivityLeaderboardJoinedBinding;
import com.example.fitnessapp.dto.LeaderBoardTO;
import com.example.fitnessapp.model.Filter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderboardJoinedActivity extends DrawerBaseActivity {

    private ActivityLeaderboardJoinedBinding activityLeaderboardJoinedBinding;
    private BottomNavigationView bottomNavigationView;
    private FitnessAppAndroidApplication fitnessApp;
    private ArrayList<LeaderBoardTO> leaderBoardList;
    private ListLeaderboardAdapter listLeaderboardAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLeaderboardJoinedBinding = ActivityLeaderboardJoinedBinding.inflate(getLayoutInflater());
        setContentView(activityLeaderboardJoinedBinding.getRoot());
        this.fitnessApp = (FitnessAppAndroidApplication) getApplication();
        allocateActivityTitle("Joined Leaderboards");

        leaderBoardList = new ArrayList<>();

        listLeaderboardAdapter = new ListLeaderboardAdapter(leaderBoardList, this, fitnessApp, true, getApplicationContext());

        this.initBottomNavigation();
        this.getJoinedLeaderboards();
        this.initListView();

    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.joined);
    }

    private void getJoinedLeaderboards() {
        Call<ArrayList<LeaderBoardTO>> call = this.fitnessApp.getLeaderBoardService().getJoinedLeaderBoards("Bearer " + this.fitnessApp.getJwt());
        call.enqueue(new Callback<ArrayList<LeaderBoardTO>>() {
            @Override
            public void onResponse(Call<ArrayList<LeaderBoardTO>> call, Response<ArrayList<LeaderBoardTO>> response) {
                if(response.isSuccessful()) {
                    leaderBoardList.clear();
                    for(int i = 0; i < response.body().size(); i++) {
                        leaderBoardList.add(response.body().get(i));
                    }
                    listLeaderboardAdapter.notifyDataSetChanged();

                } else {
                    showToast("Communication error occured. " + response.message());
                }
            }
            @Override
            public void onFailure(Call<ArrayList<LeaderBoardTO>> call, Throwable t) {
                showToast("Communication error occured. Try again!");
            }
        });
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
        activityLeaderboardJoinedBinding.leaderboardListview.setAdapter(listLeaderboardAdapter);
        activityLeaderboardJoinedBinding.leaderboardListview.setClickable(true);
        activityLeaderboardJoinedBinding.leaderboardListview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                //TODO: Hier ein neue Activity/Fragment starten -> und leaderBoardList.get(i) mitgeben;

            }
        });
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}