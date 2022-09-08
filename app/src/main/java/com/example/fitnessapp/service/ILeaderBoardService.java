package com.example.fitnessapp.service;

import com.example.fitnessapp.dto.LeaderBoardTO;
import com.example.fitnessapp.dto.UserTO;
import com.example.fitnessapp.model.Filter;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface ILeaderBoardService {
    @GET("/rest/leaderBoard/{filter}")
    public Call<LeaderBoardTO> getMainLeaderBoard(@Path("filter") Filter filter, @Header("Authorization") String jwt);

    @GET("/rest/leaderBoard/{leaderBoardId}/{filter}")
    public Call<LeaderBoardTO> getLeaderBoard(@Path("leaderBoardId")int leaderBoardid, @Path("filter") Filter filter, @Header ("Authorization") String jwt);

    @GET("/rest/leaderBoard/getJoinedLeaderBoards")
    public Call<ArrayList<LeaderBoardTO>> getJoinedLeaderBoards(@Header ("Authorization") String jwt);

    @POST("/rest/leaderBoard/joinLeaderBoard/{leaderBoardId}")
    public Call<ResponseBody> joinLeaderBoard(@Path("leaderBoardId") int leaderBoardId, @Header ("Authorization") String jwt);

    @DELETE("/rest/leaderBoard/leaveLeaderBoard/{leaderBoardId}")
    public Call<ResponseBody> leaveLeaderBoard(@Path("leaderBoardId") int leaderBoardId, @Header ("Authorization") String jwt);

    @GET("/rest/leaderBoard/getAllActiveLeaderBoards")
    public Call<ArrayList<LeaderBoardTO>> getAllActiveLeaderBoards(@Header ("Authorization") String jwt);

    @PUT("/rest/leaderBoard/createLeaderBoard")
    public Call <ResponseBody> createLeaderBoard(@Query("name") String name, @Query("startDate") String startDate, @Query("endDate") String endDate, @Header ("Authorization") String jwt);
}
