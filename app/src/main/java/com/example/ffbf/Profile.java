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

   private EditText fn, sn, mail, type;
    private TextView userMailLog;
    private Button back, update;
   private DatabaseReference dbref;
   private String loginMail, firstName, surname;



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
          //get the object, sent from previous activity
        User user = getIntent().getParcelableExtra("User");
        //get the email of the current user
        loginMail = getIntent().getStringExtra("MailLogin");
        //update button to be invisible
        update.setVisibility(View.INVISIBLE);
        //database reference
        dbref = FirebaseDatabase.getInstance().getReference("_user_");

        //display chosen user's details
        fn.setText(user.getFn());
        sn.setText(user.getSn());
        mail.setText(user.getMail());
        type.setText(user.getType());
        userMailLog.setText(loginMail);


        // if the user wants to access his own profile
        if (user.getMail().equals(loginMail)) {
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
                dbref.child("_user_").child(loginMail).child("sn").setValue(sn.getText().toString());
                return true;
            }
            else {
                return false;
            }
        }

        private boolean isFirstNameChanged () {
        if(!firstName.equals(fn.getText().toString())){
         dbref.child("_user").child(loginMail).child("fn").setValue(fn.getText().toString());
         return true;
        }
        else {
            return false;
        }
        }


}