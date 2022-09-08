package com.example.fitnessapp.activities.trainingsverwaltung;

import android.app.LauncherActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.fitnessapp.FitnessAppAndroidApplication;
import com.example.fitnessapp.R;
import com.example.fitnessapp.dto.ExerciseDto;
import com.example.fitnessapp.dto.ExercisePlanDto;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;

public class CreatePlanActivity extends AppCompatActivity {

    private FitnessAppAndroidApplication fitnessApp;

    ListView listView;
    ArrayAdapter<String> adapter;
    String[] exercises = {"Test1", "Test2", "Test3", "Test2", "Test3", "Test2", "Test3", "Test2", "Test3", "Test2", "Test3", "Test2", "Test3"};
    List<ExerciseDto> exerciseList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_plan);
        this.fitnessApp = (FitnessAppAndroidApplication) getApplication();
        renderResults();
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
        Call<List<ExerciseDto>> call = this.fitnessApp.getTrainingManagementService().searchExercisesByName("Bearer " + this.fitnessApp.getJwt(), name);

    }

    public void renderResults() {
        listView = findViewById(R.id.createplan_listview);
        adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_multiple_choice, exercises);
        listView.setAdapter(adapter);

    }

    public void showClicked(View view) {
        listView = findViewById(R.id.createplan_listview);
        SparseBooleanArray array = listView.getCheckedItemPositions();
        StringBuilder sb = new StringBuilder();
        List<ExerciseDto> list = new ArrayList<>();
        for(int i = 0; i< array.size(); i++){
            if(array.valueAt(i)==true){
                ExerciseDto o = (ExerciseDto) listView.getItemAtPosition(i);
                System.out.println(o.toString());
                list.add(o);
            }
        }
        this.exerciseList = list;
    }

    public void submitPlan(String name) {
        ExercisePlanDto planDto = new ExercisePlanDto();
        planDto.setName(name);
        //Find out how to get user info
        planDto.setCreator(1);
        planDto.setExercises(this.exerciseList);
        Call<ExercisePlanDto> call = this.fitnessApp.getTrainingManagementService().savePlan("Bearer " + this.fitnessApp.getJwt(),planDto);
    }

}