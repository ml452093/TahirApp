package com.example.fitnessapp.activities.Leaderboard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.example.fitnessapp.R;
import com.example.fitnessapp.dto.LeaderBoardTO;
import com.example.fitnessapp.dto.UserTO;

import java.util.ArrayList;

class ListUserAdapter extends BaseAdapter implements ListAdapter {

    private ArrayList<UserTO> userList;
    private Context context;

    public ListUserAdapter(ArrayList<UserTO> list, Context context) {
        this.userList = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return userList.size();
    }

    @Override
    public UserTO getItem(int pos) {
        return userList.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return userList.get(pos).getId();
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = LayoutInflater.from(context).inflate(R.layout.list_item_user, parent, false);
        }

        UserTO userTO = getItem(position);

        TextView userName = view.findViewById(R.id.user_name);
        TextView score = view.findViewById(R.id.score_tv);

        userName.setText(userTO.getName());
        score.setText(Integer.toString(userTO.getTotalScore()));

        return view;
    }
}
