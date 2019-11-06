package com.android.example.baki_bohi;

import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.android.example.baki_bohi.registration.NewRegistration;

public class ShopKeeperProfile extends AppCompatActivity {
    TextView name;
    TextView email;
    TextView phn;
    TextView address;
    TextView shopname;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_keeper_profile);
        getSupportActionBar().hide();

        Intent intent = getIntent();
        final String uid = intent.getStringExtra(NewRegistration.USER_ID);

        name = findViewById(R.id.sname);
        email = findViewById(R.id.semail);
        phn = findViewById(R.id.sphn);
        address = findViewById(R.id.sadress);
        shopname = findViewById(R.id.shpname);
    }
}
