package com.example.fitnessapp.activities;

import android.os.Bundle;

import com.example.fitnessapp.databinding.ActivityCreateExerciseBinding;

public class CreateExerciseActivity extends DrawerBaseActivity {

    private ActivityCreateExerciseBinding activityCreateExerciseBinding;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateExerciseBinding = ActivityCreateExerciseBinding.inflate(getLayoutInflater());
        setContentView(activityCreateExerciseBinding.getRoot());
        allocateActivityTitle("Übung erstellen");
    }
}
