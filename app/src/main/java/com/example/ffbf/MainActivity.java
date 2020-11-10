package com.example.ffbf;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    EditText  mail, password;
    Button login, register;
    String email;
    DatabaseReference  dbref;
    ArrayList<User> uList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mail = findViewById(R.id.et_mail);
        password = findViewById(R.id.et_psword);
        login = findViewById(R.id.btn_login);
        register = findViewById(R.id.btn_register);

        //2. Attach  database reference object to the node to read from
        dbref = FirebaseDatabase.getInstance().getReference("_user_");
        //4. Attach the listener
        dbref.addListenerForSingleValueEvent(listener);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                email = mail.getText().toString();


                for (int i = 0; i <= uList.size(); i++) {

                    if (uList.get(i).getMail().equals(email) && uList.get(i).getPassword().equals(password.getText().toString())) {


                        Intent intent = new Intent(MainActivity.this, HomeRestList.class);
                        intent.putExtra("MAIL", email);

                        startActivity(intent);
                    }

                }
            }


        });


           register.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   Intent i = new Intent(MainActivity.this, Register.class);
                   startActivity(i);
               }
           });

    }
    // 3. Value Event Listener Object.
    ValueEventListener listener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            for(DataSnapshot dss: snapshot.getChildren())
            {

                User u = dss.getValue(User.class);
                uList.add(u);
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };
}