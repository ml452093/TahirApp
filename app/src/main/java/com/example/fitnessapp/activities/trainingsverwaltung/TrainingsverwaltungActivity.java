package com.example.fitnessapp.activities.trainingsverwaltung;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.fitnessapp.activities.DrawerBaseActivity;
import com.example.fitnessapp.activities.trainingsverwaltung.CreatePlanActivity;
import com.example.fitnessapp.activities.trainingsverwaltung.CreateExerciseActivity;
import com.example.fitnessapp.databinding.ActivityTrainingsverwaltungBinding;


public class TrainingsverwaltungActivity extends DrawerBaseActivity {

    private ActivityTrainingsverwaltungBinding activityTrainingsverwaltungBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityTrainingsverwaltungBinding = ActivityTrainingsverwaltungBinding.inflate(getLayoutInflater());
        setContentView(activityTrainingsverwaltungBinding.getRoot());
        allocateActivityTitle("Trainingsverwaltung");
    }

    public void openCreateExercise(View button) {
        Intent i = new Intent(this, CreateExerciseActivity.class);
        i.putExtra("mode", "create");
        startActivity(i);
    }

    public void openCreatePlan(View button) {
        Intent i = new Intent(this, CreatePlanActivity.class);
        i.putExtra("mode", "create");
        startActivity(i);
    }

    //CHANGE ACTIVITY CLASSES
    public void openAddExercises(View button) {
        Intent i = new Intent(this, AddExercisesActivity.class);
        startActivity(i);
    }

    public void openAddPlans(View button) {
        Intent i = new Intent(this, AddPlansActivity.class);
        startActivity(i);
    }

    public void openEditPlanExercise(View button) {
        Intent i = new Intent(this, EditExerciseOrPlanActivity.class);
        startActivity(i);
    }
}