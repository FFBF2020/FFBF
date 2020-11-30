package com.example.ffbf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;

import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class Register extends AppCompatActivity {

    //Create imposters
    private EditText fn, sn, mail, password;
    private Button register;

    // Declare FirebaseAuth object
    private FirebaseAuth auth;
    private DatabaseReference dbref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //2. Initialize firebase auth
        auth = FirebaseAuth.getInstance();
        dbref = FirebaseDatabase.getInstance().getReference("_user_");


        // Link the imposters to the XML counterparts
        fn = findViewById(R.id.et_fn);
        sn = findViewById(R.id.et_ln);
        mail = findViewById(R.id.et_email);
        password = findViewById(R.id.et_pass);
        register = findViewById(R.id.btn_reg);







            // Register a new user
            register.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if (!(TextUtils.isEmpty(mail.getText().toString()) &&
                            TextUtils.isEmpty(password.getText().toString()) && TextUtils.isEmpty(fn.getText().toString()) && TextUtils.isEmpty(sn.getText().toString()))) {

                        auth.createUserWithEmailAndPassword(mail.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {

                                    Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_LONG).show();


                                    User user = new User(fn.getText().toString(), sn.getText().toString(), mail.getText().toString(), password.getText().toString(), "user");
                                    dbref.child(dbref.push().getKey()).setValue(user);

                                    startActivity(new Intent(Register.this, MainActivity.class));
                                    finish();

                                } else {
                                    Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();

                                }

                            }
                        });


                    }

                }

            });

        }

    }








