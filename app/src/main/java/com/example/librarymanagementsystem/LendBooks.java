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

public class LendBooks extends Signup {
EditText fullname,id,lenddays,lendto;
TextView lendbook;
String Bookname,Bookid,LendingDays,LendingFrom;
ProgressBar proobar;
FirebaseFirestore firstore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lend_books);
        fullname = findViewById(R.id.lendname);
        id = findViewById(R.id.lendid);
        lenddays = findViewById(R.id.lendingdays);
        lendto = findViewById(R.id.lendingto);
        firstore = FirebaseFirestore.getInstance();
        proobar = findViewById(R.id.progresslend);
        lendbook = findViewById(R.id.lendbookbtn);
        lendbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bookname = fullname.getText().toString();
                Bookid = id.getText().toString();
                LendingDays = lenddays.getText().toString();
                LendingFrom = lendto.getText().toString();

                if (TextUtils.isEmpty(Bookname)){
                    fullname.setError("Full Name is Required.");
                    return;
                }

                if (TextUtils.isEmpty(Bookid)){
                    id.setError("Book ID is Required.");
                    return;
                }

                if (TextUtils.isEmpty(LendingDays)){
                    lenddays.setError("Lending Days Should be mentioned");
                    return;
                }

                if (TextUtils.isEmpty(LendingFrom)){
                    lendto.setError("Enter name of the person");
                    return;
                }

                proobar.setVisibility(View.VISIBLE);
                lendbook.setVisibility(View.INVISIBLE);


                CollectionReference collectionReference = firstore.collection("Lend Books");

                BookLendModel bookLendModel = new BookLendModel(
                        Bookname,Bookid,LendingDays,LendingFrom


                );


                collectionReference.add(bookLendModel)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(LendBooks.this, "Request Submitted", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(LendBooks.this,UserActivities.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(LendBooks.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });
    }
}





