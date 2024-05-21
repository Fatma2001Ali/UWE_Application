package com.example.universityapp;

import android.app.Activity;
import android.content.Intent;
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
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class accountFragment extends Fragment {

    Button button_2, button_3 , signout;

    FirebaseUser user;
    FirebaseAuth auth;

    public  accountFragment(){


    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_account, container, false);


       button_3 = rootView.findViewById(R.id.button3);


        button_3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



//                Toast.makeText(getActivity(), "message", Toast.LENGTH_SHORT).show();

                FragmentManager fragmentManager = requireActivity().getSupportFragmentManager();


                FragmentTransaction transaction = fragmentManager.beginTransaction();


                transaction.replace(R.id.framLatOut, new editprofile());

                transaction.addToBackStack(null);

                transaction.commit();

            }
        });

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
        auth = FirebaseAuth.getInstance();
        user = auth.getCurrentUser();

        if(user == null){
            Intent i = new Intent(getActivity(), signIn.class);
            startActivity(i);
            ((Activity) getActivity()).overridePendingTransition(0, 0);


        }else{

        }
        signout =rootView.findViewById(R.id.button6);
        signout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                FirebaseAuth.getInstance().signOut();
                Intent i = new Intent(getActivity(), signIn.class);
                startActivity(i);
                ((Activity) getActivity()).overridePendingTransition(0, 0);
            }
        });

        return rootView;
    }


}