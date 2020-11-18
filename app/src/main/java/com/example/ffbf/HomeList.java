package com.example.ffbf;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class HomeList extends AppCompatActivity {

    private TextView mailLogin;
    private Button restList,streetFood;
    private FirebaseAuth auth;
    private FirebaseUser fbus;
    private Spinner userMenu;
    ArrayAdapter<CharSequence> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_list);

        userMenu = findViewById(R.id.sp_userMenu);
        mailLogin = findViewById(R.id.tv_mail_login);
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
                    Intent i = new Intent(HomeList.this, Profile.class);
                    i.putExtra("EMAIL", userMailLogin);
                    startActivity(i);

                }
                else if(parent.getItemAtPosition(position).equals("Logout")){

                    auth.getInstance().signOut();
                    Intent i = new Intent(HomeList.this, MainActivity.class);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(i);
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        streetFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent (HomeList.this, ListPlaces.class);
                i.putExtra("EMAIL", fbus.getEmail());
                i.putExtra("TYPE", "Stall");
                startActivity(i);

            }
        });

        restList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(HomeList.this, ListPlaces.class);
                i.putExtra("EMAIL", fbus.getEmail());
                i.putExtra("TYPE", "Restaurant");
                startActivity(i);
            }
        });

    }
    }
