package com.example.ffbf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeRestList extends AppCompatActivity {
     TextView mailLogin;
     Button logout,restList,streetFood;
     FirebaseAuth auth;
     FirebaseUser fbus;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_rest_list);

        mailLogin = findViewById(R.id.tv_mail_login);
        logout = findViewById(R.id.btn_logOut);
        restList = findViewById(R.id.btn_restaurants);
        streetFood = findViewById(R.id.btn_streetFood);

        auth = FirebaseAuth.getInstance();
        fbus = auth.getCurrentUser();

        mailLogin.setText(fbus.getEmail());

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Sign out and send the user back to login page, clear all Activities
                auth.getInstance().signOut();
                Intent i = new Intent(HomeRestList.this, MainActivity.class);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(i);

            }
        });

        streetFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent (HomeRestList.this, StreetFood.class));

            }
        });

        restList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HomeRestList.this, RestaurantsList.class));
            }
        });

    }
}