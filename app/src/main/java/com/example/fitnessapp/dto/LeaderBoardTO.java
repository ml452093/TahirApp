package com.example.fitnessapp.dto;

import com.example.fitnessapp.model.Filter;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class LeaderBoardTO {
    private int id;
    private String name;
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    private List<UserTO> users;

    public LeaderBoardTO() {}

    public LeaderBoardTO(String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LeaderBoardTO(int id, String name, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
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

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
    }

    public List<UserTO> getUsers() {
        return users;
    }

    public void setUsers(List<UserTO> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "LeaderBoard{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LeaderBoardTO that = (LeaderBoardTO) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, startDate, endDate);
    }
}
