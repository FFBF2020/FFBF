package com.example.ffbf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DownloadManager;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class ReviewDetails extends AppCompatActivity {

    TextView mailAuthor, reviewText;
    Button delete, back;
    String currentUserMail, userType;
   Query dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_details);


        mailAuthor = findViewById(R.id.tv_reviewMail);
        reviewText = findViewById(R.id.etML_review);
        delete = findViewById(R.id.btn_deleteReview);
        back = findViewById(R.id.btn_back2);


        // get intent
        Intent i = getIntent();
       final Review review = getIntent().getParcelableExtra("ReviewDetails");
        currentUserMail = i.getStringExtra("EMAIL");
        userType = i.getStringExtra("UserType");
        final String reviewT = review.getReview();

        mailAuthor.setText(review.getUserMail());
        reviewText.setText(review.getReview());

        // if current user is admin or author of the review, delete button visible
        if(currentUserMail.equals(review.getUserMail()) || userType.equals("admin")) {
            delete.setVisibility(View.VISIBLE);

            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //query to find the specific review for deleting
                    dbref = FirebaseDatabase.getInstance().getReference("_places").orderByChild("review").equalTo(reviewT);
                    // looping to find the data for deleting
                    dbref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            for(DataSnapshot dss: snapshot.getChildren()){
                                   dss.getRef().removeValue();


                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            });

  }
        else {

            delete.setVisibility(View.INVISIBLE);
        }

        //back to all reviews
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ReviewDetails.this, ReviewsList.class));
            }
        });
    }
}