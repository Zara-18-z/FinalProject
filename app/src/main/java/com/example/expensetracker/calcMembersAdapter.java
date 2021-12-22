package com.example.expensetracker;

import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class calcMembersAdapter extends RecyclerView.Adapter<calcMembersAdapter.calcView> {


    ArrayList<Member> membersList = new ArrayList<>();

    public calcMembersAdapter(ArrayList<Member> membersList) {
        this.membersList = membersList;
    }

    @NonNull
    @Override
    public calcView onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_add_3, parent, false);
        return new calcView(view);
    }

    @Override
    public void onBindViewHolder(@NonNull calcView holder, int position) {

        Member member = membersList.get(position);
        holder.textMemberName.setText(member.getMembername());
        holder.textExpense.setText(("Expenses paid: $" + member.getExpense()).toString());
    }

    @Override
    public int getItemCount() {
        return membersList.size();
    }

    public class calcView extends RecyclerView.ViewHolder{


        TextView textMemberName, textExpense;
        public calcView(@NonNull View itemView) {
            super(itemView);

            textMemberName = itemView.findViewById(R.id.text_name);
            textExpense = itemView.findViewById(R.id.text_expenses);
        }
    }
}
