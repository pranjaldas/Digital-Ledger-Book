package com.android.example.baki_bohi;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.android.example.baki_bohi.transaction.AddTransaction;
import com.android.example.baki_bohi.transaction.ViewTransaction;

public class MainHome extends AppCompatActivity {
    Button profile;
    Button addtrans;
    Button viewtrans;
    Button addcustomer;
    Button viewcustomer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_home);
        getSupportActionBar().hide();

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

    }
}
