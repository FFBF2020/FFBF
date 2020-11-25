package com.example.ffbf;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomeList extends AppCompatActivity  {

    private TextView mailLogin;
    private Button restList,streetFood, addPlace, logout, users;
    private FirebaseAuth auth;
    private FirebaseUser fbus;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list);

        logout = findViewById(R.id.btn_logout);
        users = findViewById(R.id.btn_users);
        mailLogin = findViewById(R.id.tv_mail_login);
        restList = findViewById(R.id.btn_restaurants);
        streetFood = findViewById(R.id.btn_streetFood);
        addPlace = findViewById(R.id.btn_addPlace);
       // Get the current user from Firebase and display the email
        auth = FirebaseAuth.getInstance();
        fbus = auth.getCurrentUser();
        final String userMailLogin = fbus.getEmail();
        mailLogin.setText(userMailLogin);



         //logout and clear all activities
         logout.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                 auth.getInstance().signOut();
                 Intent i= new Intent(HomeList.this, MainActivity.class);
                 i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                 i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                 startActivity(i);
             }
         });


          // going to Activity displaying street food
        streetFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent (HomeList.this, ListPlaces.class);
                //send user email to can identify later is it user or admin
                i.putExtra("EMAIL", userMailLogin);
                // this value will select only stalls to be shown
                i.putExtra("TYPE", "Stall");
                startActivity(i);

            }
        });
         //navigates to the same Activity as above, but will display only restaurants
        restList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeList.this, ListPlaces.class);
                i.putExtra("EMAIL", userMailLogin);
                //the type will select only restaurants to be shown
                i.putExtra("TYPE", "Rest");
                startActivity(i);
            }
        });

         // to navigate to register Activity
        addPlace.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeList.this, AddNewStrFoodShop.class);
                startActivity(i);
            }
        });

       //to see all users
        users.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent (HomeList.this, UserList.class);
                i.putExtra("MAIL", userMailLogin);
                startActivity(i);
            }
        });

    }


}
