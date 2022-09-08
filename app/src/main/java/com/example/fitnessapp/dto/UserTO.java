package com.example.fitnessapp.dto;

import java.util.List;
import java.util.Objects;
import java.util.Set;
import com.example.fitnessapp.model.*;

public class UserTO {


    private int id;

    private List<FinishedExerciseTO> finishedExercises;
    private List<LeaderBoardTO> leaderBoards;

    private String name;
    private String email;
    private int height;
    private int weight;
    private boolean isPublic;
    private Role role;

    public UserTO() {

    }

    public UserTO(String name, String email, int height, int weight, boolean isPublic, Role role) {
        this.name = name;
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.isPublic = isPublic;
        this.role = role;
    }

    public UserTO(int id, String name, String email, int height, int weight, boolean isPublic, Role role, List<FinishedExerciseTO> finishedExercises) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.height = height;
        this.weight = weight;
        this.isPublic = isPublic;
        this.role = role;
        this.finishedExercises = finishedExercises;
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

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public List<LeaderBoardTO> getLeaderBoards() { return this.leaderBoards; }

    public void setLeaderBoards(List<LeaderBoardTO> leaderBoards) { this.leaderBoards = leaderBoards; }

    public void setFinishedExercises(List<FinishedExerciseTO> finishedExercises) {
        this.finishedExercises = finishedExercises;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", height=" + height +
                ", weight=" + weight +
                ", isPublic=" + isPublic +
                ", role=" + role +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserTO user = (UserTO) o;
        return id == user.id && height == user.height && weight == user.weight && isPublic == user.isPublic && Objects.equals(name, user.name) && Objects.equals(email, user.email) && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, email, height, weight, isPublic, role);
    }

    public int getTotalScore() {
        int totalScore = 0;
        for(FinishedExerciseTO f : finishedExercises) {
            totalScore += f.getScore();
        }
        return totalScore;
    }
}
