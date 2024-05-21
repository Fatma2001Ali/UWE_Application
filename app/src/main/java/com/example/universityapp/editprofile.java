package com.example.universityapp;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class editprofile extends Fragment {

    Button button_2;

    public editprofile(){


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_editprofile, container, false);


        button_2 = rootView.findViewById(R.id.button2);


        button_2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//                Toast.makeText(getActivity(), "message", Toast.LENGTH_SHORT).show();

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();


                FragmentTransaction transaction = fragmentManager.beginTransaction();


                transaction.replace(R.id.framLatOut, new accountFragment());

                transaction.addToBackStack(null);

                transaction.commit();

            }
        });


        return rootView;
    }


}