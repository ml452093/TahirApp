package com.example.fitnessapp.service;

import com.example.fitnessapp.dto.ExerciseDto;
import com.example.fitnessapp.dto.ExercisePlanDto;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ITrainingManagementService {

    @POST("/rest/exercises")
    public Call<ExerciseDto> saveExercise(@Header("Authorization") String jwt, @Body ExerciseDto exerciseDto);

    @POST("/rest/exercises/plans")
    public Call<ExercisePlanDto> savePlan(@Header("Authorization") String jwt, @Body ExercisePlanDto exercisePlanDto);

    @GET("/rest/exercises/plans/byName")
    public Call<List<ExercisePlanDto>> searchExercisePlansByName(@Header("Authorization") String jwt, @Query("name") String name);

    @GET("/rest/exercises/byName")
    public Call<List<ExerciseDto>> searchExercisesByName(@Header("Authorization") String jwt, @Query("name") String name);

    @PATCH("/rest/exercises/edit")
    public Call<ExerciseDto> editExercise(@Header("Authorization") String jwt, @Body ExerciseDto exerciseDto);

    @PATCH("/rest/exercises/plans/edit")
    public Call<ExercisePlanDto> editPlan(@Header("Authorization") String jwt, @Body ExercisePlanDto exercisePlanDto);

    @PATCH("/rest/exercises/addUser")
    public Call<ExerciseDto> addUserToExercise(@Header("Authorization") String jwt, @Query("exercise") Integer exerciseId, @Query("user") Integer userId);

    @PATCH("/rest/exercises/plans/addUser")
    public Call<ExercisePlanDto> addUserToExercisePlan(@Header("Authorization") String jwt, @Query("plan") Integer planId, @Query("user") Integer userId);

    @DELETE("/rest/exercises")
    public Call<ResponseBody> deleteExercise(@Header("Authorization") String jwt, @Query("id") Integer exerciseId);

    @DELETE("/rest/exercises/plans")
    public Call<ResponseBody> deletePlan(@Header("Authorization") String jwt, @Query("id") Integer planId);
}