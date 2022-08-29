package com.example.librarymanagementsystem;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

import java.io.FileNotFoundException;
import java.io.InputStream;

public class RegisterBook extends AppCompatActivity {
    EditText bookname,bookid,booksubject;
    ImageView bookimage;
    TextView regbook;
    Uri filepath;
    Bitmap bitmap;
    String Bookname;
    String Bookid;
    String Booksubject;
FirebaseFirestore fstore;
ProgressBar progressBar;
private static int RESULT_LOAD_IMG;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_book);
        bookid=findViewById(R.id.bookid);
        bookname=findViewById(R.id.bookname_notifications_activity);
        booksubject=findViewById(R.id.booksubject);
        bookimage=findViewById(R.id.bookimage);
        progressBar = findViewById(R.id.progressregister);

        regbook=findViewById(R.id.regbook);
        fstore=FirebaseFirestore.getInstance();
        regbook.setOnClickListener(v -> {

            Bookid = bookid.getText().toString();
            Bookname = bookname.getText().toString();
            Booksubject = booksubject.getText().toString();

            if (TextUtils.isEmpty(Bookid)){
                bookid.setError("Email is Required.");
                return;
            }

            if (TextUtils.isEmpty(Bookname)){
                bookname.setError("Phone is Required.");
                return;
            }

            if (TextUtils.isEmpty(Booksubject)){
                booksubject.setError("Name is Required.");
                return;
            }

            regbook.setVisibility(View.INVISIBLE);
            progressBar.setVisibility(View.VISIBLE);

            CollectionReference collectionReference = fstore.collection("Books");

            InputStream imageStream=null;
            try {
                imageStream = getContentResolver()
                        .openInputStream(filepath);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            if (imageStream==null) return;

            //uploading to storage
            FirebaseStorage.getInstance().getReference("Books")
                    .child(filepath.getLastPathSegment())
                    .putStream(imageStream).addOnSuccessListener(taskSnapshot -> taskSnapshot.getStorage().getDownloadUrl().addOnSuccessListener(uri -> {
                        //got download url and now adding data to firestore
                        Recyclermodel book = new Recyclermodel(
                                Bookid,Bookname,Booksubject,uri.toString());
                        collectionReference.add(book)
                                .addOnSuccessListener(documentReference -> {
                                    Toast.makeText(RegisterBook.this, "Book Registered", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(RegisterBook.this,AdminActivities.class);
                                    startActivity(intent);
                                })
                                .addOnFailureListener(e -> {
                                    Log.e("TAG", "onFailure: failure" + e.getMessage() );
                                    Toast.makeText(RegisterBook.this, e.getMessage(), Toast.LENGTH_LONG).show();
                                });
                    }));
                });//listener

        }

        //pickImageFromGallery
        public void upload(View view) {
            Intent photoPickerIntent = new Intent(Intent.ACTION_PICK);
            photoPickerIntent.setType("image/*");
            startActivityForResult(photoPickerIntent, RESULT_LOAD_IMG);
        }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            try {
                filepath = data.getData();
                final InputStream imageStream = getContentResolver()
                        .openInputStream(filepath);
                 bitmap = BitmapFactory.decodeStream(imageStream);
                bookimage.setImageBitmap(bitmap);

            } catch (FileNotFoundException e) {
                e.printStackTrace();
                Toast.makeText(RegisterBook.this, "Something went wrong", Toast.LENGTH_LONG).show();
            }

        }else {
            Toast.makeText(RegisterBook.this, "You haven't picked Image",Toast.LENGTH_LONG).show();
        }
    }
}
