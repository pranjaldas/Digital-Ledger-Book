package com.android.example.baki_bohi.transaction;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatAutoCompleteTextView;

import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.customer.SearchAdapter;
import com.android.example.baki_bohi.models.Customer;
import com.android.example.baki_bohi.models.TranTest;
import com.android.example.baki_bohi.tabs.HomeScreen;
import com.android.example.baki_bohi.util.Persistance;
import com.android.example.baki_bohi.util.UiUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AddTransaction extends AppCompatActivity {
    //Declaring variables not war
    private EditText amountEditText, debitEditText, creditEditText;
    private Button addButton;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef, customerDBRef;
    private AppCompatAutoCompleteTextView searchCustomerTextbox;
    private Customer selectedCustomer;
    private List<Customer> customerList;
    private SearchAdapter adapter;

    private float debitAmount, purchaseAmount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_transaction);

        initViews();
        debitAmount = 0;
        purchaseAmount = 0;
        updateUI();

        fetchCustomerList();

        // Initialization of Database path variables.
        mRef = mDatabase.getReference("Transactions");
        addTextListeners();
        creditEditText.setKeyListener(null);

        //date and Time initialization
        Calendar calander = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm a");
        final String date = simpleDateFormat.format(calander.getTime());
        final String time = simpleTimeFormat.format(calander.getTime());

        //into database
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtil.showSimpleProgressDialog(getApplicationContext(), "Please wait", "saving your data", false);
                String amt = amountEditText.getText().toString().trim();
                String dbt = debitEditText.getText().toString().trim();
                String cdt = creditEditText.getText().toString().trim();
                if (debitAmount == 0 && purchaseAmount == 0) {
                    Toast.makeText(getApplicationContext(), "Please fill the values", Toast.LENGTH_SHORT).show();
                    return;

                } else {
                    String key = mRef.push().getKey();

                    TranTest transaction = new TranTest(amt, dbt, cdt, date, time, Persistance.uId);
                    mRef.child(key).setValue(transaction).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(AddTransaction.this, "Transaction added Successfully", Toast.LENGTH_SHORT).show();
                                Intent intn = new Intent(AddTransaction.this, HomeScreen.class);
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

    private void fetchCustomerList(){
        customerDBRef = mDatabase.getReference("Customers");
        customerDBRef.addListenerForSingleValueEvent(customerDBFetchListener);
        adapter = new SearchAdapter(this, R.layout.search_customer_item, customerList);
        searchCustomerTextbox.setAdapter(adapter);
        searchCustomerTextbox.setOnItemClickListener(searchCustomerItemClickListener);
    }

    AdapterView.OnItemClickListener searchCustomerItemClickListener = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
            selectedCustomer = (Customer) adapterView.getItemAtPosition(i);
            Log.d("TAG", "onItemClick: "+ selectedCustomer);
            searchCustomerTextbox.setText(selectedCustomer.getName());
        }
    };

    ValueEventListener customerDBFetchListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            for(DataSnapshot data : dataSnapshot.getChildren()) {
                Customer item = data.getValue(Customer.class);
                customerList.add(item);
            }
            adapter.notifyDataSetChanged();
            Log.d("TAG_AddTransaction", "onDataChange: " + customerList.toString());
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            databaseError.toException().printStackTrace();
            Toast.makeText(AddTransaction.this, databaseError.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };

    private void addTextListeners() {
        amountEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = amountEditText.getText().toString();
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

        debitEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String str = debitEditText.getText().toString();
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

    @Override
    public void onBackPressed() {
        Intent intn = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(intn);
        finish();
        super.onBackPressed();
    }

    private void updateUI() {
        creditEditText.setText(String.valueOf(purchaseAmount - debitAmount));
    }

    private void initViews() {
        amountEditText = findViewById(R.id.transaction_total);
        debitEditText = findViewById(R.id.transaction_debit);
        creditEditText = findViewById(R.id.transaction_credit);
        addButton = findViewById(R.id.transaction_add);
        searchCustomerTextbox = findViewById(R.id.add_transaction_search_customer);

        mDatabase = FirebaseDatabase.getInstance();
        customerList = new ArrayList<>();
    }
}
