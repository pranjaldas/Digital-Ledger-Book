package com.android.example.baki_bohi.transaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.example.baki_bohi.MainHome;
import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.util.UiUtil;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTransaction extends AppCompatActivity {
    private EditText amount;
    private EditText debit;
    private EditText credit;
    private Button add;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    private float debitAmount, purchaseAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        initViews();
        debitAmount = 0;
        purchaseAmount = 0;
        updateUI();

        // Initialization
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Transactions");

        addTextListeners();


        //into database
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtil.showSimpleProgressDialog(getApplicationContext(), "Please wait", "saving your data", false);
                String amt = amount.getText().toString().trim();
                String dbt = debit.getText().toString().trim();
                String cdt = credit.getText().toString().trim();
                if (amt.equals("") && cdt.equals("") && dbt.equals("")) {
                    Toast.makeText(getApplicationContext(), "Please fill the values", Toast.LENGTH_SHORT).show();
                    return;
                } else if (Integer.parseInt(amount.getText().toString().trim()) < Integer.parseInt(debit.getText().toString().trim())) {
                    debit.setError("Total Purchase could not be Less than paid amount");
                    debit.setFocusable(true);
                    return;
                } else {
                    String key = mRef.push().getKey();
                    mRef.child(key).child("amount").setValue(amt);
                    mRef.child(key).child("debit").setValue(dbt);
                    mRef.child(key).child("credit").setValue(cdt);

                    Toast.makeText(AddTransaction.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                    Intent intn = new Intent(AddTransaction.this, MainHome.class);
                    startActivity(intn);
                    finish();
                    UiUtil.removeSimpleProgressDialog();
                }
            }
        });
    }

    private void addTextListeners() {
        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = amount.getText().toString();
                if (str.equals("")) {
                    purchaseAmount = 0;
                    updateUI();
                } else {
                    purchaseAmount = Float.parseFloat(str);
                    updateUI();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });

        debit.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = debit.getText().toString();
                if (str.equals("")) {
                    debitAmount = 0;
                    updateUI();
                } else {
                    debitAmount = Float.parseFloat(str);
                    updateUI();
                }
            }
            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void updateUI() {
        credit.setText(String.valueOf(purchaseAmount - debitAmount));
    }

    private void initViews() {
        amount = findViewById(R.id.transaction_total);
        debit = findViewById(R.id.transaction_debit);
        credit = findViewById(R.id.transaction_credit);
        add = findViewById(R.id.transaction_add);
    }
}
