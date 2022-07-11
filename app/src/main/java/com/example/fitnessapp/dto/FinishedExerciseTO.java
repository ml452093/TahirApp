package com.example.fitnessapp.dto;

import java.time.LocalDateTime;
import java.util.Objects;

public class FinishedExerciseTO {

    private int id;

    private String name;
    private String description;
    private int repetitions;
    private int sets;
    private double weight;
    private int score;

    private LocalDateTime date;


    public FinishedExerciseTO() {

    }

    public FinishedExerciseTO(String name, String description, int repetitions, int sets, double weight, int score, LocalDateTime date) {
        this.name = name;
        this.description = description;
        this.repetitions = repetitions;
        this.sets = sets;
        this.weight = weight;
        this.score = score;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRepetitions() {
        return repetitions;
    }

    public void setRepetitions(int repetitions) {
        this.repetitions = repetitions;
    }

    public int getSets() {
        return sets;
    }

    public void setSets(int sets) {
        this.sets = sets;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "FinishedExercise{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", repetitions=" + repetitions +
                ", sets=" + sets +
                ", weight=" + weight +
                ", score=" + score +
                ", date=" + date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FinishedExerciseTO that = (FinishedExerciseTO) o;
        return id == that.id && repetitions == that.repetitions && sets == that.sets && Double.compare(that.weight, weight) == 0 && score == that.score && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, repetitions, sets, weight, score, date);
    }

}
