package com.example.fitnessapp;

import android.app.Application;

import com.example.fitnessapp.service.ILeaderBoardService;
import com.example.fitnessapp.service.ITrainingManagementService;
import com.example.fitnessapp.service.IUserService;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class FitnessAppAndroidApplication extends Application {

    private IUserService userService;
    private ILeaderBoardService leaderBoardService;
    private ITrainingManagementService trainingManagementService;

    private String jwt;

    public FitnessAppAndroidApplication() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.userService = retrofit.create(IUserService.class);
        this.leaderBoardService = retrofit.create(ILeaderBoardService.class);
        this.trainingManagementService = retrofit.create(ITrainingManagementService.class);
    }

    public void registerNewUser() {

    }

    public IUserService getUserService() { return this.userService; }
    public ILeaderBoardService getLeaderBoardService() { return  this.leaderBoardService; }
    public ITrainingManagementService getTrainingManagementService() { return this.trainingManagementService; }

    public String getJwt() { return jwt; }

    public void setJwt(String jwt) {this.jwt = jwt;}
}
