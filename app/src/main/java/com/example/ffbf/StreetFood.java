package com.example.ffbf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StreetFood extends AppCompatActivity {

    Button addNew;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_street_food);


        addNew = findViewById(R.id.btn_add_newStrFood);



    addNew.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            startActivity(new Intent(StreetFood.this, AddNewStrFoodShop.class));
        }
    });






    }
}