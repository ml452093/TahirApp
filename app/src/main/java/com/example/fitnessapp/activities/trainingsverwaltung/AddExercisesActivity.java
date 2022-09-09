package com.example.fitnessapp.activities.trainingsverwaltung;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.example.fitnessapp.FitnessAppAndroidApplication;
import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.DrawerBaseActivity;
import com.example.fitnessapp.databinding.ActivityAddExercisesBinding;
import com.example.fitnessapp.dto.ExerciseDto;
import com.example.fitnessapp.dto.ExercisePlanDto;
import com.example.fitnessapp.dto.UserTO;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddExercisesActivity extends DrawerBaseActivity {

    private ActivityAddExercisesBinding activityAddExercisesBinding;
    private FitnessAppAndroidApplication fitnessApp;

    List<UserTO> users = new ArrayList<>();
    List<UserTO> selectedUsers = new ArrayList<>();
    List<ExerciseDto> exercises = new ArrayList<>();
    List<ExerciseDto> selectedExercises = new ArrayList<>();
    ListView listViewUsers;
    ListView listViewExercises;
    ArrayAdapter<UserTO> userAdapter;
    ArrayAdapter<ExerciseDto> exerciseAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddExercisesBinding = ActivityAddExercisesBinding.inflate(getLayoutInflater());
        setContentView(activityAddExercisesBinding.getRoot());
        allocateActivityTitle("Trainingsverwaltung");
        this.fitnessApp = (FitnessAppAndroidApplication) getApplication();
        searchUsers(null);
        searchExercises(null);
    }

    public void searchUsers(View button) {
        EditText editName = (EditText) findViewById(R.id.addexercise_user_edittext);
        String name = editName.getText().toString();
        //Call and response handling
        renderUsers();
    }

    public void searchExercises(View button) {
        EditText editName = (EditText) findViewById(R.id.addexercise_exercise_edittext);
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

    public void selectUsers(View button) {
        listViewUsers = findViewById(R.id.addexercise_user_listview);
        SparseBooleanArray array = listViewUsers.getCheckedItemPositions();
        for(int i = 0; i< array.size(); i++){
            if(array.valueAt(i)==true){
                UserTO o = (UserTO) listViewUsers.getItemAtPosition(i);
                selectedUsers.add(o);
            }
        }
    }

    public void selectExercises(View button) {
        listViewExercises = findViewById(R.id.addexercise_exercise_listview);
        SparseBooleanArray array = listViewExercises.getCheckedItemPositions();
        for(int i = 0; i< array.size(); i++){
            if(array.valueAt(i)==true){
                ExerciseDto o = (ExerciseDto) listViewExercises.getItemAtPosition(i);
                selectedExercises.add(o);
            }
        }
    }

    public void clearUserSelection(View button) {
        selectedUsers.clear();
    }

    public void clearExerciseSelection(View button) {
        selectedExercises.clear();
    }

    public void submitAddPlans(View button) {
        for(int i = 0; i < selectedUsers.size(); i++){
            for(int j = 0; j < selectedExercises.size(); j++){
                Call<ExerciseDto> call = this.fitnessApp.getTrainingManagementService().addUserToExercise("Bearer " + this.fitnessApp.getJwt(), selectedExercises.get(j).getId(), selectedUsers.get(i).getId());
            }
        }
    }

    public void renderUsers() {
        listViewUsers = findViewById(R.id.createplan_listview);
        UserTO[] userArray = users.toArray(new UserTO[0]);
        userAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, userArray);
        listViewUsers.setAdapter(userAdapter);
    }

    public void renderExercises() {
        listViewExercises = findViewById(R.id.createplan_listview);
        ExerciseDto[] exerciseArray = exercises.toArray(new ExerciseDto[0]);
        exerciseAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_multiple_choice, exerciseArray);
        listViewExercises.setAdapter(exerciseAdapter);
    }
}