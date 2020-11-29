package com.example.ffbf;

import androidx.annotation.NonNull;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;


public class HomeList extends AppCompatActivity  {

    private TextView mailLogin;
    private Button restList,streetFood, addPlace, logout, users;
    private FirebaseAuth auth;
    private FirebaseUser fbus;
    private DatabaseReference dbref;
    ArrayList<User> list = new ArrayList<>();
    private String userType;




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
        dbref = FirebaseDatabase.getInstance().getReference("_user_");



        // take all users from Firebase and save it into ArrayList with loop
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dss:snapshot.getChildren()){

                    User u = dss.getValue(User.class);
                    list.add(u);
                }
                getCurrentUserType();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        };

        dbref.addListenerForSingleValueEvent(listener);


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
                i.putExtra("TYPE", "stall");
                i.putExtra("UserType", userType);
                startActivity(i);

            }
        });
         //navigates to the same Activity as above, but will display only restaurants
        restList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeList.this, ListPlaces.class);
                i.putExtra("EMAIL", userMailLogin);
                i.putExtra("UserType", userType);
                //the type will select only restaurants to be shown
                i.putExtra("TYPE", "rest");
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
                i.putExtra("UserType", userType);

                startActivity(i);
            }
        });



    }

public void getCurrentUserType() {

        for(int i=0; i<list.size(); i++) {

            if(auth.getCurrentUser().getEmail().equals(list.get(i).getMail())) {
                userType = list.get(i).getType();
            }
        }
}
}
