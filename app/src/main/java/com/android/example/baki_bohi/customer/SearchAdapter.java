package com.android.example.baki_bohi.customer;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.models.Customer;
import com.android.example.baki_bohi.util.Persistance;
import com.bumptech.glide.Glide;
import com.mikhaellopez.circularimageview.CircularImageView;

import java.util.ArrayList;
import java.util.List;

public class SearchAdapter extends ArrayAdapter {

    private List<Customer> dataList;
    private Context mContext;
    private int itemLayout;

    private ListFilter listFilter = new ListFilter();
    private List<Customer> dataListAllItems;



    public SearchAdapter(Context context, int resource, List<Customer> storeDataList) {
        super(context, resource, storeDataList);
        dataList = storeDataList;
        mContext = context;
        itemLayout = resource;
    }

    @Override
    public int getCount() {
        return dataList.size();
    }

    @Override
    public Customer getItem(int position) {
        return dataList.get(position);
    }

    @Override
    public View getView(int position, View view, @NonNull ViewGroup parent) {
        if (view == null) {
            view = LayoutInflater.from(parent.getContext()).inflate(itemLayout, parent, false);
        }
        //TODO: populate view
        TextView customerName = view.findViewById(R.id.search_customer_item_name);
        TextView customerAddress = view.findViewById(R.id.search_customer_item_address);
        TextView customerPhoneNumber = view.findViewById(R.id.search_customer_item_phone_number);
        CircularImageView customerImageView = view.findViewById(R.id.search_customer_item_img);

        customerName.setText(getItem(position).getName());
        customerAddress.setText(getItem(position).getName());
        customerPhoneNumber.setText(getItem(position).getPhone());
        Glide.with(mContext).load(Persistance.PLACEHOLDER_IMAGE_URL).into(customerImageView);


        return view;
    }



    @NonNull
    @Override
    public Filter getFilter() {
        return listFilter;
    }

    public class ListFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();
            if (dataListAllItems == null) {
                synchronized (mContext) {
                    dataListAllItems = new ArrayList<Customer>(dataList);
                }
            }

            if (prefix == null || prefix.length() == 0) {
                synchronized (mContext) {
                    results.values = dataListAllItems;
                    results.count = dataListAllItems.size();
                }
            } else {
                final String searchStrLowerCase = prefix.toString().toLowerCase();

                ArrayList<Customer> matchValues = new ArrayList<>();

                for (Customer dataItem : dataListAllItems) {
                    if (dataItem.getName().toLowerCase().contains(searchStrLowerCase)) {
                        matchValues.add(dataItem);
                    }
                }

                results.values = matchValues;
                results.count = matchValues.size();
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            if (results.values != null) {
                dataList = (ArrayList<Customer>)results.values;
            } else {
                dataList = null;
            }
            if (results.count > 0) {
                notifyDataSetChanged();
            } else {
                notifyDataSetInvalidated();
            }
        }

    }
}