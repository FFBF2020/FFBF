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
import java.util.List;

public class UserList extends AppCompatActivity implements  UserAdapter.UserHolder.OnUserClickListener {

    RecyclerView rv;
    DatabaseReference dbref;
    UserAdapter adapter;
    ArrayList<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);


        rv.findViewById(R.id.id_rv);
        rv.setLayoutManager(new LinearLayoutManager(UserList.this));
        dbref = FirebaseDatabase.getInstance().getReference("_user_");
        dbref.addListenerForSingleValueEvent(listener);

    }

    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot dss:snapshot.getChildren()){

                User u = dss.getValue(User.class);
                list.add(u);
            }

            adapter = new UserAdapter(list, UserList.this);
            rv.setAdapter(adapter);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };


    @Override
    public void onUserClicked(int position) {
        Intent i = new Intent (UserList.this, Profile.class);
        i.putExtra("User", list.get(position));
        startActivity(i);

    }
}