package com.example.tahmid.hydroponicapp;


import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegistrationActivity extends AppCompatActivity {

    private TextInputLayout textInputEmail;
    private TextInputLayout textInputUsername;
    private TextInputLayout textInputPassword;
    private Button confirmBtn;
    private FirebaseAuth ath;
    private DatabaseReference ref,ref2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        textInputEmail = findViewById(R.id.text_input_email);
        textInputUsername = findViewById(R.id.text_input_username);
        textInputPassword = findViewById(R.id.text_input_password);
        confirmBtn=findViewById(R.id.conId);
        ath=FirebaseAuth.getInstance();
        ref= FirebaseDatabase.getInstance().getReference("Users");


        confirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmInput();
            }
        });


    }

    private boolean validateEmail() {
        String emailInput = textInputEmail.getEditText().getText().toString().trim();

        if (emailInput.isEmpty()) {
            textInputEmail.setError("Field can't be empty");
            return false;
        } else {
            textInputEmail.setError(null);
            return true;
        }
    }

    private boolean validateUsername() {
        String usernameInput = textInputUsername.getEditText().getText().toString().trim();

        if (usernameInput.isEmpty()) {
            textInputUsername.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            textInputUsername.setError("Username too long");
            return false;
        } else {
            textInputUsername.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = textInputPassword.getEditText().getText().toString().trim();

        if (passwordInput.isEmpty()) {
            textInputPassword.setError("Field can't be empty");
            return false;
        } else {
            textInputPassword.setError(null);
            return true;
        }
    }

    public void confirmInput() {
        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            return;
        }

        final String email=textInputEmail.getEditText().getText().toString().trim();
        final String name=textInputUsername.getEditText().getText().toString().trim();
        final String passWord=textInputPassword.getEditText().getText().toString().trim();

        Toast.makeText(this,email+" "+name+" "+passWord, Toast.LENGTH_SHORT).show();

        ath.createUserWithEmailAndPassword(email, passWord)
                .addOnCompleteListener(RegistrationActivity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            SensorData s1=new SensorData("0","0","0","0");

                            Users r1=new Users(email,name,passWord);
                            FirebaseUser user = ath.getCurrentUser();
                            ref=FirebaseDatabase.getInstance().getReference("Users");
                            ref.child(user.getUid()).setValue(r1);
                            ref.child(user.getUid()).child("Values").setValue(s1);

                            Toast.makeText(RegistrationActivity.this, "Authentication Successful",
                                    Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                        }

                    }
                });
        //nsaocnoc

    }
}
