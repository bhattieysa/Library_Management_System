package com.example.librarymanagementsystem;

import androidx.annotation.NonNull;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class ReserveBooks extends Signup {
    EditText name, bookname, bookid, email, subject;
    TextView reserve;
    String Fullname, Bookname, Bookid, Email, Subject;
    FirebaseFirestore firebaseFirestore;
    ProgressBar progressDialog;
    String userId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve_books);
        name = findViewById(R.id.resname);
        bookname = findViewById(R.id.bookname_notifications_activity);
        bookid = findViewById(R.id.bookid);
        email = findViewById(R.id.resmail);
        progressDialog = findViewById(R.id.progressreserve);
        subject = findViewById(R.id.booksubject);
        firebaseFirestore = FirebaseFirestore.getInstance();
        reserve = findViewById(R.id.resbook);
        reserve.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fullname = name.getText().toString().trim();
                Bookname = bookname.getText().toString();
                Bookid = bookid.getText().toString();
                Email = email.getText().toString();
                Subject = subject.getText().toString();

                if (TextUtils.isEmpty(Bookname)) {
                    bookname.setError("Book NAme is Required.");
                    return;
                }

                if (TextUtils.isEmpty(Bookid)) {
                    bookid.setError("BookID is Required.");
                    return;
                }

                if (TextUtils.isEmpty(Email)) {
                    email.setError("E-mail is Required.");
                    return;
                }

                if (TextUtils.isEmpty(Subject)) {
                    subject.setError("Book Subjet is Required.");
                    return;
                }

                if (TextUtils.isEmpty(Fullname)) {
                    name.setError("Full Name is Required.");
                    return;
                }

                reserve.setVisibility(View.INVISIBLE);
                progressDialog.setVisibility(View.VISIBLE);


                CollectionReference collectionReference = firebaseFirestore.collection("Reserved Books");

                BookReserveModel reserveModel = new BookReserveModel(
                        Fullname, Email, Bookname, Bookid, Subject


                );

                collectionReference.add(reserveModel)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(ReserveBooks.this, "Book Reserved", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(ReserveBooks.this,UserActivities.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(ReserveBooks.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }


        });

    }
}

