package com.example.expensetracker;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MembersFragment extends Fragment {

    public MembersFragment() {
        // Required empty public constructor
    }

    LinearLayout layoutList;
    Button addButton;
    Button submit;

    ArrayList<String> MembersList = new ArrayList<>();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_members, container, false);

        Context context = this.getContext();
        SharedPreferences sp = context.getSharedPreferences("sp", Context.MODE_PRIVATE);

        layoutList = v.findViewById(R.id.listLayout);
        addButton = v.findViewById(R.id.add_member_button);
        submit = v.findViewById(R.id.members_Added);


        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView();
            }
        });

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(validateInput()) {

                    SharedPreferences.Editor editor = sp.edit();
                    Set<String> MembersSet = new HashSet<>();
                    MembersSet.addAll(MembersList);
                    editor.putStringSet("MembersSet", MembersSet);
                    editor.commit();

                    Toast.makeText(getActivity(), "Members Added Successfully!", Toast.LENGTH_SHORT).show();
                }

                }

        });

        return v;
    }

    private boolean validateInput() {
        MembersList.clear();

        boolean result = true;

        int count = layoutList.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = layoutList.getChildAt(i);

            EditText name = (EditText) v.findViewById(R.id.MemberName);

            Member member = new Member();

            if (!name.getText().toString().equals("")) {
                member.setMembername(name.getText().toString());
            } else {
                result = false;
                break;
            }

            MembersList.add(member.getMembername());
        }

        if(MembersList.size()==0){
            result = false;
            Toast.makeText(getActivity(), "Enter Members Names!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(getActivity(), "Please Enter All Info Needed!", Toast.LENGTH_SHORT).show();
        }

        return result;
    }






    private void addView() {
        final View rows = getLayoutInflater().inflate(R.layout.row_add, null, false);

        EditText MemberName = (EditText) rows.findViewById(R.id.MemberName);
        ImageView remove = rows.findViewById(R.id.remove_member);

        remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(rows);
            }
        });

        layoutList.addView(rows);
    }

    private void removeView(View view) {
        layoutList.removeView(view);
    }


}