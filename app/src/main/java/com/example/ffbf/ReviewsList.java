package com.example.ffbf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.content.Intent;
import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ReviewsList extends AppCompatActivity implements ReviewAdapter.Holder.ReviewInterface {

                RecyclerView rv;
                RecyclerView.LayoutManager mng;
                ReviewAdapter adapter;
                DatabaseReference dbref;
                ArrayList<Review> list = new ArrayList<>();
                private String type;


                @Override
                protected void onCreate(Bundle savedInstanceState) {
                        super.onCreate(savedInstanceState);
                        setContentView(R.layout.activity_reviews_list);
                        // connect XML widget with java class. Get the intent from previous activity
                        rv = findViewById(R.id.r_view);
                        Intent i = getIntent();
                        // type is the type of the places - restaurants or stalls
                        type = i.getStringExtra("TYPE");
                        //set Firebase path
                        dbref = FirebaseDatabase.getInstance().getReference("_review_");
                        mng = new LinearLayoutManager(ReviewsList.this);
                        rv.setLayoutManager(mng);
                        dbref.addListenerForSingleValueEvent(listener);
                }
                //get data from Firebase, using loop and set it to Arraylist
                ValueEventListener listener = new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                for (DataSnapshot dss : snapshot.getChildren()) {
                                        Review review = dss.getValue(Review.class);

                                        list.add(review);


                                }
                                //set adapter with the list of places
                                adapter = new ReviewAdapter(list, ReviewsList.this);
                                rv.setAdapter(adapter);

                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                };
                // when the user click on the image, will be relocated to details of the place
                @Override
                public void onPlaceClick(int i) {
                        Intent intent = new Intent(ReviewsList.this, ReviewDetails.class);
                        intent.putExtra("Place", list.get(i));
                        startActivity(intent);

                }

}