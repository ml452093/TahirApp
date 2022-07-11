package com.example.fitnessapp.service;

import com.example.fitnessapp.model.Filter;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ILeaderBoardService {
    @GET("/rest/leaderboard/getMainLeaderBoard/{filter}")
    public Call<ResponseBody> getMainLeaderBoard(@Path("filter") Filter filter, @Header("Authorization") String jwt);

    @GET("/rest/leaderboard/{leaderBoardId}/{filter}")
    public Call<ResponseBody> getLeaderBoard(@Path("filter") Filter filter, @Header ("Authorization") String jwt);

    @GET("/rest/leaderboard/getJoinedLeaderBoards")
    public Call<ResponseBody> getJoinedLeaderBoards(@Header ("Authorization") String jwt);

    @POST("/rest/leaderboard/joinLeaderBoard/{leaderBoardId}")
    public Call<ResponseBody> joinLeaderBoard(@Path("leaderBoardId") int leaderBoardId, @Header ("Authorization") String jwt);

    @DELETE("/rest/leaderboard/leaveLeaderBoard/{leaderBoardId}")
    public Call<ResponseBody> leaveLeaderBoard(@Path("leaderBoardId") int leaderBoardId, @Header ("Authorization") String jwt);

    @GET("/rest/leaderboard/getAllActiveLeaderBoards")
    public Call<ResponseBody> getAllActiveLeaderBoards(@Header ("Authorization") String jwt);

    @PUT("/rest/leaderboard/createLeaderBoard")
    public Call <ResponseBody> createLeaderBoard(@Header ("Authorization") String jwt);
}
