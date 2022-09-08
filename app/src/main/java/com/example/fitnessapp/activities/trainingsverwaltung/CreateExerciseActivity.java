package com.example.fitnessapp.activities.trainingsverwaltung;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.fitnessapp.FitnessAppAndroidApplication;
import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.DrawerBaseActivity;
import com.example.fitnessapp.databinding.ActivityCreateExerciseBinding;
import com.example.fitnessapp.dto.ExerciseDto;

import retrofit2.Call;

public class CreateExerciseActivity extends DrawerBaseActivity {

    private ActivityCreateExerciseBinding activityCreateExerciseBinding;
    private FitnessAppAndroidApplication fitnessApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityCreateExerciseBinding = ActivityCreateExerciseBinding.inflate(getLayoutInflater());
        setContentView(activityCreateExerciseBinding.getRoot());
        allocateActivityTitle("Übung erstellen");
        this.fitnessApp = (FitnessAppAndroidApplication) getApplication();
    }

    public void submitExercise(View button){
        ExerciseDto exerciseDto = new ExerciseDto();
        // Get Input fields
        EditText editName = (EditText) findViewById(R.id.createExerciseName);
        EditText editWeight = (EditText) findViewById(R.id.createExerciseWeight);
        EditText editReps = (EditText) findViewById(R.id.createExerciseReps);
        EditText editSets = (EditText) findViewById(R.id.createExerciseSets);
        EditText editPoints = (EditText) findViewById(R.id.createExercisePoints);
        //Set properties of dto to input values
        exerciseDto.setName(editName.getText().toString());
        exerciseDto.setWeight(Double.parseDouble(editWeight.getText().toString()));
        exerciseDto.setNumberOfReps(Integer.parseInt(editReps.getText().toString()));
        exerciseDto.setNumberOfSets(Integer.parseInt(editSets.getText().toString()));
        exerciseDto.setPoints(Double.parseDouble(editPoints.getText().toString()));
        Call<ExerciseDto> call = this.fitnessApp.getTrainingManagementService().saveExercise(this.fitnessApp.getJwt(), exerciseDto);
    }
}
