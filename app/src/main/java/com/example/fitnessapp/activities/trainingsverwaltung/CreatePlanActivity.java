package com.example.fitnessapp.activities.trainingsverwaltung;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.FitnessAppAndroidApplication;
import com.example.fitnessapp.R;
import com.example.fitnessapp.dto.ExerciseDto;
import com.example.fitnessapp.dto.ExercisePlanDto;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreatePlanActivity extends AppCompatActivity {

    private FitnessAppAndroidApplication fitnessApp;

    ListView listView;
    List<ExerciseDto> exercises = new ArrayList<>();
    List<ExerciseDto> selectedExercises = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);
        this.fitnessApp = (FitnessAppAndroidApplication) getApplication();
    }

    public void submitPlan(View view) {
        //Get Name for exercise plan
        EditText editName = (EditText) findViewById(R.id.createPlanName);
        String name =  editName.getText().toString();
        Integer userId = 0;
        ExercisePlanDto exercisePlanDto = new ExercisePlanDto(name, selectedExercises, userId);
        Call<ExercisePlanDto> call = this.fitnessApp.getTrainingManagementService().savePlan("Bearer " + this.fitnessApp.getJwt(), exercisePlanDto);
    }

    public void searchExercises(View view) {
        EditText editName = (EditText) findViewById(R.id.createPlanExerciseSearchName);
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
        this.renderResults();
    }

    public void renderResults() {
        listView = findViewById(R.id.createplan_listview);
        adapter = new ListAdapter(this, android.R.layout.simple_list_item_multiple_choice, exercises);
        listView.setAdapter(adapter);
    }

    public void selectExercises(View view) {
        listView = findViewById(R.id.createplan_listview);
        SparseBooleanArray array = listView.getCheckedItemPositions();
        for(int i = 0; i< array.size(); i++){
            if(array.valueAt(i)==true){
                ExerciseDto o = (ExerciseDto) listView.getItemAtPosition(i);
                selectedExercises.add(o);
            }
        }
    }

    public void clearSelection(View button) {
        selectedExercises.clear();
    }

}