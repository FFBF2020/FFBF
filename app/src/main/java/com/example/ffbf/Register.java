package com.example.ffbf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity {

    //Create imposters
     EditText fn, sn,mail,password;
     Button register;

     //Database reference object
     DatabaseReference dbref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

       // Link the imposters to the XML counterparts
        fn = findViewById(R.id.et_fn);
        sn = findViewById(R.id.et_ln);
        mail = findViewById(R.id.et_email);
        password = findViewById(R.id.et_pass);
        register = findViewById(R.id.btn_reg);

        // Link db reference object to the node needed to search in fb

        dbref = FirebaseDatabase.getInstance().getReference("_user_");

       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(!(TextUtils.isEmpty(mail.getText().toString()) &&
                       TextUtils.isEmpty(password.getText().toString()) && TextUtils.isEmpty(fn.getText().toString()) && TextUtils.isEmpty(sn.getText().toString())))
               {
                     // db reference to save object
                   User user = new User(fn.getText().toString(), sn.getText().toString(), mail.getText().toString(), password.getText().toString());
                   dbref.child(dbref.push().getKey()).setValue(user);


               }
                   // Back to login page
               Intent i = new Intent(Register.this, MainActivity.class);
                startActivity(i);

           }
       });
    }
}