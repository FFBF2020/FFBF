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
    private Button restList,streetFood, addPlace;
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
        addPlace = findViewById(R.id.btn_addPlace);
       // Get the current user from Firebase and display the email
        auth = FirebaseAuth.getInstance();
        fbus = auth.getCurrentUser();
        final String userMailLogin = fbus.getEmail();
        mailLogin.setText(userMailLogin);


         //Initialize adapter and set the items from String resource directory
        adapter = ArrayAdapter.createFromResource(this, R.array.dropDownMenu, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        userMenu.setAdapter(adapter);
       //Dropdown menu, displaying options to see all users or to log out
        userMenu.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                  // the position 0 is the first item, navigating to list with all users
                    case 0:
                        Intent i = new Intent(HomeList.this, UserList.class);
                        i.putExtra("EMAIL", userMailLogin);
                        startActivity(i);

                        break;


                    case 1:
                       // case 1 is the second item, which is logout
                        auth.getInstance().signOut();
                        Intent intent1 = new Intent(HomeList.this, MainActivity.class);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent1);
                        break;

                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




          // going to Activity displaying street food
        streetFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent (HomeList.this, ListPlaces.class);
                //send user email to can identify later is it user or admin
                i.putExtra("EMAIL", fbus.getEmail());
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
                i.putExtra("EMAIL", fbus.getEmail());
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

    }


}
