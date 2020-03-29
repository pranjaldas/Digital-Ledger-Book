package com.android.example.baki_bohi.customer;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.models.Customer;
import com.android.example.baki_bohi.tabs.HomeScreen;
import com.android.example.baki_bohi.util.Persistance;
import com.android.example.baki_bohi.util.UiUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddCustomer extends AppCompatActivity {
    // Declare
    private static final int RESULT_PICK_CONTACT = 1;
    EditText cname;
    EditText caddress;
    EditText cphone;
    Button select;
    Button addcustomer;
    //Firebase
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Customers");

        setContentView(R.layout.activity_add_customer);
        cname = findViewById(R.id.customer_name);
        caddress = findViewById(R.id.customer_address);
        cphone = findViewById(R.id.customer_phone);
        select = findViewById(R.id.select_from_contact);
        addcustomer = findViewById(R.id.customer_add);


        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent in = new Intent(Intent.ACTION_PICK, ContactsContract.CommonDataKinds.Phone.CONTENT_URI);
                startActivityForResult(in, RESULT_PICK_CONTACT);
            }
        });
        addcustomer.setOnClickListener(new View.OnClickListener() {
            //            UiUtil.showSimpleProgressDialog(AddCustomer.this, "Please wait...", "Getting your data from server", false);
            @Override
            public void onClick(View v) {
                UiUtil.showSimpleProgressDialog(AddCustomer.this, "Please wait...", "Saving your data", false);

                String c_name = cname.getText().toString().trim();
                String c_addrs = caddress.getText().toString().trim();
                String c_phn = cphone.getText().toString().trim();
                String key = mRef.push().getKey();
                String s_id = Persistance.uId;
                Customer customer = new Customer(c_name, c_addrs, c_phn, key, s_id);
                mRef.child(key).setValue(customer).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            UiUtil.removeSimpleProgressDialog();
                            Toast.makeText(AddCustomer.this, "Customer Addes Succesfully", Toast.LENGTH_SHORT).show();
                            Intent intn = new Intent(getApplicationContext(), HomeScreen.class);
                            startActivity(intn);
                            finish();
                        } else {
                            UiUtil.removeSimpleProgressDialog();
                            Toast.makeText(AddCustomer.this, "Opps..something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case RESULT_PICK_CONTACT:
                    contactPicked(data);
                    break;
            }
        } else {
            Toast.makeText(this, "Failed To pick contact", Toast.LENGTH_SHORT).show();
        }
    }

    private void contactPicked(Intent data) {
        Cursor cursor = null;

        try {
            String phoneNo = null;
            Uri uri = data.getData();
            cursor = getContentResolver().query(uri, null, null, null, null);
            cursor.moveToFirst();
            int phoneIndex = cursor.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER);

            phoneNo = cursor.getString(phoneIndex);

            cphone.setText(phoneNo);


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




