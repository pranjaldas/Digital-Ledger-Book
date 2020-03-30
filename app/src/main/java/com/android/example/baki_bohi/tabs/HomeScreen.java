package com.android.example.baki_bohi.tabs;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.android.example.baki_bohi.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.tabs.TabLayout;

public class HomeScreen extends AppCompatActivity implements TabLayout.BaseOnTabSelectedListener {

    private static final int TRANSACTION_FRAGMENT = 0;
    private static final int CUSTOMER_FRAGMENT = 1;
    private static final int NOTIFICATION_FRAGMENT = 2;
    private int selectedTab;
    private ViewPager viewPager;
    private TabLayout tabLayout;
    private FloatingActionButton fab;
    private TabsAdapter tabsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        tabLayout = findViewById(R.id.home_screen_tab_layout);
        viewPager = findViewById(R.id.main_screen_view_pager);
        tabsAdapter = new TabsAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabsAdapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.addOnTabSelectedListener(this);

        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                doOnFabSelected(selectedTab);
            }
        });
    }

    private void doOnFabSelected(int position) {
        switch (position) {
            case CUSTOMER_FRAGMENT:
                Toast.makeText(this, "GOTO add customer", Toast.LENGTH_SHORT).show();
                break;
            case TRANSACTION_FRAGMENT:
                Toast.makeText(this, "Go to add transaction", Toast.LENGTH_SHORT).show();
                break;
            case NOTIFICATION_FRAGMENT:
                Toast.makeText(this, "notification", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        selectedTab = tab.getPosition();
        setFabVisibility(selectedTab);
    }

    private void setFabVisibility(int position) {
        if (position == NOTIFICATION_FRAGMENT) {
            fab.hide();
        } else {
            fab.show();
        }
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    /**
     *
     * **/

}
