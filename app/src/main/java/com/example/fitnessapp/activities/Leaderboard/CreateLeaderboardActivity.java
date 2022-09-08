package com.example.fitnessapp.activities.Leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.example.fitnessapp.FitnessAppAndroidApplication;
import com.example.fitnessapp.activities.DrawerBaseActivity;
import com.example.fitnessapp.databinding.ActivityCreateLeaderboardBinding;
import com.example.fitnessapp.databinding.ActivityLeaderboardPublicBinding;
import com.example.fitnessapp.dto.LeaderBoardTO;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateLeaderboardActivity extends DrawerBaseActivity {

    ActivityCreateLeaderboardBinding activityCreateLeaderboardBinding;
    FitnessAppAndroidApplication fitnessApp;
    DatePickerDialog endDateDialog;
    Button endDateBT;
    Button createButton;
    EditText leaderBoardName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateLeaderboardBinding = ActivityCreateLeaderboardBinding.inflate(getLayoutInflater());
        setContentView(activityCreateLeaderboardBinding.getRoot());
        this.fitnessApp = (FitnessAppAndroidApplication) getApplication();
        allocateActivityTitle("Create Leaderboard");


        initDatePicker();

        endDateBT = activityCreateLeaderboardBinding.endDateSpinner;
        endDateBT.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openEndDatePicker(view);
            }
        });

        endDateBT.setText(getTodaysDate());

        createButton = activityCreateLeaderboardBinding.createButton;
        createButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createNewLeaderboard();
            }
        });

    }

    private void createNewLeaderboard() {
        String startDate = getTodaysDate() + " 00:00:00";
        String endDate = endDateBT.getText().toString()  + " 23:59:59";
        String leaderBoardName = activityCreateLeaderboardBinding.leaderboardNameEt.getText().toString();

        if(leaderBoardName.length() < 4) {
            showToast("Name des Leaderboards zu kurz");
            return;
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime startDateLD = LocalDateTime.parse(startDate, formatter);
        LocalDateTime endDateLD = LocalDateTime.parse(endDate, formatter);
        System.out.println(startDate);
        System.out.println(startDateLD);
        System.out.println(endDate);
        System.out.println(endDateLD);
        System.out.println(leaderBoardName);

        Call<ResponseBody> call = this.fitnessApp.getLeaderBoardService().createLeaderBoard(leaderBoardName, startDate, endDate, "Bearer " + this.fitnessApp.getJwt());
        System.out.println("Get Public Leaderboards");
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                if(response.isSuccessful()) {
                    showToast("SUCCESS");
                    Intent i = new Intent(getApplicationContext(), LeaderboardPublicActivity.class);
                    startActivity(i);
                } else {
                    showToast("Communication error occured. " + response.message());
                }
            }
            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                showToast("Communication error occured. Try again!");
            }
        });


    }

    private String getTodaysDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month +1;
        int day = cal.get(Calendar.DAY_OF_MONTH);

        return  makeDateString(day, month, year);
    }

    private void initDatePicker() {

        DatePickerDialog.OnDateSetListener endDateSetter = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = makeDateString(day, month, year);
                endDateBT.setText(date);
            }
        };
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);

        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_LIGHT;



        endDateDialog = new DatePickerDialog(this, style, endDateSetter, year, month, day);
    }

    public void openEndDatePicker(View view) {
        endDateDialog.show();
    }


    private String makeDateString(int day, int month, int year) {
        String monthString = Integer.toString(month);
        String dayString = Integer.toString(day);
        if(month < 10) {
            monthString = "0" + month;
        }

        if(day < 10){
            dayString = "0" + day;
        }

        return year + "-" + monthString + "-" + dayString;
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(this, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}