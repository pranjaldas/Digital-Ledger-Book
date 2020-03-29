package com.android.example.baki_bohi.tabs;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.viewpager.widget.ViewPager;

import com.android.example.baki_bohi.R;
import com.android.example.baki_bohi.ShopKeeperProfile;
import com.android.example.baki_bohi.customer.AddCustomer;
import com.android.example.baki_bohi.registration.LogIn;
import com.android.example.baki_bohi.transaction.AddTransaction;
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
                Intent intn = new Intent(getApplicationContext(), AddCustomer.class);
                startActivity(intn);
                finish();
                break;
            case TRANSACTION_FRAGMENT:
                Intent intent = new Intent(getApplicationContext(), AddTransaction.class);
                startActivity(intent);
                finish();
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
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();

        inflater.inflate(R.menu.home_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.log_out:
                Toast.makeText(this, "Loging you out", Toast.LENGTH_SHORT).show();
                SharedPreferences myPrefs = getSharedPreferences("bakiBohiPrefs", MODE_PRIVATE);
                SharedPreferences.Editor edit = myPrefs.edit();
                edit.clear();
                edit.commit();
                Intent intn = new Intent(getApplicationContext(), LogIn.class);
                startActivity(intn);
                finish();
                break;
            case R.id.about_app:
                Toast.makeText(this, "Not Yet Code", Toast.LENGTH_SHORT).show();
                break;
            case R.id.contact_us:
                Toast.makeText(this, "Not Yet code", Toast.LENGTH_SHORT).show();
                break;
            case R.id.shopkeeper_profile:
                Intent intnToShop = new Intent(getApplicationContext(), ShopKeeperProfile.class);
                startActivity(intnToShop);
                finish();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
