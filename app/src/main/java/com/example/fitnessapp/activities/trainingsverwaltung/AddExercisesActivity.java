package com.example.fitnessapp.activities.trainingsverwaltung;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.DrawerBaseActivity;
import com.example.fitnessapp.databinding.ActivityAddExercisesBinding;

public class AddExercisesActivity extends DrawerBaseActivity {

    private ActivityAddExercisesBinding activityAddExercisesBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddExercisesBinding = ActivityAddExercisesBinding.inflate(getLayoutInflater());
        setContentView(activityAddExercisesBinding.getRoot());
        allocateActivityTitle("Trainingsverwaltung");
    }
}