package com.example.fitnessapp.activities.trainingsverwaltung;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.fitnessapp.FitnessAppAndroidApplication;
import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.DrawerBaseActivity;
import com.example.fitnessapp.databinding.ActivityEditExerciseOrPlanBinding;
import com.example.fitnessapp.dto.ExerciseDto;
import com.example.fitnessapp.dto.ExercisePlanDto;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class EditExerciseOrPlanActivity extends DrawerBaseActivity {

    private ActivityEditExerciseOrPlanBinding activityEditExerciseOrPlanBinding;
    private FitnessAppAndroidApplication fitnessApp;

    List<ExerciseDto> exercises = new ArrayList<>();
    List<ExercisePlanDto> plans = new ArrayList<>();
    ListView listViewExercises;
    ListView listViewPlans;
    ArrayAdapter<ExerciseDto> exerciseAdapter;
    ArrayAdapter<ExercisePlanDto> planAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityEditExerciseOrPlanBinding = ActivityEditExerciseOrPlanBinding.inflate(getLayoutInflater());
        setContentView(activityEditExerciseOrPlanBinding.getRoot());
        allocateActivityTitle("Trainingsverwaltung");
        this.fitnessApp = (FitnessAppAndroidApplication) getApplication();
        this.searchExercisesEdit(null);
        this.searchPlansEdit(null);
    }

    public void searchExercisesEdit(View button) {
        EditText editName = (EditText) findViewById(R.id.edittext_editplanexercise_editexercise);
        String name = editName.getText().toString();
        Call<List<ExerciseDto>> call = this.fitnessApp.getTrainingManagementService().searchExercisesByName("Bearer " + this.fitnessApp.getJwt(), name);
        call.enqueue(new Callback<List<ExerciseDto>>() {
            @Override
            public void onResponse(Call<List<ExerciseDto>> call, Response<List<ExerciseDto>> response) {
                if(response.isSuccessful()) {
                    exercises = response.body();
                }else {

                }
            }

            @Override
            public void onFailure(Call<List<ExerciseDto>> call, Throwable t) {

            }
        });
        renderExercises();
    }

    public void searchPlansEdit(View button) {
        EditText editName = (EditText) findViewById(R.id.edittext_editplanexercise_editplan);
        String name = editName.getText().toString();
        Call<List<ExercisePlanDto>> call = this.fitnessApp.getTrainingManagementService().searchExercisePlansByName("Bearer " + this.fitnessApp.getJwt(), name);
        call.enqueue(new Callback<List<ExercisePlanDto>>() {
            @Override
            public void onResponse(Call<List<ExercisePlanDto>> call, Response<List<ExercisePlanDto>> response) {
                if(response.isSuccessful()) {
                    plans = response.body();
                }else {

                }
            }

            @Override
            public void onFailure(Call<List<ExercisePlanDto>> call, Throwable t) {

            }
        });
        renderPlans();
    }

    public void editExercise(View button) {
        listViewExercises = findViewById(R.id.editplanexercise_exercise_listview);
        Integer index = listViewExercises.getCheckedItemPosition();
        ExerciseDto exerciseDto = exercises.get(index);
        Intent i = new Intent(this, CreateExerciseActivity.class);
        i.putExtra("mode", "edit");
        i.putExtra("dto", exerciseDto);
        startActivity(i);
    }

    public void deleteExercise(View button) {
        listViewExercises = findViewById(R.id.editplanexercise_exercise_listview);
        Integer index = listViewExercises.getCheckedItemPosition();
        ExerciseDto exerciseDto = exercises.get(index);
        Call<ResponseBody> call = this.fitnessApp.getTrainingManagementService().deleteExercise("Bearer " + this.fitnessApp.getJwt(), exerciseDto.getId());
    }

    public void editPlan(View button) {
        listViewPlans = findViewById(R.id.editplanexercise_plan_listview);
        Integer index = listViewPlans.getCheckedItemPosition();
        ExercisePlanDto planDto = plans.get(index);
        Intent i = new Intent(this, CreatePlanActivity.class);
        i.putExtra("mode", "edit");
        i.putExtra("dto", planDto);
        startActivity(i);
    }

    public void deletePlan(View button) {
        listViewPlans = findViewById(R.id.editplanexercise_plan_listview);
        Integer index = listViewPlans.getCheckedItemPosition();
        ExercisePlanDto planDto = plans.get(index);
        Call<ResponseBody> call = this.fitnessApp.getTrainingManagementService().deletePlan("Bearer " + this.fitnessApp.getJwt(), planDto.getId());
    }

    public void renderExercises() {
        listViewExercises = findViewById(R.id.editplanexercise_exercise_listview);
        ExerciseDto[] exerciseArray = exercises.toArray(new ExerciseDto[0]);
        exerciseAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, exerciseArray);
        listViewExercises.setAdapter(exerciseAdapter);
    }

    public void renderPlans() {
        listViewPlans = findViewById(R.id.editplanexercise_plan_listview);
        ExercisePlanDto[] planArray = plans.toArray(new ExercisePlanDto[0]);
        planAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, planArray);
        listViewPlans.setAdapter(planAdapter);
    }
}