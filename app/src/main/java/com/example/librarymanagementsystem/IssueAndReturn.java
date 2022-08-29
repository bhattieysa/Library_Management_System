package com.example.librarymanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class IssueAndReturn extends AppCompatActivity {
EditText editTextbookname, editTextdateissue, editTextdatereturn, editTextbookid;
TextView textViewbutton;
FirebaseFirestore firestore1;
ProgressBar progressBar1;
String textbookname,datereturn,dateissue,textbookid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_issue_and_return);
        editTextbookname = findViewById(R.id.booknaam);
        editTextdateissue = findViewById(R.id.dateissue);
        editTextdatereturn = findViewById(R.id.returndate);
        editTextbookid = findViewById(R.id.bookid);
        firestore1=FirebaseFirestore.getInstance();
        progressBar1 = findViewById(R.id.returnprogress);
        textViewbutton = findViewById(R.id.returnbook);
        textViewbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textbookname = editTextbookname.getText().toString();
                dateissue = editTextdateissue.getText().toString();
                datereturn = editTextdatereturn.getText().toString();
                textbookid = editTextbookid.getText().toString();

                if (TextUtils.isEmpty(textbookname)) {
                    editTextbookname.setError("Book NAme is Required.");
                    return;
                }

                if (TextUtils.isEmpty(textbookid)) {
                    editTextbookid.setError("BookID is Required.");
                    return;
                }

                if (TextUtils.isEmpty(dateissue)) {
                    editTextdateissue.setError("E-mail is Required.");
                    return;
                }

                if (TextUtils.isEmpty(datereturn)) {
                    editTextdatereturn.setError("Book Subjet is Required.");
                    return;
                }

                textViewbutton.setVisibility(View.INVISIBLE);
                progressBar1.setVisibility(View.VISIBLE);

                CollectionReference collectionReference = firestore1.collection("Issue and Return Books");

                Issueandreturnmodel issueandreturnmodel = new Issueandreturnmodel(
                        textbookname, textbookid, datereturn, dateissue


                );

                collectionReference.add(issueandreturnmodel)
                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                            @Override
                            public void onSuccess(DocumentReference documentReference) {
                                Toast.makeText(IssueAndReturn.this, "sucessful", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(IssueAndReturn.this,AdminActivities.class);
                                startActivity(intent);
                            }
                        })
                        .addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(IssueAndReturn.this, e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        });
            }

        });

    }
}