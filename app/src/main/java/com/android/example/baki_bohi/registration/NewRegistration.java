package com.android.example.baki_bohi.registration;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.android.example.baki_bohi.R;
import com.teleclinic.kabdo.smartform.SmartEditText;

public class NewRegistration extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_registration);

    }

    public void check(View view) {
        SmartEditText phone= findViewById(R.id.phoneSmartEditText);
        final SmartEditText password= findViewById(R.id.passwordSmartEditText);
        final SmartEditText confirmpass= findViewById(R.id.confirmPasswordSmartEditText);

        if(phone.check() && password.check() && confirmpass.check()){
            Toast.makeText(this, "Validation Successful", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Validation not done", Toast.LENGTH_SHORT).show();
        }

    }
}
