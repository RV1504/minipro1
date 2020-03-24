package com.example.miniproject1;

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

import com.example.miniproject1.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login extends AppCompatActivity {
    EditText mFullName,mEmail,mPassword,mPhone;
    Button mRegister;
    TextView mLoginBtn;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    TextView mCreatebtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mFullName=findViewById(R.id.fullname);
        mEmail=findViewById(R.id.email);
        mPassword=findViewById(R.id.password);
        mPhone=findViewById(R.id.phone);
        mRegister=findViewById(R.id.reg_btn);
        mLoginBtn=findViewById(R.id.login_btn);
        mCreatebtn=findViewById(R.id.registerText);
        fAuth=FirebaseAuth.getInstance();
        progressBar=findViewById(R.id.progressBar);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=mEmail.getText().toString().trim();
                String password=mPassword.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required");
                    return;
                }
                if (TextUtils.isEmpty(password))
                {
                    mPassword.setError("Password is Required");
                    return;
                }

                if(password.length()<6)
                {
                    mPassword.setError("Password must be at least 6 character long");
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);

                fAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful())
                        {
                            Toast.makeText(login.this, "Logged in Successfully", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        }
                        else
                        {
                            Toast.makeText(login.this, "Error!!"+task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    }
                });

            }

        });
        mCreatebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),register.class));
            }

        });
    }}
