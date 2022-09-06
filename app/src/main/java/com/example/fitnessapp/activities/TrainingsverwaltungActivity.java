package com.example.fitnessapp.activities;


import android.os.Bundle;

import com.example.fitnessapp.databinding.ActivityTrainingsverwaltungBinding;


public class TrainingsverwaltungActivity extends DrawerBaseActivity {

    ActivityTrainingsverwaltungBinding activityTrainingsverwaltungBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTrainingsverwaltungBinding = ActivityTrainingsverwaltungBinding.inflate(getLayoutInflater());
        setContentView(activityTrainingsverwaltungBinding.getRoot());
        allocateActivityTitle("Trainingsverwaltung");
    }
}