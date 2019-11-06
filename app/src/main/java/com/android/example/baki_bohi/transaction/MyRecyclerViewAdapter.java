package com.android.example.baki_bohi.transaction;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.models.TranTest;

import java.util.ArrayList;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyViewHolder> {


    ArrayList<TranTest> transactionList;
    Context mContext;

    public MyRecyclerViewAdapter(ArrayList<TranTest> transactionList, Context context) {
        this.transactionList = transactionList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.transactionlist, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        TranTest item = transactionList.get(position);
        holder.totalAmt.setText(item.getAmount());
        holder.debitAmt.setText(item.getDebit());
        holder.creditAmt.setText(item.getCredit());
        holder.transactionItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Not yet code", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return transactionList.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView creditAmt, debitAmt, totalAmt;
        LinearLayout transactionItem;

        public MyViewHolder(View itemView) {
            super(itemView);
            creditAmt = itemView.findViewById(R.id.creditEdit);
            debitAmt = itemView.findViewById(R.id.debitEdit);
            totalAmt = itemView.findViewById(R.id.amountEdit);
            transactionItem = itemView.findViewById(R.id.transactionList);
        }
    }
}
