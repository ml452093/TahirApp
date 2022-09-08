package com.example.fitnessapp.activities.trainingsverwaltung;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.DrawerBaseActivity;
import com.example.fitnessapp.databinding.ActivityEditExerciseOrPlanBinding;

public class EditExerciseOrPlanActivity extends DrawerBaseActivity {

    private ActivityEditExerciseOrPlanBinding activityEditExerciseOrPlanBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEditExerciseOrPlanBinding = ActivityEditExerciseOrPlanBinding.inflate(getLayoutInflater());
        setContentView(activityEditExerciseOrPlanBinding.getRoot());
        allocateActivityTitle("Trainingsverwaltung");
    }
}