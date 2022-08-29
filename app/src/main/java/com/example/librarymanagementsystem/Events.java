package com.example.librarymanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class Events extends AppCompatActivity {
EditText eventname,eventdate,eventdesc;
TextView addevent;
String Eventname, Eventdesc, Eventdate;
FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        db = FirebaseFirestore.getInstance();
        eventdate=findViewById(R.id.eventdate);
        eventdesc=findViewById(R.id.eventdesc);
        eventname=findViewById(R.id.eventname);
        addevent=findViewById(R.id.addevent);

    }


    public void saveData(View view) {

        Eventdate = eventdate.getText().toString();
        Eventdesc = eventdesc.getText().toString();
        Eventname = eventname.getText().toString();


        if (TextUtils.isEmpty(Eventdate)){
            eventdate.setError("Date is Required.");
            return;
        }

        if (TextUtils.isEmpty(Eventdesc)){
            eventdesc.setError("Must Have Description.");
            return;
        }

        if (TextUtils.isEmpty(Eventname)){
            eventname.setError("Name is Required.");
            return;
        }

        CollectionReference collectionReference = db.collection("Events");



        Eventsmodel eventsmodel = new Eventsmodel(
                Eventdate, Eventname,Eventdesc
        );

        collectionReference.add(eventsmodel)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast.makeText(Events.this, "Event Added", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Events.this,AdminActivities.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Events.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                });
    }
}