package com.example.expensetracker;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;


public class HomeFragment extends Fragment {

    private TextView tripId;
    private TextView tripName;
    private TextView MembersCount;
    private TextView Leader;

    View view;

    public HomeFragment() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);

        Context context = this.getContext();
        SharedPreferences sp = context.getSharedPreferences("sp", Context.MODE_PRIVATE);
        String name = sp.getString("name", "");
        String LeaderName = sp.getString("LeaderName", "");
        int id = sp.getInt("id", 0);
        int count = sp.getInt("count", 0);

        tripId = view.findViewById(R.id.ID1);
        tripName = view.findViewById(R.id.TName);
        MembersCount = view.findViewById(R.id.MembersCount);
        Leader = view.findViewById(R.id.LName);

        tripId.setText(Integer.toString(id));
        tripName.setText(name);
        Leader.setText(LeaderName);
        MembersCount.setText(Integer.toString(count));

        return view;

    }



}