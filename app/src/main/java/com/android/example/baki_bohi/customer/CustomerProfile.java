package com.android.example.baki_bohi.customer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.models.Customer;
import com.android.example.baki_bohi.tabs.HomeScreen;

public class CustomerProfile extends AppCompatActivity {
    private Customer customer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_profile);
        Intent intn=this.getIntent();
        Bundle bundle=intn.getExtras();
        customer=(Customer) bundle.getSerializable("Customer_object");
        System.out.println(customer.toString());
        Log.i("LOG",customer.toString());

    }
    @Override
    public void onBackPressed() {
        Intent intn = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(intn);
        finish();
        super.onBackPressed();
    }
}
