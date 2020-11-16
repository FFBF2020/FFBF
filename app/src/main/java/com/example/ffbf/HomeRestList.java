package com.example.ffbf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class HomeRestList extends AppCompatActivity {
     private TextView mailLogin;
     private Button logout,restList,streetFood;
     private FirebaseAuth auth;
     private FirebaseUser fbus;
     private Spinner userMenu;
     ArrayAdapter<CharSequence> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_rest_list);

        userMenu = findViewById(R.id.sp_userMenu);
        mailLogin = findViewById(R.id.tv_mail_login);
        logout = findViewById(R.id.btn_logOut);
        restList = findViewById(R.id.btn_restaurants);
        streetFood = findViewById(R.id.btn_streetFood);

        auth = FirebaseAuth.getInstance();
        fbus = auth.getCurrentUser();
       final String userMailLogin = fbus.getEmail();
        mailLogin.setText(userMailLogin);



      adapter = ArrayAdapter.createFromResource(this, R.array.dropDownMenu, android.R.layout.simple_spinner_item);
      adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      userMenu.setAdapter(adapter);

      userMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
          @Override
          public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

              if(parent.getItemAtPosition(position).equals("Profile")) {
                  Intent i = new Intent(HomeRestList.this, Profile.class);
                  i.putExtra("EMAIL", userMailLogin);
                  startActivity(i);

              }

          }

          @Override
          public void onNothingSelected(AdapterView<?> parent) {

          }
      });










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

                Intent i = new Intent (HomeRestList.this, StreetFood.class);
                i.putExtra("Email", fbus.getEmail());
                startActivity(i);

            }
        });

        restList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Intent i = new Intent(HomeRestList.this, RestaurantsList.class);
               i.putExtra("Mail", fbus.getEmail());
               startActivity(i);
            }
        });

    }
}