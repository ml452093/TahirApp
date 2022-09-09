package com.example.fitnessapp.dto;


import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;
import java.util.Set;

public class ExerciseDto implements Parcelable {

    private Integer exerciseId;
    private String name;
    private Double weight;
    private Integer numberOfReps;
    private Integer numberOfSets;
    private Double points;
    private Integer creatorId;

    //Default Constructor
    public ExerciseDto(){

    }

    //Full Constructor
    public ExerciseDto(String name, Double weight, Integer numberOfReps, Integer numberOfSets, Double points, Integer creatorId) {
        this.name = name;
        this.weight = weight;
        this.numberOfReps = numberOfReps;
        this.numberOfSets = numberOfSets;
        this.creatorId = creatorId;
        this.points = points;
    }

    //Parcel Constructor
    public ExerciseDto(Parcel in) {
        exerciseId = in.readInt();
        name = in.readString();
        weight = in.readDouble();
        numberOfReps = in.readInt();
        numberOfSets = in.readInt();
        points = in.readDouble();
        creatorId = in.readInt();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel out, int flags) {
        out.writeInt(exerciseId);
        out.writeString(name);
        out.writeDouble(weight);
        out.writeInt(numberOfReps);
        out.writeInt(numberOfSets);
        out.writeDouble(points);
        out.writeInt(creatorId);
    }

    public static final Parcelable.Creator<ExerciseDto> CREATOR = new Parcelable.Creator<ExerciseDto>() {
        public ExerciseDto createFromParcel(Parcel in) {
            return new ExerciseDto(in);
        }

        public ExerciseDto[] newArray(int size) {
            return new ExerciseDto[size];
        }
    };

    public Integer getId() {
        return exerciseId;
    }

    public void setId(Integer id) { this.exerciseId = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Integer getNumberOfReps() {
        return numberOfReps;
    }

    public void setNumberOfReps(Integer numberOfReps) {
        this.numberOfReps = numberOfReps;
    }

    public Integer getNumberOfSets() {
        return numberOfSets;
    }

    public void setNumberOfSets(Integer numberOfSets) {
        this.numberOfSets = numberOfSets;
    }

    public Double getPoints(){ return points;}

    public void setPoints(Double points) {this.points = points;}

    public Integer getCreator() {
        return creatorId;
    }

    public void setCreator(Integer cId) {
        this.creatorId = cId;
    }
}