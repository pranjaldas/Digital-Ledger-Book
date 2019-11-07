package com.android.example.baki_bohi.transaction;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.models.TranTest;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ViewTransaction extends AppCompatActivity implements ChildEventListener {
    FirebaseDatabase mDatabase;
    ArrayList<TranTest> tranList;
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    private DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_transaction);

        //Init RecyclerView
        tranList = new ArrayList<>();
        recyclerView = findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyRecyclerViewAdapter(tranList, this);
        recyclerView.setAdapter(adapter);

        //Init Firebase
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Transactions");
        mRef.addChildEventListener(this);
    }

    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        TranTest item = dataSnapshot.getValue(TranTest.class);
        tranList.add(item);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
        TranTest item = dataSnapshot.getValue(TranTest.class);
        tranList.remove(item);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
}
