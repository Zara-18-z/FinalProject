package com.example.expensetracker;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class AddTripActivity extends AppCompatActivity {

    private Button btn;
    private EditText tripID;
    private EditText tripName;
    private EditText membersCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_trip);

        tripID = findViewById(R.id.TripID);
        tripName = findViewById(R.id.TripName);
        membersCount = findViewById(R.id.MembersCount);

        tripID.addTextChangedListener(tripInfoTextWatcher);
        tripName.addTextChangedListener(tripInfoTextWatcher);
        membersCount.addTextChangedListener(tripInfoTextWatcher);

        SharedPreferences sp = getSharedPreferences("sp", Context.MODE_PRIVATE);

        btn = findViewById(R.id.addTrip);
        btn.setEnabled(false);

            btn.setOnClickListener(view -> {
                Intent intent = new Intent(getApplicationContext(), AddMembers.class);

                //passing data: ID, Name and Count
                int count = Integer.parseInt(membersCount.getText().toString().trim());
                int id = Integer.parseInt(tripID.getText().toString().trim());
                String name = tripName.getText().toString().trim();

                //storing data
                SharedPreferences.Editor editor = sp.edit();
                editor.putInt("count", count);
                editor.putInt("id", id);
                editor.putString("name", name);
                editor.commit();

                startActivity(intent);

            });
    }

    private final TextWatcher tripInfoTextWatcher = new TextWatcher() {
        @Override
        public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            String Id = tripID.getText().toString().trim();
            String Name = tripName.getText().toString().trim();
            String Members = membersCount.getText().toString().trim();

            if (TextUtils.isEmpty(Id) && TextUtils.isEmpty(Name) && TextUtils.isEmpty(Members)) {
                tripID.setError("Please enter an ID");
                tripName.setError("Please enter an trip name");
                membersCount.setError("Please enter members total");
                btn.setEnabled(false);
            }
            else {
                btn.setEnabled(true);
            }

        }

        @Override
        public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {




           // btn.setEnabled(!Id.isEmpty() && !Name.isEmpty() && !Members.isEmpty());
        }

        @Override
        public void afterTextChanged(Editable editable) {

        }
    };
}