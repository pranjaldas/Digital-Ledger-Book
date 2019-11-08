package com.android.example.baki_bohi.customer;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

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

public class ViewCustomer extends AppCompatActivity {
    FirebaseDatabase mDatabase;
    DatabaseReference mRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);
        UiUtil.showSimpleProgressDialog(ViewCustomer.this, "Please wait...", "Retrieving your data", true);

        final ArrayList<String> customerlist = new ArrayList<String>();
        final ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(ViewCustomer.this, android.R.layout.simple_list_item_1, customerlist);
        final ListView listView = findViewById(R.id.customer_list);

        mDatabase = FirebaseDatabase.getInstance();
        mRef = mDatabase.getReference("Customers");
        Query query = mRef.orderByChild("sid").equalTo(Persistance.uId);

        query.addChildEventListener(new ChildEventListener() {

            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

//              for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    Customer ctmr = dataSnapshot.getValue(Customer.class);
                    customerlist.add(ctmr.getName());
                listView.setAdapter(itemsAdapter);
                UiUtil.removeSimpleProgressDialog();
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                Customer ctmr = dataSnapshot.getValue(Customer.class);
                customerlist.add(ctmr.getName());
                itemsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(getApplicationContext(), "No data", Toast.LENGTH_SHORT).show();

            }

        });

//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//
//                for(DataSnapshot snapshot:dataSnapshot.getChildren()){
//                    Customer ctmr=dataSnapshot.getValue(Customer.class);
//                    customerlist.add(ctmr.getName());
//                }
//
//                ArrayAdapter<String> itemsAdapter = new ArrayAdapter<String>(ViewCustomer.this, android.R.layout.simple_list_item_1, customerlist);
//                ListView listView = findViewById(R.id.customer_list);
//
//                listView.setAdapter(itemsAdapter);
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError databaseError) {
//                Toast.makeText(getApplicationContext(),"View Unsuccessfull",Toast.LENGTH_SHORT).show();
//            }
//        });


    }


}
