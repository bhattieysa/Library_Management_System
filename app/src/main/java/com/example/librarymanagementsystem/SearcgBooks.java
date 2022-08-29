package com.example.librarymanagementsystem;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.FirebaseFirestore;

import java.io.ByteArrayOutputStream;

public class SearcgBooks extends AppCompatActivity {
    private RecyclerView recview;
    private adapter adapter;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searcg_books);


        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));


        //FireStoreRecyclerOptions
        FirestoreRecyclerOptions<Recyclermodel> options = new FirestoreRecyclerOptions
                .Builder<Recyclermodel>()
                .setQuery(FirebaseFirestore.getInstance().collection("Books"),
                        Recyclermodel.class)
                .build();


        //adapter
        adapter = new adapter(options);
        recview.setAdapter(adapter);
    }



    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }


}