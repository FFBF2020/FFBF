package com.example.ffbf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.Intent;
import android.os.Bundle;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class ListPlaces extends AppCompatActivity implements ListAdapter.Holder.PlacesInterface {

    private RecyclerView rv;
    private RecyclerView.LayoutManager mng;
   private ListAdapter adapter;
    ArrayList<RestAndStrFood> list = new ArrayList<>();
    private String typePlace, userType, userLoginMail;
   private Query dbref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_places);
      // connect XML widget with java class. Get the intent from previous activity
        rv = findViewById(R.id.r_view);
        Intent i = getIntent();
        // get the type of the place and the type of the user
        typePlace = i.getStringExtra("TYPE");
        userType = i.getStringExtra("UserType");
        userLoginMail = i.getStringExtra("EMAIL");
        //set Firebase path
        dbref = FirebaseDatabase.getInstance().getReference("_places_").orderByChild("placeType").equalTo(typePlace);
        mng = new LinearLayoutManager(ListPlaces.this);
        rv.setLayoutManager(mng);
        dbref.addListenerForSingleValueEvent(listener);
    }
     //get data from Firebase, using loop and set it to Arraylist
   ValueEventListener listener = new ValueEventListener() {
        @Override
       public void onDataChange(@NonNull DataSnapshot snapshot) {
            for (DataSnapshot dss : snapshot.getChildren()) {
                RestAndStrFood rast = dss.getValue(RestAndStrFood.class);

                list.add(rast);


            }

            //set adapter with the list of places
   adapter = new ListAdapter(list, ListPlaces.this);
                 rv.setAdapter(adapter);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
          // when the user click on the image, will be relocated to details of the place
    @Override
    public void onPlaceClick(int i) {
     Intent intent = new Intent(ListPlaces.this, PlaceDetails.class);
     intent.putExtra("Place", list.get(i));
     intent.putExtra("UserType", userType);
     intent.putExtra("PlaceType", typePlace);
     intent.putExtra("EMAIL", userLoginMail);
     startActivity(intent);

    }
}