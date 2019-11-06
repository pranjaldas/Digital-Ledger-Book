package com.android.example.baki_bohi.transaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.example.baki_bohi.MainHome;
import com.android.example.baki_bohi.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddTransaction extends AppCompatActivity {
    private EditText amount;
    private EditText debit;
    private EditText credit;
    private ProgressBar progress;
    private Button add;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;

    /*Global variables
    private String amt;
    private String dbt;
    private String cdt;

     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        // Initialization
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Transactions");
        progress = findViewById(R.id.progressBar);


        //into database
        this.uiUpdate();
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progress.setVisibility(View.VISIBLE);
                String amt = amount.getText().toString().trim();
                String dbt = debit.getText().toString().trim();
                String cdt = credit.getText().toString().trim();


                String key = mRef.push().getKey();
                mRef.child(key).child("amount").setValue(amt);
                mRef.child(key).child("debit").setValue(dbt);
                mRef.child(key).child("credit").setValue(cdt);
                progress.setVisibility(View.GONE);
                Toast.makeText(AddTransaction.this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();

                /*refreshing the text fields
                FirebaseDatabase.close();
                amount.setText("");
                credit.setText("");
                debit.setText("");
                */
                Intent intn = new Intent(AddTransaction.this, MainHome.class);
                startActivity(intn);


            }
        });

    }

    private void uiUpdate() {
        amount = findViewById(R.id.textView7);
        debit = findViewById(R.id.textView9);
        credit = findViewById(R.id.textView11);
        progress = findViewById(R.id.progressBar);
        add = findViewById(R.id.button1);
        //Validation
        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String A = s.toString();
                //int A1=Integer.parseInt(A);
                //String C=Integer.toString(A1);
                credit.setText(A);
            }

            @Override
            public void afterTextChanged(Editable s) {
                credit.setKeyListener(null);

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
                    Toast.makeText(AddTransaction.this, "Debit Text Empty", Toast.LENGTH_SHORT).show();
                } else {
                    int A1 = Integer.parseInt(amount.getText().toString().trim());
                    int D1 = Integer.parseInt(debit.getText().toString().trim());
                    if (D1 > A1) {
                        debit.setError("D could not greater then A");
                    } else {
                        int C1 = A1 - D1;
                        String C = Integer.toString(C1);
                        credit.setText(C);

                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {
                int A1 = Integer.parseInt(amount.getText().toString().trim());
                int D1 = Integer.parseInt(debit.getText().toString().trim());
                add.setEnabled(!(D1 > A1));


            }
        });


    }
}
