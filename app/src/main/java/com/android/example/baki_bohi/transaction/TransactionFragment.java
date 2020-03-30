package com.android.example.baki_bohi.transaction;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.models.TranTest;
import com.android.example.baki_bohi.util.UiUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class TransactionFragment extends Fragment {

    private static TransactionFragment transactionFragment;
    FirebaseDatabase mDatabase;
    ArrayList<TranTest> tranList;
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    private DatabaseReference mRef;

    public static TransactionFragment getInstance() {
        if (transactionFragment == null) {
            transactionFragment = new TransactionFragment();
        }
        return transactionFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_transactions, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        UiUtil.showSimpleProgressDialog(getActivity(), "Please wait...", "Getting your data from server", false);
        tranList = new ArrayList<>();
        recyclerView = getActivity().findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MyRecyclerViewAdapter(tranList, getActivity());
        recyclerView.setAdapter(adapter);

        //Init Firebase
        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Transactions");
        mRef.addChildEventListener(childEventListener);

    }

    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            TranTest item = dataSnapshot.getValue(TranTest.class);
            tranList.add(item);
            adapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(tranList.size() - 1);
            UiUtil.removeSimpleProgressDialog();
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {
            TranTest item = dataSnapshot.getValue(TranTest.class);
            tranList.remove(item);
            adapter.notifyDataSetChanged();
            recyclerView.scrollToPosition(tranList.size() - 1);
            UiUtil.removeSimpleProgressDialog();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
        }
    };

    @Override
    public void onDestroy() {
        mRef.removeEventListener(childEventListener);
        super.onDestroy();
    }
}
