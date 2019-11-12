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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.example.baki_bohi.MainHome;
import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.models.TranTest;
import com.android.example.baki_bohi.util.UiUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

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
        credit.setKeyListener(null);

        //date and Time
        Calendar calander = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm a");
        final String date = simpleDateFormat.format(calander.getTime());
        final String time = simpleTimeFormat.format(calander.getTime());

        //into database
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtil.showSimpleProgressDialog(getApplicationContext(), "Please wait", "saving your data", false);
                String amt = amount.getText().toString().trim();
                String dbt = debit.getText().toString().trim();
                String cdt = credit.getText().toString().trim();
                if (debitAmount == 0 && purchaseAmount == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill the values", Toast.LENGTH_SHORT).show();
                    return;

                } else {
                    String key = mRef.push().getKey();

                    TranTest transaction = new TranTest(amt, dbt, cdt, date, time);
                    mRef.child(key).setValue(transaction).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AddTransaction.this, "Transaction added Successfully", Toast.LENGTH_SHORT).show();
                                Intent intn = new Intent(AddTransaction.this, MainHome.class);
                                startActivity(intn);
                                finish();
                                UiUtil.removeSimpleProgressDialog();
                            } else {
                                Toast.makeText(AddTransaction.this, "Not inserted Transaction", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });


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
