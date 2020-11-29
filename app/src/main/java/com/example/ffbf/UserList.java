package com.example.ffbf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class UserList extends AppCompatActivity implements  UserAdapter.UserHolder.OnUserClickListener {

    private RecyclerView rv;
   private DatabaseReference dbref;
   private UserAdapter adapter;
    private String loginMail;
    ArrayList<User> list = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        Intent i = getIntent();
        loginMail = i.getStringExtra("MAIL");

        rv = findViewById(R.id.rv);
        rv.setLayoutManager(new LinearLayoutManager(UserList.this));
        //db reference
        dbref = FirebaseDatabase.getInstance().getReference("_user_");
        dbref.addListenerForSingleValueEvent(listener);

    }
    // take the data from Firebase and save it to ArrayList
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot dss:snapshot.getChildren()){

                User u = dss.getValue(User.class);
                list.add(u);
            }

            //set the list to adapter
            adapter = new UserAdapter(list, UserList.this);
            rv.setAdapter(adapter);

        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

   // on clicking, take the position to adapter and send the object to class Profile
    @Override
    public void onUserClicked(int position) {
        Intent i = new Intent (UserList.this, Profile.class);
        i.putExtra("User", list.get(position));
        i.putExtra("MailLogin", loginMail);
        startActivity(i);

    }

}
