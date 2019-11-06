package com.android.example.baki_bohi;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateAccount extends AppCompatActivity {

    EditText firstName, secondName, phoneNo, eMail, address, shopName, shopType;
    Button createAccount;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        initViews();

        //databaseReference = firebaseDatabase.getInstance().getReference("Customer");
        //firebaseAuth = FirebaseAuth.getInstance();


        createAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(validate()){
                    //True
                    saveToFireBase();
                }else{
                    Toast.makeText(CreateAccount.this, "Please Enter all the fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }

    private boolean validate(){
        boolean flag = true;
        if (TextUtils.isEmpty(firstName.getText())){
            flag = false;
        }
        if (TextUtils.isEmpty(secondName.getText())){
            flag= false;
        }
        if (TextUtils.isEmpty(phoneNo.getText())){
            flag=false;
        }
        if (TextUtils.isEmpty(eMail.getText())){
            flag=false;
        }
        if (TextUtils.isEmpty(address.getText())){
            flag=false;
        }
        if (TextUtils.isEmpty(shopName.getText())){
            flag=false;
        }
        if (TextUtils.isEmpty(shopType.getText())){
            flag=false;
        }
        return flag;
    }

    private void saveToFireBase(){
        Customer data = new Customer(firstName.getText().toString(),secondName.getText().toString(),phoneNo.getText().toString(),eMail.getText().toString(),address.getText().toString(),shopName.getText().toString(),shopType.getText().toString());

        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("ShopKeepr");
/*        String pushID = databaseReference.push().getKey();
        Log.d("TAG", pushID);*/
        databaseReference.child("Sk65").setValue(data)
            .addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    Toast.makeText(CreateAccount.this, "Database Updated", Toast.LENGTH_SHORT).show();
                }
            })
            .addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(CreateAccount.this, "Database Update Failed", Toast.LENGTH_SHORT).show();
                }
            });

    }

    private void initViews() {
        //casting views
        firstName = findViewById(R.id.firstName);
        secondName = findViewById(R.id.secondName);
        phoneNo = findViewById(R.id.phoneNo);
        eMail = findViewById(R.id.eMail);
        address = findViewById(R.id.address);
        shopName =findViewById(R.id.shopName);
        shopType = findViewById(R.id.shopType);
        createAccount =findViewById(R.id.createAccount);
    }
}
