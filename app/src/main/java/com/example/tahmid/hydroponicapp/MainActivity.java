package com.example.tahmid.hydroponicapp;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {

    private EditText mobileText,passText;
    private Button loginBtn,regBtn;
    private FirebaseAuth mAuth;
    private String email,password;

   // private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mobileText=findViewById(R.id.phoneId);
        passText=findViewById(R.id.passId);
        loginBtn=findViewById(R.id.loginId);
        regBtn=findViewById(R.id.registerId);
       // progressDialog=new ProgressDialog(getApplicationContext());

        mAuth = FirebaseAuth.getInstance();

       try{
           loginBtn.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View view) {
                   signIn();
               }
           });
       }catch (Exception e){
           Toast.makeText(this,e.getMessage().toString(),Toast.LENGTH_SHORT).show();
       }

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(MainActivity.this,RegistrationActivity.class);
                startActivity(intent);

            }
        });

    }

    public void signIn()
    {


       try{
           email=mobileText.getText().toString().trim();
           password=passText.getText().toString().trim();


           mAuth.signInWithEmailAndPassword(email, password)
                   .addOnCompleteListener(MainActivity.this, new OnCompleteListener<AuthResult>() {
                       @Override
                       public void onComplete(@NonNull Task<AuthResult> task) {
                           if (task.isSuccessful()) {

                               Intent success=new Intent(getApplicationContext(),DashBoard.class);
                               success.putExtra("mail",email);
                               startActivity(success);
                               Toast.makeText(MainActivity.this, "Login Successful",
                                       Toast.LENGTH_SHORT).show();
                           } else {
                               Toast.makeText(MainActivity.this, "Login failed. Please give correct infomation ",
                                       Toast.LENGTH_SHORT).show();

                           }

                       }
                   });

       }catch (Exception e){
           Toast.makeText(this,"Fields can not be empty",Toast.LENGTH_SHORT).show();
       }



    }
}
