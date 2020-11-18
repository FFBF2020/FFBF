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

public class ListPlaces extends AppCompatActivity implements ListAdapter.Holder.PlacesInterface {

    RecyclerView rv;
    RecyclerView.LayoutManager mng;
    ListAdapter adapter;
    DatabaseReference dbref;
    ArrayList<RestAndStrFood> list = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_places);

        rv = findViewById(R.id.r_view);
        dbref = FirebaseDatabase.getInstance().getReference("_places_");
        mng = new LinearLayoutManager(ListPlaces.this);
        rv.setLayoutManager(mng);
    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot dss: snapshot.getChildren()){
                RestAndStrFood rast = dss.getValue(RestAndStrFood.class);
                list.add(rast);
            }
            adapter = new ListAdapter(list, ListPlaces.this);
            rv.setAdapter(adapter);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

    @Override
    public void onPlaceClick(int i) {
     Intent intent = new Intent(ListPlaces.this, PlaceDetails.class);
     intent.putExtra("Place", list.get(i));
     startActivity(intent);

    }
}