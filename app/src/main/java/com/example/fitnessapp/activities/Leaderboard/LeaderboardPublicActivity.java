package com.example.fitnessapp.activities.Leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.example.fitnessapp.FitnessAppAndroidApplication;
import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.DrawerBaseActivity;
import com.example.fitnessapp.databinding.ActivityLeaderboardBinding;
import com.example.fitnessapp.databinding.ActivityLeaderboardPublicBinding;
import com.example.fitnessapp.dto.LeaderBoardTO;
import com.example.fitnessapp.model.Role;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaderboardPublicActivity extends DrawerBaseActivity {

    ActivityLeaderboardPublicBinding activityLeaderboardPublicBinding;
    BottomNavigationView bottomNavigationView;
    FitnessAppAndroidApplication fitnessApp;
    private ArrayList<LeaderBoardTO> leaderBoardList;
    private ListLeaderboardAdapter listLeaderboardAdapter;
    private FloatingActionButton floatingButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityLeaderboardPublicBinding = ActivityLeaderboardPublicBinding.inflate(getLayoutInflater());
        setContentView(activityLeaderboardPublicBinding.getRoot());
        this.fitnessApp = (FitnessAppAndroidApplication) getApplication();
        allocateActivityTitle("Öffentliche Leaderboards");
        floatingButton = activityLeaderboardPublicBinding.floatingButton;

        if(fitnessApp.getUserRole()== Role.ADMIN){

            floatingButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getApplicationContext(), CreateLeaderboardActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                }
            });
        }else {
            floatingButton.setVisibility(View.INVISIBLE);
        }


        leaderBoardList = new ArrayList<>();
        listLeaderboardAdapter = new ListLeaderboardAdapter(leaderBoardList, this, fitnessApp, false, getApplicationContext());

        this.initBottomNavigation();
        this.getPublicLeaderboards();
        this.initListView();
    }

    private void getPublicLeaderboards() {
        Call<ArrayList<LeaderBoardTO>> call = this.fitnessApp.getLeaderBoardService().getAllActiveLeaderBoards("Bearer " + this.fitnessApp.getJwt());
        System.out.println("Get Public Leaderboards");
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

    private void initListView() {
        activityLeaderboardPublicBinding.publicLeaderboardLv.setAdapter(listLeaderboardAdapter);
        activityLeaderboardPublicBinding.publicLeaderboardLv.setClickable(true);

        activityLeaderboardPublicBinding.publicLeaderboardLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                System.out.println("ITEM CLICKED");
                LeaderBoardTO leaderBoard = (LeaderBoardTO) activityLeaderboardPublicBinding.publicLeaderboardLv.getItemAtPosition(i);
                System.out.println(leaderBoard.getId());
                //Intent intent = new Intent(getApplicationContext(), ShowLeaderboardActivity.class);
                //intent.putExtra("leaderBoardId",)
                //TODO: Hier ein neue Activity/Fragment starten -> und leaderBoardList.get(i) mitgeben;

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        bottomNavigationView.setSelectedItemId(R.id.publicity);
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}