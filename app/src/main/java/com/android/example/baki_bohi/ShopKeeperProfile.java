package com.android.example.baki_bohi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.example.baki_bohi.models.Shopkeeper;
import com.android.example.baki_bohi.registration.LogIn;
import com.android.example.baki_bohi.util.Persistance;
import com.android.example.baki_bohi.util.UiUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class ShopKeeperProfile extends AppCompatActivity {
    TextView name;
    TextView email;
    TextView phn;
    TextView address;
    TextView shopname;
    TextView Uid;
    Button logout;
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop_keeper_profile);


        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Shopkeeper");

        name = findViewById(R.id.profile_name);
        email = findViewById(R.id.semail);
        phn = findViewById(R.id.sphone);
        address = findViewById(R.id.profile_shop_address);
        shopname = findViewById(R.id.profile_shop_name);
        Uid = findViewById(R.id.userid);
        logout = findViewById(R.id.logout);

        Query query = mRef.orderByChild("uid").equalTo(Persistance.uId);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                Shopkeeper sk = dataSnapshot.getValue(Shopkeeper.class);

                name.setText(sk.getName());
                email.setText(sk.getEmail());
                phn.setText(sk.getPhone());
                address.setText(sk.getAddress());
                shopname.setText(sk.getShopname());
                Uid.setText(sk.getUid());
                UiUtil.removeSimpleProgressDialog();


            }


            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {


            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences myPrefs = getSharedPreferences("bakiBohiPrefs", MODE_PRIVATE);
                SharedPreferences.Editor edit = myPrefs.edit();
                edit.clear();
                edit.commit();
                Intent intn = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intn);
                finish();

            }
        });
    }
}
