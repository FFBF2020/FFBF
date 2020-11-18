package com.example.ffbf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Profile extends AppCompatActivity {

    EditText fn, sn, mail, type;
    Button back, edit, save;
    DatabaseReference dbref;


    String userMail, firstN, surname, userType;
    Query query;
    ArrayList<User> userList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        fn = findViewById(R.id.et_firstN);
        sn = findViewById(R.id.et_surn);
        mail = findViewById(R.id.et_mail1);
        type = findViewById(R.id.et_userType);
        back = findViewById(R.id.btn_Back);
        edit = findViewById(R.id.btn_edit);
        save = findViewById(R.id.btn_save);

        userMail = getIntent().getStringExtra("EMAIL");

       dbref = FirebaseDatabase.getInstance().getReference("_user_");
       dbref.child("mail").child(userMail).addValueEventListener(new ValueEventListener() {
           @Override
           public void onDataChange(@NonNull DataSnapshot snapshot) {
               firstN = snapshot.child("fn").getValue().toString();
               surname = snapshot.child("sn").getValue().toString();
               userType = snapshot.child("type").getValue().toString();

               fn.setText(firstN);
               sn.setText(surname);
               type.setText(userType);
               mail.setText(userMail);
           }

           @Override
           public void onCancelled(@NonNull DatabaseError error) {

           }
       });






    }

}