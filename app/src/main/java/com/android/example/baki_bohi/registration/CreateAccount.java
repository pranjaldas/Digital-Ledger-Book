package com.android.example.baki_bohi.registration;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.models.Shopkeeper;
import com.android.example.baki_bohi.tabs.HomeScreen;
import com.android.example.baki_bohi.util.Persistance;
import com.android.example.baki_bohi.util.UiUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount extends AppCompatActivity {

    Spinner spinner;
    EditText name;
    EditText phnno;
    EditText address;
    EditText shopname;
    Button createac;
    FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);
        getSupportActionBar().hide();
        /*to catch the variables email and uid;
        Intent intent = getIntent();
        final String email = intent.getStringExtra(NewRegistration.EMAIL);
        final String uid = intent.getStringExtra(NewRegistration.USER_ID);
        */

        //Initialization
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Shopkeeper");

        spinner = findViewById(R.id.spinnerCountries);
        spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));
        name = findViewById(R.id.profile_name);
        phnno = findViewById(R.id.sphn);
        address = findViewById(R.id.sadress);
        shopname = findViewById(R.id.sShopname);
        createac = findViewById(R.id.createac);

        createac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtil.showSimpleProgressDialog(CreateAccount.this, "Please wait...", "Crating your Account", false);
                String sname = name.getText().toString().trim();
                String sphn = phnno.getText().toString().trim();
                String saddress = address.getText().toString().trim();
                String shpname = shopname.getText().toString().trim();
                String email = Persistance.email;
                String uid = Persistance.uId;
                Shopkeeper shpkpr = new Shopkeeper(sname, sphn, email, saddress, shpname, uid);

                mRef.child(uid).setValue(shpkpr).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        UiUtil.removeSimpleProgressDialog();
                        if (task.isSuccessful()) {
                            Toast.makeText(CreateAccount.this, "ShopKeeper Account Created", Toast.LENGTH_SHORT).show();
                            Intent intn = new Intent(CreateAccount.this, HomeScreen.class);
                            startActivity(intn);
                            finish();
                        }

                    }
                });
                /*
                String key= mRef.push().getKey();
                mRef.child(key).child("amount").setValue(amt);
                mRef.child(key).child("debit").setValue(dbt);
                mRef.child(key).child("credit").setValue(cdt);
                progress.setVisibility(View.GONE);
                Toast.makeText(AddTransaction.this,"Data Inserted Successfully",Toast.LENGTH_SHORT).show();
                */
            }
        });
    }
}
