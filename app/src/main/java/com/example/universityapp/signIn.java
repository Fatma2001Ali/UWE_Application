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

public class signIn extends AppCompatActivity {
    Button button;
    TextView textView;

    EditText email_ , password_;

    ProgressBar progressbar;

    FirebaseAuth mAuth;

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
        setContentView(R.layout.activity_sign_in);

        button = findViewById(R.id.button1);
        email_ = findViewById(R.id.editTextTextEmailAddress);
        password_ = findViewById(R.id.editTextTextPassword);

        progressbar = findViewById(R.id.progressbar);

        mAuth = FirebaseAuth.getInstance();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                progressbar.setVisibility(View.VISIBLE);
                String email = String.valueOf(email_.getText());
                String password = String.valueOf(password_.getText());

                if(TextUtils.isEmpty(email )){
                    Toast.makeText(signIn.this , "please enter your email" , Toast.LENGTH_SHORT).show();
                }
               if(TextUtils.isEmpty(password) ){
                    Toast.makeText(signIn.this , "please enter your password" , Toast.LENGTH_SHORT).show();
                }



                mAuth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener( new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull com.google.android.gms.tasks.Task<AuthResult> task) {
                                progressbar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {


                                    Toast.makeText(signIn.this, "Login Successful.",
                                            Toast.LENGTH_SHORT).show();

                                Intent intent = new Intent(getApplicationContext(), menubottomnavigationbar.class);
                                startActivity(intent);
                                finish();
                            }else {

                                    Toast.makeText(signIn.this, "Authentication failed.",
                                            Toast.LENGTH_SHORT).show();

                                }
                            }

                        });
            }
        });



        textView = findViewById(R.id.textView8);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext() , signup_.class);
                startActivity(intent);
                finish();
            }
        });

    }

}