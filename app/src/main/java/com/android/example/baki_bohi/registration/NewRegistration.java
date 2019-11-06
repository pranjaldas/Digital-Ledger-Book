package com.android.example.baki_bohi.registration;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.example.baki_bohi.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class NewRegistration extends AppCompatActivity {
    FirebaseAuth mfirebaseAuth;
    private EditText emailId;
    private EditText password;
    private EditText confirmpass;
    private Button regbtn;
    private ProgressBar progress;
    //to send email and uid
    public static final String EMAIL = "com.android.example.baki_bohi.registration.EMAIL";
    public static final String USER_ID = "com.android.example.baki_bohi.registration.USER_ID";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_registration);
        getSupportActionBar().hide();



        //initialization
        mfirebaseAuth = FirebaseAuth.getInstance();
        emailId = findViewById(R.id.emailSmartEditText);
        password = findViewById(R.id.passwordSmartEditText);
        confirmpass = findViewById(R.id.confirmPassword);
        progress = findViewById(R.id.progressBar);
        regbtn = findViewById(R.id.buttonRegister);


        regbtn.setOnClickListener(new View.OnClickListener() {
            //take input from fields and store it in variable

            @Override
            public void onClick(View v) {
                //take input from fields and store it in variable
                final String email = emailId.getText().toString().trim();
                final String password1 = password.getText().toString().trim();
                final String confirmpass1 = confirmpass.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    Toast.makeText(NewRegistration.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password1)) {
                    Toast.makeText(NewRegistration.this, "Enter Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(confirmpass1)) {
                    Toast.makeText(NewRegistration.this, "Enter Confirm Password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (password1.length() < 6) {
                    Toast.makeText(NewRegistration.this, "Password too Short", Toast.LENGTH_SHORT).show();
                    return;
                }
                progress.setVisibility(View.VISIBLE);
                if (password1.equals(confirmpass1)) {
                    mfirebaseAuth.createUserWithEmailAndPassword(email, password1).addOnCompleteListener(NewRegistration.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    progress.setVisibility(View.GONE);
                                    if (!task.isSuccessful()) {
                                        Toast.makeText(NewRegistration.this, "Sign Up Unsuccessful", Toast.LENGTH_SHORT).show();

                                    } else {
                                        Toast.makeText(NewRegistration.this, "You Are registered", Toast.LENGTH_SHORT).show();
                                        String uid = FirebaseAuth.getInstance().getUid();
                                        Intent intn = new Intent(NewRegistration.this, CreateAccount.class);
                                        intn.putExtra(EMAIL, email);
                                        intn.putExtra(USER_ID, uid);
                                        startActivity(intn);
                                    }

                                }
                            }
                    );

                }

            }
        });
    }
}





