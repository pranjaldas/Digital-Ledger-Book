package com.android.example.baki_bohi.registration;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.tabs.HomeScreen;
import com.android.example.baki_bohi.util.Persistance;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogIn extends AppCompatActivity {
    private EditText emailLogin, passwordLogin;
    private Button login;
    private TextView forgetpassword;
    private TextView newregister;
    private ProgressBar progressBar;
    private FirebaseAuth mfirebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        getSupportActionBar().hide();

        //initialization
        mfirebaseAuth = FirebaseAuth.getInstance();
        emailLogin = findViewById(R.id.emailLogin);
        passwordLogin = findViewById(R.id.passwordLogin);
        progressBar = findViewById(R.id.progressBar);
        login = findViewById(R.id.login);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String emlgn = emailLogin.getText().toString().trim();
                final String pw = passwordLogin.getText().toString().trim();
                if (TextUtils.isEmpty(emlgn)) {
                    Toast.makeText(LogIn.this, "Enter email", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(pw)) {
                    Toast.makeText(LogIn.this, "Enter password", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (pw.length() < 6) {
                    Toast.makeText(LogIn.this, "Password is too Short", Toast.LENGTH_SHORT).show();
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);
                mfirebaseAuth.signInWithEmailAndPassword(emlgn, pw).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override

                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressBar.setVisibility(View.GONE);
                        if (task.isSuccessful()) {
                            Persistance.uId = mfirebaseAuth.getUid();
                            saveToSharedPreference(emlgn, pw);
                            Intent intn = new Intent(LogIn.this, HomeScreen.class);
                            startActivity(intn);
                            finish();

                        } else {
                            Toast.makeText(LogIn.this, "Log in failed password and email mismatch", Toast.LENGTH_SHORT).show();

                        }
                    }
                });
            }
        });


        newregister = findViewById(R.id.newRegister);
        newregister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intn = new Intent(LogIn.this, NewRegistration.class);
                startActivity(intn);

            }
        });

    }

    private void saveToSharedPreference(String emlgn, String pw) {
        SharedPreferences myPrefs = getSharedPreferences("bakiBohiPrefs", MODE_PRIVATE);
        SharedPreferences.Editor edit = myPrefs.edit();
        edit.putString("email", emlgn);
        edit.putString("pw", pw);
        edit.apply();

    }

}
