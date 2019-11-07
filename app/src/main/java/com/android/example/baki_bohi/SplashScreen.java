package com.android.example.baki_bohi;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.example.baki_bohi.registration.LogIn;
import com.android.example.baki_bohi.util.Persistance;
import com.android.example.baki_bohi.util.UiUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SplashScreen extends AppCompatActivity {
    private static int SPLASH_SCREEN_TIMEOUT = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);
        getSupportActionBar().hide();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                login();
            }
        }, SPLASH_SCREEN_TIMEOUT);
    }

    private void login() {
        SharedPreferences preferences = getSharedPreferences("bakiBohiPrefs", MODE_PRIVATE);
        final String email = preferences.getString("email", null);
        final String password = preferences.getString("pw", null);

        if (email == null && password == null) {
            Intent intn = new Intent(SplashScreen.this, LogIn.class);
            startActivity(intn);
            finish();
        } else {
            final FirebaseAuth mfirebaseAuth = FirebaseAuth.getInstance();
            UiUtil.showSimpleProgressDialog(this, "", "Logging you in...", false);
            mfirebaseAuth.signInWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override

                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Persistance.uId = mfirebaseAuth.getUid();
                                saveToSharedPreference(email, password);
                                Intent intn = new Intent(getApplicationContext(), MainHome.class);
                                intn.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(intn);
                                UiUtil.removeSimpleProgressDialog();

                            } else {
                                UiUtil.removeSimpleProgressDialog();
                                Intent intn = new Intent(SplashScreen.this, LogIn.class);
                                startActivity(intn);
                                finish();
                                Toast.makeText(getApplicationContext(), "Log in failed password and email mismatch", Toast.LENGTH_SHORT).show();

                            }
                        }
                    });
        }
    }

    private void saveToSharedPreference(String emlgn, String pw) {
        SharedPreferences myPrefs = getSharedPreferences("bakiBohiPrefs", MODE_PRIVATE);
        SharedPreferences.Editor edit = myPrefs.edit();
        edit.putString("email", emlgn);
        edit.putString("pw", pw);
        edit.apply();

    }
}
