package com.example.expensetracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class CalculateFragment extends Fragment {

    RecyclerView recyclerView;
    ArrayList<Member> membersList = new ArrayList<>();

    public CalculateFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_calculate, container, false);

        recyclerView = v.findViewById(R.id.recycler);

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        Context context = this.getContext();
        SharedPreferences sp = context.getSharedPreferences("sp", Context.MODE_PRIVATE);

        Set<String> MembersNames = new HashSet<>();


        MembersNames = sp.getStringSet("MembersSet", null);
        int n = MembersNames.size();
        ArrayList<Member> MembersNamesList = new ArrayList<>(n);

      /*  //converting the set to an arraylist

        MembersNamesList = new ArrayList<Member>(n);
        for (String x : MembersNames) {
            Member member = new Member();
            member.setMembername(x.toString());
            MembersNamesList.add(member);
        } */

        //get hashmap from shared prefs
        String storedHashMapString = sp.getString("hashMapString", "didn't work");
        Gson gson = new Gson();

        java.lang.reflect.Type type = new TypeToken<HashMap<String, Double>>(){}.getType();
        HashMap<String, Double> hmap = gson.fromJson(storedHashMapString, type);
        Double sum = 0.0;
        Member member = new Member();
        for(String x: MembersNames) {
            member.setMembername(x.toString());
            for(String key: hmap.keySet()) {
                if(x.equals(key)) {
                    Double value = hmap.get(key);
                    sum += value;
                    member.setExpense(sum);
                    MembersNamesList.add(member);
                }
            }
        }

        recyclerView.setAdapter(new calcMembersAdapter(MembersNamesList));


        return v;
    }
}