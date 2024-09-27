package com.example.tp3_bdd;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import java.util.List;

public class SportsAdapter extends BaseAdapter {
    private Context context;
    private List<Sports> sportsList;

    public SportsAdapter(Context context, List<Sports> sportsList) {
        this.context = context;
        this.sportsList = sportsList;
    }

    @Override
    public int getCount() {
        return sportsList.size();
    }

    @Override
    public Object getItem(int position) {
        return sportsList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.sports_item, parent, false);
        }

        Sports sport = sportsList.get(position);

        TextView nameTextView = convertView.findViewById(R.id.sport_name);
        TextView categoryTextView = convertView.findViewById(R.id.sport_category);
        TextView playersTextView = convertView.findViewById(R.id.sport_players);

        nameTextView.setText(sport.getName());
        categoryTextView.setText(sport.getCategory());
        playersTextView.setText(String.valueOf(sport.getNumberOfPlayers()));

        return convertView;
    }
}
