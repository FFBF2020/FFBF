package com.example.ffbf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    EditText  mail, password;
    Button login;
    String Email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mail.findViewById(R.id.et_username);
        password.findViewById(R.id.et_password);
        login.findViewById(R.id.btn_login);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Email = (mail.getText().toString());

                Intent i = new Intent(MainActivity.this, HomeRestList.class);
                i.putExtra("MAIL", Email);

                startActivity(i);
            }
        });




    }
}