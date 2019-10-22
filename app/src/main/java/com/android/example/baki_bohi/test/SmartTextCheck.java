package com.android.example.baki_bohi.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.android.example.baki_bohi.R;
import com.teleclinic.kabdo.smartform.SmartEditText;

public class SmartTextCheck extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_smart_text_check);
    }

    public void check(View view) {
        SmartEditText email = findViewById(R.id.emailSmartEditText);
        SmartEditText password = findViewById(R.id.passwordSmartEditText);
        
        if(email.check() && password.check()){
            //go to next code
            Toast.makeText(this, "True", Toast.LENGTH_SHORT).show();
        }else
            Toast.makeText(this, "False", Toast.LENGTH_SHORT).show();
        
    }
}
