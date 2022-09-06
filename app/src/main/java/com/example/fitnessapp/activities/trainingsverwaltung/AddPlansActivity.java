package com.example.fitnessapp.activities.trainingsverwaltung;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.DrawerBaseActivity;
import com.example.fitnessapp.databinding.ActivityAddPlansBinding;

public class AddPlansActivity extends DrawerBaseActivity {

    private ActivityAddPlansBinding activityAddPlansBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityAddPlansBinding = ActivityAddPlansBinding.inflate(getLayoutInflater());
        setContentView(activityAddPlansBinding.getRoot());
        allocateActivityTitle("Trainingsverwaltung");
    }
}