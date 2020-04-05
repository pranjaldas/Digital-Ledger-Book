package com.android.example.baki_bohi.notes;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.models.Note;
import com.android.example.baki_bohi.tabs.HomeScreen;
import com.android.example.baki_bohi.util.Persistance;
import com.android.example.baki_bohi.util.UiUtil;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AddNote extends AppCompatActivity {
    private EditText subject;
    private EditText description;
    private Button saveNote;
    private DatabaseReference mRef;
    private FirebaseDatabase mDatabase;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();
        //date and Time initialization
        Calendar calander = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy");
        SimpleDateFormat simpleTimeFormat = new SimpleDateFormat("hh:mm a");
        final String date = simpleDateFormat.format(calander.getTime());
        final String time = simpleTimeFormat.format(calander.getTime());

        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UiUtil.showSimpleProgressDialog(AddNote.this, "Please wait...", "Saving your Note", false);
                String sub = subject.getText().toString();
                String des = description.getText().toString();
                String key = mRef.push().getKey();
                //Notes object initialization
                Note note = new Note();
                note.setNote_id(key);
                note.setDate(date);
                note.setTime(time);
                note.setDescription(des);
                note.setSubject(sub);
                note.setSid(Persistance.uId);
                mRef.child(key).setValue(note).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            UiUtil.removeSimpleProgressDialog();
                            Toast.makeText(AddNote.this, "Note Added Successfully", Toast.LENGTH_SHORT).show();
                            Intent intn = new Intent(getApplicationContext(), HomeScreen.class);
                            startActivity(intn);
                            finish();
                        } else {
                            UiUtil.removeSimpleProgressDialog();
                            Toast.makeText(AddNote.this, "Opps..something went wrong!", Toast.LENGTH_SHORT).show();
                        }
                    }

                });


            }
        });


    }

    private void initViews() {
        subject = findViewById(R.id.note_subject);
        description = findViewById(R.id.note_description);
        saveNote = findViewById(R.id.note_add);
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Notes");
    }

    @Override
    public void onBackPressed() {
        Intent intn = new Intent(getApplicationContext(), HomeScreen.class);
        startActivity(intn);
        finish();
        super.onBackPressed();
    }
}
