package com.android.example.baki_bohi.transaction;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.models.TranTest;
import com.android.example.baki_bohi.util.Persistance;
import com.android.example.baki_bohi.util.UiUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class TransactionFragment extends Fragment {

    private static TransactionFragment transactionFragment;
    FirebaseDatabase mDatabase;
    ArrayList<TranTest> tranList;
    RecyclerView recyclerView;
    MyRecyclerViewAdapter adapter;
    private DatabaseReference mRef;
    Query query;

    public static TransactionFragment getInstance() {
        if (transactionFragment == null) {
            transactionFragment = new TransactionFragment();
        }
        return transactionFragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_transactions, container, false);
    }
    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            TranTest item = dataSnapshot.getValue(TranTest.class);
            tranList.add(item);
            Log.i("onChildAdded: ", tranList.toString());
            adapter.notifyDataSetChanged();
            UiUtil.removeSimpleProgressDialog();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            TranTest item = dataSnapshot.getValue(TranTest.class);
            tranList.add(item);
            adapter.notifyDataSetChanged();
            Log.i("onChildAdded: ", item.toString());
            UiUtil.removeSimpleProgressDialog();
        }

        @Override
        public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

        }

        @Override
        public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(getActivity(), "No data", Toast.LENGTH_SHORT).show();
        }
    };

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        UiUtil.showSimpleProgressDialog(getActivity(), "Please wait...", "Getting your data from server", false);

        //Init RecyclerView
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
        query = mRef.orderByChild("sid").equalTo(Persistance.uId);
        query.addChildEventListener(childEventListener);
    }

    @Override
    public void onDestroy() {
        query.removeEventListener(childEventListener);
        super.onDestroy();
    }
}
