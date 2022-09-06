package com.example.fitnessapp.activities.Leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.fitnessapp.R;
import com.example.fitnessapp.dto.LeaderBoardTO;

import java.util.ArrayList;

class ListLeaderboardAdapter extends ArrayAdapter<LeaderBoardTO> {

    public ListLeaderboardAdapter(Context context, ArrayList<LeaderBoardTO> leaderboardArrayList) {
        super(context, R.layout.list_item_leaderboard, leaderboardArrayList);
    }



    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LeaderBoardTO leaderBoardTO = getItem(position);

        if(convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_leaderboard, parent, false);
        }

        TextView leaderboardName = convertView.findViewById(R.id.leaderboard_name);
        TextView startDate = convertView.findViewById(R.id.start_date);
        TextView endDate = convertView.findViewById(R.id.end_date);
        Button joinButton = convertView.findViewById(R.id.join_button);

        leaderboardName.setText(leaderBoardTO.getName());
        startDate.setText(leaderBoardTO.getStartDate().toString());
        endDate.setText(leaderBoardTO.getEndDate().toString());


        return super.getView(position, convertView, parent);
    }
}
