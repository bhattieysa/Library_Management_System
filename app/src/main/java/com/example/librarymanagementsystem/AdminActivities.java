package com.example.librarymanagementsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class AdminActivities extends AppCompatActivity implements View.OnClickListener{
TextView button1,button2,button3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_activities);
        button1=findViewById(R.id.regnewbook);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivities.this,RegisterBook.class);
                startActivity(intent);
            }
        });
        button2=findViewById(R.id.issuereturns);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivities.this,IssueAndReturn.class);
                startActivity(intent);
            }
        });
        button3=findViewById(R.id.addevents);
        button3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AdminActivities.this,Events.class);
                startActivity(intent);
            }
        });

    }

    @Override
    public void onClick(View v) {
        if (v.getId()==R.id.send_notification){
            Intent intent=new Intent(this,NotificationSendingActivity.class);
            startActivity(intent);
        }
    }
}