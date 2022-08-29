package com.example.librarymanagementsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.api.LogDescriptor;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.messaging.FirebaseMessaging;

import java.util.HashMap;
import java.util.Map;

public class Signup extends AppCompatActivity {
    EditText name, email, password, mobile;
    TextView signp;
    String emailstring, passwordstring, namestring, phonestring;
    FirebaseAuth mAuth;
    ProgressBar progressBar;
    FirebaseFirestore firebaseFirestore;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        name = findViewById(R.id.fullname);
        email = findViewById(R.id.signupemail);
        password = findViewById(R.id.code);
        mobile =findViewById(R.id.mobile);
        signp = findViewById(R.id.signupbtn);
        progressBar = findViewById(R.id.progressbr);
        mAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

       // if (mAuth.getCurrentUser() != null){
       //     Intent intent = new Intent(Signup.this, UserActivities.class);
       //     startActivity(intent);
       // }



        signp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailstring = email.getText().toString().trim();
                passwordstring = password.getText().toString().trim();
                namestring = name.getText().toString();
                phonestring = mobile.getText().toString();

                if (TextUtils.isEmpty(emailstring)){
                    email.setError("Email is Required.");
                    return;
                }

                if (TextUtils.isEmpty(phonestring)){
                    email.setError("Phone is Required.");
                    return;
                }

                if (TextUtils.isEmpty(namestring)){
                    email.setError("Name is Required.");
                    return;
                }

                if (TextUtils.isEmpty(passwordstring)){
                    password.setError("Password is Required.");
                    return;
                }

                if (passwordstring.length() <6 ){
                    password.setError("Password Must Be Atleast 8 Characters");
                    return;
                }
                progressBar.setVisibility(View.VISIBLE);

                //Register the user in firebase

                mAuth.createUserWithEmailAndPassword(emailstring,passwordstring).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(Signup.this, "SignUp Successfull.", Toast.LENGTH_SHORT).show();
                            userID = mAuth.getCurrentUser().getUid();
                            DocumentReference documentReference = firebaseFirestore
                                    .collection("users").document(userID);
                            Map<String,Object> user = new HashMap<>();
                            user.put("Full Name",namestring);
                            user.put("E-mail",emailstring);
                            user.put("Mobile",phonestring);
                            documentReference.set(user).addOnSuccessListener(aVoid -> Log.d("TAG", "onSuccess:user profile is created for "+userID));
                            //getting fcm token
                            FirebaseMessaging.getInstance().getToken().addOnSuccessListener(s -> {
                                if (s==null || s.equals("")) return;
                                Map<String,Object> tokenMap=new HashMap<>(1);
                                tokenMap.put("fcm_token",s);
                                FirebaseFirestore.getInstance().collection("tokens")
                                        .add(tokenMap).addOnSuccessListener(documentReference1 -> {
                                            Intent intent = new Intent(Signup.this, UserActivities.class);
                                            startActivity(intent);
                                        });
                            });


                        }else {
                            Toast.makeText(Signup.this, "Error !" + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


            }


        });


    }

}