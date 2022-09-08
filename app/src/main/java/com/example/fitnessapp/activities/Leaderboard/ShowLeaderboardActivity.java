package com.example.fitnessapp.activities.Leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.fitnessapp.FitnessAppAndroidApplication;
import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.DrawerBaseActivity;
import com.example.fitnessapp.databinding.ActivityLeaderboardMainBinding;
import com.example.fitnessapp.databinding.ActivityShowLeaderboardBinding;
import com.example.fitnessapp.dto.LeaderBoardTO;
import com.example.fitnessapp.dto.UserTO;
import com.example.fitnessapp.model.Filter;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ShowLeaderboardActivity extends DrawerBaseActivity {

    ActivityShowLeaderboardBinding activityShowLeaderboardBinding;
    FitnessAppAndroidApplication fitnessApp;
    private ArrayList<UserTO> userList;
    private ListUserAdapter listUserAdapter;
    private Button scoreFilterBT;
    private Button nameFilterBT;
    private int leaderboardId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityShowLeaderboardBinding = ActivityShowLeaderboardBinding.inflate(getLayoutInflater());
        setContentView(activityShowLeaderboardBinding.getRoot());
        this.fitnessApp = (FitnessAppAndroidApplication) getApplication();
        allocateActivityTitle("Leaderboard übersicht");

        this.leaderboardId = getIntent().getIntExtra("leaderboardId", 0);

        System.out.println("LEADERBOARD ID:");
        System.out.println(leaderboardId);

        this.scoreFilterBT = activityShowLeaderboardBinding.scoreFilter;
        this.nameFilterBT = activityShowLeaderboardBinding.nameFilter;

        this.setButtonClicked(scoreFilterBT);

        this.scoreFilterBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonClicked(scoreFilterBT);
                setButtonClickable(nameFilterBT);
                getLeaderboard(leaderboardId, Filter.SCORE);
            }
        });

        this.nameFilterBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setButtonClicked(nameFilterBT);
                setButtonClickable(scoreFilterBT);
                getLeaderboard(leaderboardId, Filter.NAME);
            }
        });

        userList = new ArrayList<>();
        listUserAdapter = new ListUserAdapter(userList, this);

        this.getLeaderboard(leaderboardId, Filter.SCORE);
        this.initListView();
    }

    /*
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), )
    }

     */

    private void setButtonClickable(Button button) {
        button.setBackgroundResource(R.color.light_green);
        button.setClickable(true);
    }

    private void setButtonClicked(Button button) {
        button.setBackgroundResource(R.color.brown);
        button.setClickable(false);
    }

    public void getLeaderboard(int leaderboardId, Filter filter) {
        Call<LeaderBoardTO> call = this.fitnessApp.getLeaderBoardService().getLeaderBoard(leaderboardId, filter, "Bearer " + this.fitnessApp.getJwt());
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

    private void initListView() {
        activityShowLeaderboardBinding.userListview.setAdapter(listUserAdapter);
        activityShowLeaderboardBinding.userListview.setClickable(false);
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}