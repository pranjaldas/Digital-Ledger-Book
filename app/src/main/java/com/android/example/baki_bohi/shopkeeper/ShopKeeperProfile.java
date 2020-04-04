package com.android.example.baki_bohi.shopkeeper;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.models.Shopkeeper;
import com.android.example.baki_bohi.tabs.HomeScreen;
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

    FirebaseDatabase mDatabase;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        UiUtil.showSimpleProgressDialog(ShopKeeperProfile.this, "Please wait...", "Getting your data from server", false);
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


        Query query = mRef.orderByChild("uid").equalTo(Persistance.uId);
        try {
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
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onBackPressed() {
        Intent intn = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(intn);
        finish();
        super.onBackPressed();
    }
}
