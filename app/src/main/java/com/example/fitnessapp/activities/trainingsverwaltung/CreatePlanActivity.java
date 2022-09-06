package com.example.fitnessapp.activities.trainingsverwaltung;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.FitnessAppAndroidApplication;
import com.example.fitnessapp.R;
import com.example.fitnessapp.dto.ExerciseDto;
import com.example.fitnessapp.dto.ExercisePlanDto;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;

public class CreatePlanActivity extends AppCompatActivity {

    private FitnessAppAndroidApplication fitnessApp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);
        this.fitnessApp = (FitnessAppAndroidApplication) getApplication();
    }

    public void savePlan(View view) {
        EditText editName = (EditText) findViewById(R.id.createPlanName);
        String name =  editName.getText().toString();
        List<ExerciseDto> exerciseDtoList = new ArrayList<>();
        Integer userId = 0;
        ExercisePlanDto exercisePlanDto = new ExercisePlanDto(name, exerciseDtoList, userId);
        Call<ExercisePlanDto> call = this.fitnessApp.getTrainingManagementService().savePlan("Bearer " + this.fitnessApp.getJwt(), exercisePlanDto);
    }

    public void searchExercises(View view) {
        EditText editName = (EditText) findViewById(R.id.createPlanExerciseSearchName);
        String name = editName.getText().toString();
        if (name == ""){
            Call<List<ExerciseDto>> call = this.fitnessApp.getTrainingManagementService().searchExercises("Bearer " + this.fitnessApp.getJwt());
        }else {
            Call<List<ExerciseDto>> call = this.fitnessApp.getTrainingManagementService().searchExercisesByName("Bearer " + this.fitnessApp.getJwt(), name);
        }

    }
}