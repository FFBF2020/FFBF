package com.example.ffbf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddReview extends AppCompatActivity {

    private EditText userLoginMail, text_review;
    private Button saveReview;
    private String userMail, placeName;
    private DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_review);

        userLoginMail = findViewById(R.id.et_userLoginMail);
        text_review = findViewById(R.id.etML_writeRev);
        saveReview = findViewById(R.id.btn_saveRev);
         // get intents
        Intent i = getIntent();
        userMail = i.getStringExtra("EMAIL");
        placeName = i.getStringExtra("PlaceName");
        userLoginMail.setText(userMail);


     //when save button is clicked
        saveReview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //db reference to new path reviews
                dbref = FirebaseDatabase.getInstance().getReference("_reviews_");
                final String id = dbref.push().getKey();
                //if edit text boxes are not empty, register new review
                if (!(TextUtils.isEmpty(text_review.getText().toString()))) {

                    Review review = new Review(placeName, userMail, text_review.getText().toString());
                    dbref.child(id).setValue(review);
                            Toast.makeText(AddReview.this, "Successfully added a review", Toast.LENGTH_LONG).show();
                            Intent i = new Intent(AddReview.this, HomeList.class);
                            startActivity(i);
                    finish();
                        }

                else {
                    //else show message
                    Toast.makeText(AddReview.this, "Please fill all fields", Toast.LENGTH_LONG).show();

                }
            }
        });


        }
}