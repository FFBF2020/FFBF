package com.example.ffbf;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class Profile extends AppCompatActivity {

    EditText fn, sn, mail, type;
    TextView userMailLog;
    Button back, update;
    DatabaseReference dbref;
    String loginMail, firstName, surname;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);


        fn = findViewById(R.id.et_firstN);
        sn = findViewById(R.id.et_surn);
        mail = findViewById(R.id.et_mail1);
        type = findViewById(R.id.et_userType);
        back = findViewById(R.id.btn_back);
        userMailLog = findViewById(R.id.tv_mailLogin);
        update = findViewById(R.id.btn_updateProfile);

        User user = getIntent().getParcelableExtra("User");
        loginMail = getIntent().getStringExtra("MailLogin");
        update.setVisibility(View.INVISIBLE);
        dbref = FirebaseDatabase.getInstance().getReference("_user_");

        //display chosen user's details
        fn.setText(user.getFn());
        sn.setText(user.getSn());
        mail.setText(user.getMail());
        type.setText(user.getType());
        userMailLog.setText(loginMail);
        String firstName = user.getFn();
        String surname = user.getSn();

        // if the user wants to access his own profile
        if (user.getMail() == loginMail) {
            //make update button visible
            // make edit texts editable
            update.setVisibility(View.VISIBLE);

           update.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   update();
               }
           });



        }
    }

        public void update () {
            if (isFirstNameChanged() || isSurnameChanged()) {
                Toast.makeText(Profile.this, "The profile is updated ", Toast.LENGTH_LONG);
        }
        else {
                Toast.makeText(Profile.this, "Data is the same and not be updated ", Toast.LENGTH_LONG);
        }
        }

        private boolean isSurnameChanged () {
            if (!surname.equals(fn.getText().toString())){
                dbref.child("_user_").child(loginMail).child(surname).child("sn").setValue(sn.getText().toString());
                return true;
            }
            else {
                return false;
            }
        }

        private boolean isFirstNameChanged () {
        if(!firstName.equals(fn.getText().toString())){
         dbref.child("_user").child(loginMail).child(firstName).child("fn").setValue(fn.getText().toString());
         return true;
        }
        else {
            return false;
        }
        }


}