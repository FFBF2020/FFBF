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

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity {

    //Create imposters
    EditText fn, sn, mail, password;
    Button register;
    String basic;

    // Declare FirebaseAuth object
 FirebaseAuth auth;
 DatabaseReference dbref;

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




        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!(TextUtils.isEmpty(mail.getText().toString()) &&
                        TextUtils.isEmpty(password.getText().toString()) && TextUtils.isEmpty(fn.getText().toString()) && TextUtils.isEmpty(sn.getText().toString()))) {

                    auth.createUserWithEmailAndPassword(mail.getText().toString(), password.getText().toString()).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if(task.isSuccessful()){

                                Toast.makeText(Register.this, "Registered Successfully", Toast.LENGTH_LONG).show();
                            }
                            else {
                                Toast.makeText(Register.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }

                        }
                    });

                    // db reference to save object
                    dbref = FirebaseDatabase.getInstance().getReference("_user_");
                    User user = new User (fn.getText().toString(), sn.getText().toString(), mail.getText().toString(), password.getText().toString(), "user");
                    dbref.child(dbref.push().getKey()).setValue(user);

                    // Back to login page
                    Intent i = new Intent(Register.this, MainActivity.class);
                    startActivity(i);
                }
            }

        });




    }

}








