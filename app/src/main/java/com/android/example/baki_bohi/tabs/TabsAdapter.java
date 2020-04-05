package com.android.example.baki_bohi.tabs;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.android.example.baki_bohi.customer.CustomerFragment;
import com.android.example.baki_bohi.notes.NoteFragement;
import com.android.example.baki_bohi.transaction.TransactionFragment;

public class TabsAdapter extends FragmentPagerAdapter {

    private static final String[] TAB_TITLES = {"Transactions", "Customers", "Notes"};

    public TabsAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return TransactionFragment.getInstance();
            case 1:
                return CustomerFragment.getInstance();
            case 2:
                return NoteFragement.getInstance();
            default:
                return null;
        }
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return TAB_TITLES[position];
    }

    @Override
    public int getCount() {
        return 3;
    }
}
