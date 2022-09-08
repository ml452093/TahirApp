package com.example.fitnessapp.activities.Leaderboard;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fitnessapp.FitnessAppAndroidApplication;
import com.example.fitnessapp.R;
import com.example.fitnessapp.activities.MainActivity;
import com.example.fitnessapp.dto.LeaderBoardTO;

import java.util.ArrayList;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class ListLeaderboardAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<LeaderBoardTO> leaderBoardList;
    private Context context;
    private boolean joinedLeaderboard;
    private FitnessAppAndroidApplication fitnessApp;
    private Context applicationContext;


    public ListLeaderboardAdapter(ArrayList<LeaderBoardTO> list, Context context, FitnessAppAndroidApplication fitnessApp, boolean joinedLeaderboard, Context applicationContext) {
        this.leaderBoardList = list;
        this.context = context;
        this.fitnessApp = fitnessApp;
        this.joinedLeaderboard = joinedLeaderboard;
        this.applicationContext = applicationContext;
    }

    @Override
    public int getCount() {
        return leaderBoardList.size();
    }

    @Override
    public LeaderBoardTO getItem(int pos) {
        return leaderBoardList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return leaderBoardList.get(pos).getId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = LayoutInflater.from(context).inflate(R.layout.list_item_leaderboard, parent, false);
        }

        LeaderBoardTO leaderBoardTO = getItem(position);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.out.println("CLICKED IN LISTADAPTER");
                Intent intent = new Intent(view.getContext(), ShowLeaderboardActivity.class);
                intent.putExtra("leaderboardId", leaderBoardTO.getId());
                //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                view.getContext().startActivity(intent);

            }
        });



        TextView leaderboardName = view.findViewById(R.id.leaderboard_name);
        TextView startDate = view.findViewById(R.id.start_date);
        TextView endDate = view.findViewById(R.id.end_date);
        Button joinButton = view.findViewById(R.id.join_button);

        //SET MainLeaderboard Join invisible
        if(leaderBoardTO.getId() ==1) {
            joinButton.setVisibility(View.INVISIBLE);
        }

        if(joinedLeaderboard) {
            joinButton.setText("Leave");
        }else {
            joinButton.setText("Join");
        }

        leaderboardName.setText(leaderBoardTO.getName());
        startDate.setText(leaderBoardTO.getStartDate());
        endDate.setText(leaderBoardTO.getEndDate());

        joinButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                leaveOrJoinLeaderboard(leaderBoardTO, joinButton);
            }
        });


        return view;
    }

    private void leaveOrJoinLeaderboard(LeaderBoardTO leaderBoardTO, Button joinButton){
        if(joinedLeaderboard){
            Call<ResponseBody> call = fitnessApp.getLeaderBoardService().leaveLeaderBoard(leaderBoardTO.getId(), "Bearer " + fitnessApp.getJwt());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        joinButton.setClickable(false);
                        joinButton.setText("Leaved");
                    } else {
                        showToast("Communication error occured. " + response.message());
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    showToast("Communication error occured. Try again!");
                    System.out.println(t);
                }
            });
        }else {
            Call<ResponseBody> call = fitnessApp.getLeaderBoardService().joinLeaderBoard(leaderBoardTO.getId(), "Bearer " + fitnessApp.getJwt());
            call.enqueue(new Callback<ResponseBody>() {
                @Override
                public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                    if(response.isSuccessful()) {
                        joinButton.setClickable(false);
                        joinButton.setText("Joined");
                    } else {
                        showToast("Communication error occured. " + response.message());
                    }
                }
                @Override
                public void onFailure(Call<ResponseBody> call, Throwable t) {
                    showToast("Communication error occured. Try again!");
                    System.out.println(t);
                }
            });
        }
    }

    private void showToast(String message) {
        Toast toast = Toast.makeText(context, message, Toast.LENGTH_SHORT);
        toast.show();
    }
}
