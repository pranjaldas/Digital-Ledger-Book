package com.android.example.baki_bohi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.example.baki_bohi.customer.AddCustomer;
import com.android.example.baki_bohi.customer.ViewCustomer;
import com.android.example.baki_bohi.transaction.AddTransaction;
import com.android.example.baki_bohi.transaction.ViewTransaction;

public class MainHome extends AppCompatActivity {
    ImageView profile;
    ImageView addtrans;
    ImageView viewtrans;
    ImageView addcustomer;
    ImageView viewcustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);


        profile = findViewById(R.id.profileview);
        addtrans = findViewById(R.id.transactionadd);
        viewtrans = findViewById(R.id.transactionview);
        addtrans = findViewById(R.id.transactionadd);
        addcustomer = findViewById(R.id.customeradd);
        viewcustomer = findViewById(R.id.customersview);


        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intn = new Intent(MainHome.this, ShopKeeperProfile.class);
                startActivity(intn);

            }
        });
        addtrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intn = new Intent(MainHome.this, AddTransaction.class);
                startActivity(intn);
            }
        });
        viewtrans.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intn = new Intent(MainHome.this, ViewTransaction.class);
                startActivity(intn);
            }
        });
        addcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intn = new Intent(MainHome.this, AddCustomer.class);
                startActivity(intn);
            }
        });
        viewcustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intn = new Intent(MainHome.this, ViewCustomer.class);
                startActivity(intn);
            }
        });

    }
}
