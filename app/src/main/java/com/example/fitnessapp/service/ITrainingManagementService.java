package com.example.fitnessapp.service;

import com.example.fitnessapp.dto.ExerciseDto;
import com.example.fitnessapp.dto.ExercisePlanDto;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ITrainingManagementService {

    @POST("/rest/exercises")
    public Call<ExerciseDto> saveExercise(@Header("Authorization") String jwt, @Body ExerciseDto exerciseDto);

    @POST("/rest/exercises/plans")
    public Call<ExercisePlanDto> savePlan(@Header("Authorization") String jwt, @Body ExercisePlanDto exercisePlanDto);

    @GET("/rest/exercises")
    public Call<List<ExerciseDto>> searchExercises(@Header("Authorization") String jwt);

    @GET("/rest/exercises/byName")
    public Call<List<ExerciseDto>> searchExercisesByName(@Header("Authorization") String jwt, @Body String name);

}