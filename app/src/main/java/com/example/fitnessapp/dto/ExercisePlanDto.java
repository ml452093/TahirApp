package com.example.fitnessapp.dto;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Set;

public class ExercisePlanDto implements Parcelable {
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

    //Parcel Constructor
    public ExercisePlanDto(Parcel in) {
        planId = in.readInt();
        name = in.readString();
        exercises = in.readArrayList(ExercisePlanDto.class.getClassLoader());
        creatorId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(planId);
        out.writeString(name);
        out.writeList(exercises);
        out.writeInt(creatorId);
    }

    public static final Parcelable.Creator<ExercisePlanDto> CREATOR = new Parcelable.Creator<ExercisePlanDto>() {
        public ExercisePlanDto createFromParcel(Parcel in) {
            return new ExercisePlanDto(in);
        }

        public ExercisePlanDto[] newArray(int size) {
            return new ExercisePlanDto[size];
        }
    };

    public Integer getId() {
        return planId;
    }

    public void setId (Integer id) {this.planId = id; }

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