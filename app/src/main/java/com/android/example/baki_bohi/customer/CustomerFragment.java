package com.android.example.baki_bohi.customer;

import android.os.Bundle;
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
import com.android.example.baki_bohi.models.Customer;
import com.android.example.baki_bohi.util.Persistance;
import com.android.example.baki_bohi.util.UiUtil;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

public class CustomerFragment extends Fragment {

    private static CustomerFragment customerFragment;
    private FirebaseDatabase mDatabase;
    private DatabaseReference mRef;
    private Query query;

    private List<Customer> customerList;
    private RecyclerView recyclerView;
    private CustomerViewAdapter customerViewAdapter;

    public static CustomerFragment getInstance() {
        if (customerFragment == null) {
            customerFragment = new CustomerFragment();
        }
        return customerFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_view_customer, container, false);
    }
    private ChildEventListener childEventListener = new ChildEventListener() {
        @Override
        public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Customer customer = dataSnapshot.getValue(Customer.class);
            customerList.add(customer);
            customerViewAdapter.notifyDataSetChanged();
            UiUtil.removeSimpleProgressDialog();
        }

        @Override
        public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
            Customer customer = dataSnapshot.getValue(Customer.class);
            customerList.add(customer);
            customerViewAdapter.notifyDataSetChanged();
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
        UiUtil.showSimpleProgressDialog(getActivity(), "Please wait...", "Retrieving your data", true);

        customerList = new ArrayList<>();
        recyclerView = getActivity().findViewById(R.id.customer_list_recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        customerViewAdapter = new CustomerViewAdapter(customerList, getActivity());
        recyclerView.setAdapter(customerViewAdapter);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Customers");
        query = mRef.orderByChild("sid").equalTo(Persistance.uId);
        query.addChildEventListener(childEventListener);
    }

    @Override
    public void onDestroy() {
        query.removeEventListener(childEventListener);
        super.onDestroy();
    }
}
