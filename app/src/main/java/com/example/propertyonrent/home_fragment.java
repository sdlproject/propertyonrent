package com.example.propertyonrent;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import com.example.propertyonrent.*;
import com.example.propertyonrent.R;
import com.example.propertyonrent.dashboard.dashboardfragment;
import com.example.propertyonrent.home1.home1_fragment;
import com.example.propertyonrent.notification.notification_fragment;
import com.example.propertyonrent.profile.profile_fragment;
import com.google.android.material.bottomnavigation.BottomNavigationItemView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.bottomnavigation.BottomNavigationView.OnNavigationItemSelectedListener;



public class home_fragment extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_fragment_layout);
        getSupportActionBar().hide ();

        //loading the default fragment
        loadFragment(new home1_fragment());

        //getting bottom navigation view and attaching the listener
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {

        Log.i("hii","In navigationitemselect");
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.navigation_home:
                fragment = new home1_fragment();
                break;

            case R.id.navigation_dashboard:
                fragment = new dashboardfragment();
                break;

            case R.id.navigation_notifications:
                fragment = new notification_fragment();
                break;

            case R.id.navigation_profile:
                fragment = new profile_fragment();
                break;
        }

        return loadFragment(fragment);
    }
    }
