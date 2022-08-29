package com.example.librarymanagementsystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class UserActivities extends AppCompatActivity {
    TextView button1,button2,button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_activities);
        button1=findViewById(R.id.searchbooks);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(UserActivities.this,SearcgBooks.class);
                startActivity(intent);
            }
        });
        button2=findViewById(R.id.lendbooks);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivities.this,LendBooks.class);
                startActivity(intent);
            }
        });
        button3=findViewById(R.id.reservebooks);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivities.this,ReserveBooks.class);
                startActivity(intent);
            }
        });
    }
}