package com.example.expensetracker;

import static android.util.Log.println;

import static java.lang.Integer.parseInt;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.widget.AppCompatSpinner;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;


public class AddExpenseFragment extends Fragment {

    LinearLayout layoutList2;
    Button addExpenseButton;
    Button done;

    Set<String> MembersNames = new HashSet<>();
    List<String> MembersNamesList = new ArrayList<>();

    HashMap<String, Double> MemberExpense = new HashMap<>();


    public AddExpenseFragment() {
        // empty constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_add_expense, container, false);

        Context context = this.getContext();
        SharedPreferences sp = context.getSharedPreferences("sp", Context.MODE_PRIVATE);

        layoutList2 = v.findViewById(R.id.listLayout2);
        addExpenseButton = v.findViewById(R.id.add_expense_button);
        done = v.findViewById(R.id.Expenses_Added);

        addExpenseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addView();
            }
        });

        done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(validateInput()) {

                    //convert to string using gson
                    Gson gson = new Gson();
                    String hashMapString = gson.toJson(MemberExpense);

                    SharedPreferences.Editor editor = sp.edit();
                    editor.putString("hashMapString", hashMapString).apply();
                    editor.commit();

                    Toast.makeText(getActivity(), "Expenses Added Successfully!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        //get Members names
        MembersNames = sp.getStringSet("MembersSet", null);

        //converting the set to an arraylist
        int n = MembersNames.size();
        MembersNamesList = new ArrayList<String>(n);
        for (String x : MembersNames)
            MembersNamesList.add(x.toString());

        return v;
    }

    private void addView() {
        final View newView = getLayoutInflater().inflate(R.layout.row_add_2, null, false);

        EditText amount = (EditText) newView.findViewById(R.id.amount);
        AppCompatSpinner memberSpinner = newView.findViewById(R.id.spinner_member);
        ImageView removeExpense = newView.findViewById(R.id.remove_expense);

        ArrayAdapter arrayAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, MembersNamesList);
        memberSpinner.setAdapter(arrayAdapter);

        removeExpense.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                removeView(newView);
            }
        });

        layoutList2.addView(newView);
    }

    private void removeView(View view) {
        layoutList2.removeView(view);
    }

    private boolean validateInput() {
        MemberExpense.clear();
        Boolean result = true;

        int count = layoutList2.getChildCount();
        for (int i = 0; i < count; i++) {
            View v = layoutList2.getChildAt(i);

            EditText amount = (EditText) v.findViewById(R.id.amount);
            AppCompatSpinner memberSpinner = v.findViewById(R.id.spinner_member);

           MemberExpense memberExpense = new MemberExpense();

            if (!amount.getText().toString().equals("")) {
                if (memberSpinner.getSelectedItemPosition() != 0) {
                    String member = memberSpinner.getSelectedItem().toString();
                    Double expense = Double.parseDouble(amount.getText().toString());
                    MemberExpense.put( member, expense);
                }
            } else {
                result = false;
                break;
            }

            memberExpense.setHmap(MemberExpense);
        }

        if(MemberExpense.size()==0){
            result = false;
            Toast.makeText(getActivity(), "Please Enter Amounts!", Toast.LENGTH_SHORT).show();
        }else if(!result){
            Toast.makeText(getActivity(), "Please Enter All Info Needed!", Toast.LENGTH_SHORT).show();
        }

        return result;
    }
}