package com.example.expensetracker;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class AddMembers extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_members);

        Button btn = findViewById(R.id.addMember);


        btn.setOnClickListener(view -> {
            int i = 0;
            Intent intent = new Intent(this, main2.class);

            //sending data: leaderName
            EditText leaderName = findViewById(R.id.LeaderName);
            String LeaderName = leaderName.getText().toString().trim();

            SharedPreferences sp = getSharedPreferences("sp", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("LeaderName", LeaderName);
            editor.commit();

            startActivity(intent);

       });
    }
}