package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class menubottomnavigationbar extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FrameLayout frameLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menubottomnavigationbar);

        bottomNavigationView = findViewById(R.id.bottomNavBar);
        frameLayout = findViewById(R.id.framLatOut);

        // Set listener for bottom navigation view
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Fragment selectedFragment = null;

                // Determine which fragment to load based on the selected item
                if (menuItem.getItemId() == R.id.home) {
                    selectedFragment = new homeFragment();
                } else if (menuItem.getItemId() == R.id.search) {
                    selectedFragment = new searchFragment();
                } else if (menuItem.getItemId() == R.id.notification) {
                    selectedFragment = new notificationFragment();
                } else if (menuItem.getItemId() == R.id.account) {
                    selectedFragment = new accountFragment();
                }


                // Load the selected fragment
                loadFragment(selectedFragment);
                return true;
            }
        });

        // Load initial fragment
        loadFragment(new homeFragment());
    }


    private void loadFragment(Fragment fragment) {
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.framLatOut, fragment)
                    .commit();
        }

    }


}