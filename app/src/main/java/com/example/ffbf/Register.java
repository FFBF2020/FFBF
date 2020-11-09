package com.example.ffbf;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class Register extends AppCompatActivity {
     EditText fn, sn,mail,password;
     Button register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        fn.findViewById(R.id.et_fn);
        sn.findViewById(R.id.et_ln);
        mail.findViewById(R.id.et_email);
        password.findViewById(R.id.et_pass);

        register.findViewById(R.id.btn_reg);

       register.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

           }
       });
    }
}