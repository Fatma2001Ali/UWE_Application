package com.example.universityapp;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

public class homeFragment extends Fragment implements NavigationView.OnNavigationItemSelectedListener {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);

//        // Set up NavigationView
//        NavigationView navigationView = view.findViewById(R.id.nav_view);
//        navigationView.setNavigationItemSelectedListener(this);

        return view;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here
        switch (item.getItemId()) {
//            case com.google.android.material.R.id.accelerate:
//                // Handle menu item 1 click
//                // Example: startActivity(new Intent(getActivity(), MyActivity.class));
//                break;
//            case androidx.coordinatorlayout.R.id.action_image:
//                // Handle menu item 2 click
//                break;
//            // Add more cases for other menu items as needed

            default:
                break;
        }

        // Highlight the selected item in the navigation view
        item.setChecked(true);

        // Close the navigation drawer or perform any other action
        // Example: DrawerLayout drawer = getActivity().findViewById(R.id.drawer_layout);
        // drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
