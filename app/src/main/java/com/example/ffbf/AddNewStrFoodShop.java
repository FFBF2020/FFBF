package com.example.ffbf;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;


import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Scroller;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class AddNewStrFoodShop extends AppCompatActivity {

    ImageView image;
    EditText stallName, stallAddr1, stallAddr2, descr;
    CheckBox veg;
    Button upload;
    Uri image_path;
    StorageReference sref;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_str_food_shop);

        image = findViewById(R.id.iv_image);
        stallName = findViewById(R.id.et_stallName);
        stallAddr1 = findViewById(R.id.et_stallAddress1);
        stallAddr2 = findViewById(R.id.et_stallAddress2);
        veg = findViewById(R.id.cb_veg);
        upload = findViewById(R.id.btn_upload);
        descr = findViewById(R.id.etml_descr);
        sref = FirebaseStorage.getInstance().getReference("images");

        // Scroller for EditText multiple lines with fixed minimum and maximum lines
        descr.setScroller(new Scroller(getApplicationContext()));
        descr.setMinLines(2);
        descr.setMaxLines(6);

        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent();
                i.setType("image/*");
                i.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(i, 100);
            }
        });

        upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                final DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("_stalls_");
               final String id = dbref.push().getKey();

                final StorageReference reference = sref.child(id + "."+ getExtention(image_path));
                reference.putFile(image_path).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                            @Override
                            public void onSuccess(Uri uri) {

                                String url = uri.toString();
                                String type;
                                 // Check if the Checkbox is selected or not
                                if(veg.isChecked()) {
                                   type = "Vegitarian";
                                }
                                else {
                                    type = "No-vegitarian";
                                }

                                if (!(TextUtils.isEmpty(stallName.getText().toString()) &&
                                        TextUtils.isEmpty(stallAddr1.getText().toString()) && TextUtils.isEmpty(stallAddr2.getText().toString()) && TextUtils.isEmpty(descr.getText().toString()))) {

                                    Stalls stalls = new Stalls(stallName.getText().toString(), stallAddr1.getText().toString(), stallAddr2.getText().toString(),
                                            descr.getText().toString(), type, url);

                                    dbref.child(id).setValue(stalls);

                                }
                                else {
                                    Toast.makeText(AddNewStrFoodShop.this, "Please fill all fields", Toast.LENGTH_LONG).show();
                                }

                            }
                        }).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                reference.delete();
                                Toast.makeText(AddNewStrFoodShop.this, "Lost internet connection", Toast.LENGTH_LONG).show();

                            }
                        });

                    }
                });

            }
        });



    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(resultCode == 100 && resultCode == RESULT_OK && data.getData() != null) {

            Picasso.get().load(data.getData()).fit().into(image);
            image_path = data.getData();
        }
    }

    private String getExtention(Uri path) {

        ContentResolver resolver = getContentResolver();
        MimeTypeMap map = MimeTypeMap.getSingleton();
        return map.getExtensionFromMimeType(resolver.getType(path));

    }
}