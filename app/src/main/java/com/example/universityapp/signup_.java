package com.example.universityapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class signup_ extends AppCompatActivity {

    TextView textView;
    EditText name , email_,phoneNumber,password_;
    Button signUp;

    FirebaseAuth mAuth;

    ProgressBar progressBar;


    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Intent intent = new Intent(getApplicationContext(), menubottomnavigationbar.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        textView = findViewById(R.id.textView8);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , signIn.class);
                startActivity(intent);
                finish();
            }
        });

        name = findViewById(R.id.editTextTextpersonName);
        email_ = findViewById(R.id.editTextTextEmailAddress);
        phoneNumber = findViewById(R.id.editTextTextPhoneNumber);
        password_ = findViewById(R.id.editTextTextPassword);

        mAuth = FirebaseAuth.getInstance();

        progressBar = findViewById(R.id.progressbar);

        signUp = findViewById(R.id.button1);
        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                progressBar.setVisibility(View.VISIBLE);
                String email = String.valueOf(email_.getText());
                String password = String.valueOf(password_.getText());

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(signup_.this , "please enter your email" , Toast.LENGTH_SHORT).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(signup_.this , "please enter your password" , Toast.LENGTH_SHORT).show();
                    return;
                }

                mAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information

                                    FirebaseUser user = mAuth.getCurrentUser();
                                    Toast.makeText(signup_.this , "Account created" , Toast.LENGTH_SHORT).show();

                                } else {
                                    // If sign in fails, display a message to the user.

                                    Toast.makeText(signup_.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }


                        });
            }
        });
    }
}