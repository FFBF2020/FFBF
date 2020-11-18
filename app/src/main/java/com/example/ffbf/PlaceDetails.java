package com.example.ffbf;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class PlaceDetails extends AppCompatActivity {

    ImageView iv;
    TextView name, descr;
    Button rev, addRev, book;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_place_details);

        Intent i = getIntent();
        RestAndStrFood rasf = i.getParcelableExtra("Place");

        iv = findViewById(R.id.iv_image);
        name = findViewById(R.id.tv_name);
        descr = findViewById(R.id.tv_descr);
        rev = findViewById(R.id.btn_seeRev);
        addRev = findViewById(R.id.btn_addRev);
        book = findViewById(R.id.btn_book);



        name.setText(rasf.getName());
        descr.setText(rasf.getDescription());
        Picasso.get().load(rasf.getUrl()).fit().into(iv);



    }
}