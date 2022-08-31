package com.example.fitnessapp.dto;

import java.util.List;
import java.util.Set;

public class ExercisePlanDto {
    private Integer planId;
    private String name;
    private List<ExerciseDto> exercises;
    private Integer creatorId;

    //Default Constructor
    public ExercisePlanDto(){}

    //Full Constructor
    public ExercisePlanDto(String name, List<ExerciseDto> exerciseList, Integer creatorId){
        this.name = name;
        this.exercises = exerciseList;
        this.creatorId = creatorId;
    }

    public Integer getId() {
        return planId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExerciseDto> getExercises() {
        return exercises;
    }

    public void setExercises(List<ExerciseDto> exercises) {
        this.exercises = exercises;
    }

    public Integer getCreator() {
        return creatorId;
    }

    public void setCreator(Integer cId) {
        this.creatorId = cId;
    }
}