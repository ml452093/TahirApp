package com.example.fitnessapp.service;

import com.example.fitnessapp.dto.UserTO;
import com.example.fitnessapp.model.Filter;

import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface IUserService {
    @POST("/rest/user/mockRegister")
    public Call<ResponseBody> register(@Body UserTO userTO);
}
