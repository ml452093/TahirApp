package com.example.fitnessapp.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

public enum Role {
    @SerializedName("0")
    ADMIN,
    @SerializedName("1")
    MEMBER;
}
