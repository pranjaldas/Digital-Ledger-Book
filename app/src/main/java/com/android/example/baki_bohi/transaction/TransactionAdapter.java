package com.android.example.baki_bohi.transaction;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.models.TranTest;

import java.util.ArrayList;

public class TransactionAdapter extends ArrayAdapter<TranTest> {
    public TransactionAdapter(Activity context, ArrayList<TranTest> tranList) {
        super(context, 0, tranList);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.transactionlist, parent, false);
        }

        TranTest currentTran = getItem(position);

        // Find the TextView in the list_item.xml layout with the ID version_name
        TextView dateTextView = listItemView.findViewById(R.id.dateEdit);
        dateTextView.setText(currentTran.getAmount());

// Find the TextView in the list_item.xml layout with the ID version_name
        TextView timeTextView = listItemView.findViewById(R.id.timeEdit);
        timeTextView.setText(currentTran.getAmount());

// Find the TextView in the list_item.xml layout with the ID version_name
        TextView amountTextView = listItemView.findViewById(R.id.amountEdit);
        amountTextView.setText(currentTran.getAmount());

        // Find the TextView in the list_item.xml layout with the ID version_number
        TextView debitTextView = listItemView.findViewById(R.id.debitEdit);
        debitTextView.setText(currentTran.getDebit());


        TextView creditTextView = listItemView.findViewById(R.id.creditEdit);
        creditTextView.setText(currentTran.getCredit());

        // Return the whole list item layout (containing 2 TextViews and an ImageView)
        // so that it can be shown in the ListView
        return listItemView;
    }
}
