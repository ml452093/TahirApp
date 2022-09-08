package com.example.fitnessapp.activities.trainingsverwaltung;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;

import com.example.fitnessapp.FitnessAppAndroidApplication;
import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.DrawerBaseActivity;
import com.example.fitnessapp.databinding.ActivityAddPlansBinding;
import com.example.fitnessapp.dto.ExerciseDto;
import com.example.fitnessapp.dto.ExercisePlanDto;
import com.example.fitnessapp.dto.UserTO;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AddPlansActivity extends DrawerBaseActivity {

    private ActivityAddPlansBinding activityAddPlansBinding;
    private FitnessAppAndroidApplication fitnessApp;

    List<UserTO> users = new ArrayList<>();
    List<UserTO> selectedUsers = new ArrayList<>();
    List<ExercisePlanDto> plans = new ArrayList<>();
    List<ExercisePlanDto> selectedPlans = new ArrayList<>();
    ListView listviewUsers;
    ListView listViewPlans;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddPlansBinding = ActivityAddPlansBinding.inflate(getLayoutInflater());
        setContentView(activityAddPlansBinding.getRoot());
        allocateActivityTitle("Trainingsverwaltung");
        this.fitnessApp = (FitnessAppAndroidApplication) getApplication();
    }

    //Change Service to UserService and make Request there / Mock it
    public void searchUsers(View button) {
        EditText editName = (EditText) findViewById(R.id.addplan_user_edittext);
        String name = editName.getText().toString();
        Call<List<ExercisePlanDto>> call = this.fitnessApp.getTrainingManagementService().searchExercisePlansByName("Bearer " + this.fitnessApp.getJwt(), name);
        call.enqueue(new Callback<List<ExercisePlanDto>>() {
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
    }

    public void searchPlans(View button) {
        EditText editName = (EditText) findViewById(R.id.addplan_plan_edittext);
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
    }

    public void selectUsers(View button) {
        listviewUsers = findViewById(R.id.addplan_user_listview);
        SparseBooleanArray array = listviewUsers.getCheckedItemPositions();
        for(int i = 0; i< array.size(); i++){
            if(array.valueAt(i)==true){
                UserTO o = (UserTO) listviewUsers.getItemAtPosition(i);
                selectedUsers.add(o);
            }
        }
    }

    public void selectPlans(View button) {
        listViewPlans = findViewById(R.id.addplan_plan_listview);
        SparseBooleanArray array = listViewPlans.getCheckedItemPositions();
        for(int i = 0; i< array.size(); i++){
            if(array.valueAt(i)==true){
                ExercisePlanDto o = (ExercisePlanDto) listViewPlans.getItemAtPosition(i);
                selectedPlans.add(o);
            }
        }
    }

    public void clearUserSelection(View button) {
        selectedUsers.clear();
    }

    public void clearPlanSelection(View button) {
        selectedPlans.clear();
    }

    public void submit(View button) {
        for(int i = 0; i < selectedUsers.size(); i++){
            for(int j; j < selectedPlans.size(); j++){
                Call<ExercisePlanDto> call = this.fitnessApp.getTrainingManagementService().addUserToExercisePlan("Bearer " + this.fitnessApp.getJwt(), selectedPlans.get(j).getId(), selectedUsers.get(i).getId());
            }
        }
    }
}