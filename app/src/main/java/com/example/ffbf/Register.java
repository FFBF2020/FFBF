package com.example.ffbf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;




public class Register extends AppCompatActivity {

    //Create imposters
    EditText fn, sn, mail, password;
    Button register;
    String basic;

    // Declare FirebaseAuth object
 FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //2. Initialize firebase auth
        auth = FirebaseAuth.getInstance();

        // Link the imposters to the XML counterparts
        fn = findViewById(R.id.et_fn);
        sn = findViewById(R.id.et_ln);
        mail = findViewById(R.id.et_email);
        password = findViewById(R.id.et_pass);
        register = findViewById(R.id.btn_reg);

        // Link db reference object to the node needed to search in fb


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(TextUtils.isEmpty(mail.getText().toString()) &&
                        TextUtils.isEmpty(password.getText().toString()) && TextUtils.isEmpty(fn.getText().toString()) && TextUtils.isEmpty(sn.getText().toString()))) {
                    // db reference to save object
                    auth.createUserWithEmailAndPassword(mail.getText().toString(), password.getText().toString()).addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                        @Override
                        public void onSuccess(AuthResult authResult) {
                            Toast.makeText(Register.this, "Registration successful", Toast.LENGTH_LONG).show();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            System.out.println("Please fill all fields");
                        }
                    });
                }
            }

        });
        // Back to login page
        Intent i = new Intent(Register.this, MainActivity.class);
        startActivity(i);
    }
}








