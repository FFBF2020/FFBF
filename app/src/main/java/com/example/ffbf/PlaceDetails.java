package com.example.ffbf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

public class PlaceDetails extends AppCompatActivity {

    ImageView iv;
    TextView name, descr;
    Button rev, addRev, book;
    String placeType, userType, placeName, userLoginMail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        Intent i = getIntent();
        RestAndStrFood rasf = i.getParcelableExtra("Place");
        placeType = i.getStringExtra("PlaceType");
        userType = i.getStringExtra("UserType");
        userLoginMail = i.getStringExtra("EMAIL");
        placeName = rasf.getName();

        iv = findViewById(R.id.iv_place);
        name = findViewById(R.id.tv_name);
        descr = findViewById(R.id.tv_descr);
        rev = findViewById(R.id.btn_seeRev);
        addRev = findViewById(R.id.btn_addRev);
        book = findViewById(R.id.btn_book);



        name.setText(rasf.getName());
        descr.setText(rasf.getDescription());
        Picasso.get().load(rasf.getUrl()).fit().into(iv);


        if(userType.equals("user") && placeType.equals("rest")){
            addRev.setVisibility(View.INVISIBLE);

        }
        else {
            addRev.setVisibility(View.VISIBLE);
        }

        if(placeType.equals("stall")){
            book.setVisibility(View.INVISIBLE);
        }

        // button to see all reviews
        rev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent(PlaceDetails.this, ReviewsList.class);
                i.putExtra("PlaceName", placeName);
                i.putExtra("UserType", userType);
                i.putExtra("EMAIL", userLoginMail);

                startActivity(i);

            }
        });


        // add review

        addRev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


    }
}