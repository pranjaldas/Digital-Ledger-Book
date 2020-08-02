package com.android.example.baki_bohi.customer;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.RecyclerView;

import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.models.Customer;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.io.Serializable;
import java.util.List;

public class CustomerViewAdapter extends RecyclerView.Adapter<CustomerViewAdapter.MyViewHolder> implements PopupMenu.OnMenuItemClickListener {


    private List<Customer> customerList;
    private Context mContext;
    private Customer clickedItem;

    public CustomerViewAdapter(List<Customer> customerList, Context context) {
        this.customerList = customerList;
        this.mContext = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.customer_item, parent, false);
        MyViewHolder myViewHolder = new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Customer item = customerList.get(position);
        Log.d("TAG", "onBindViewHolder: " + item);
        holder.name.setText(item.getName());
        holder.address.setText(item.getAddress());
        holder.callCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "Calling " + item.getName(), Toast.LENGTH_SHORT).show();
                String phoneNumber = item.getPhone();
                Intent intent = new Intent(Intent.ACTION_DIAL);
                intent.setData(Uri.parse("tel:" + phoneNumber));
                if (intent.resolveActivity(mContext.getPackageManager()) != null) {
                    mContext.startActivity(intent);
                }

            }
        });
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intn = new Intent(mContext.getApplicationContext(), AddCustomer.class);
                Bundle bundle=new Bundle();
                bundle.putSerializable("Customer_object",item);
                intn.putExtras(bundle);
                mContext.startActivity(intn);
            }
        });
        holder.layout.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                clickedItem = item;
                showContextMenu(item, holder.layout);
                return true;
            }
        });
    }

    private void showContextMenu(Customer item, View view) {
        PopupMenu popup = new PopupMenu(mContext, view);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.customer_list_item_actions, popup.getMenu());
        popup.setOnMenuItemClickListener(this);
        popup.show();
    }

    @Override
    public int getItemCount() {
        return customerList.size();
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.customer_list_item_call:
                callCustomer();
                break;
            case R.id.customer_list_item_message:
                messageCustomer();
                break;
            case R.id.customer_list_item_edit:
                editCustomerDetails();
                break;
        }
        return false;
    }

    private void editCustomerDetails() {
        Toast.makeText(mContext, "Go to edit customer page", Toast.LENGTH_SHORT).show();
        //TODO goto edit customer page
    }

    private void messageCustomer() {
        Toast.makeText(mContext, "Sending message to " + clickedItem.getName(), Toast.LENGTH_SHORT).show();
        String message = "hello world"; //TODO get message from resource file
        Intent sendIntent = new Intent(Intent.ACTION_VIEW);
        sendIntent.setData(Uri.parse("sms:"));
        sendIntent.putExtra("address", clickedItem.getPhone());
        sendIntent.putExtra("sms_body", message);
        mContext.startActivity(sendIntent);
    }

    private void callCustomer() {
        Toast.makeText(mContext, "Calling " + clickedItem.getName(), Toast.LENGTH_SHORT).show();
        String phoneNumber = clickedItem.getPhone();
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + phoneNumber));
        if (intent.resolveActivity(mContext.getPackageManager()) != null) {
            mContext.startActivity(intent);
        }
    }


    public static class MyViewHolder extends RecyclerView.ViewHolder {

        TextView name, address;
        CircularImageView imageView,callCustomer;
        LinearLayout layout;

        public MyViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.customer_item_name);
            address = itemView.findViewById(R.id.customer_item_address);
            imageView = itemView.findViewById(R.id.customer_item_img);
            callCustomer=itemView.findViewById(R.id.selected_customer_phone);
            layout = itemView.findViewById(R.id.customer_item_layout);
        }

    }
}
