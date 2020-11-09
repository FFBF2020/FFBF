package com.example.ffbf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class HomeRestList extends AppCompatActivity {
     TextView user;
     String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_rest_list);

        user.findViewById(R.id.tv_user);


        Intent i2 = getIntent();
        username = i2.getStringExtra("USERNAME");
        user.setText(username);




    }
}