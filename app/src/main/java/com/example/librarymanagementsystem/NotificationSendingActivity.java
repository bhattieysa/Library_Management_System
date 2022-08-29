package com.example.librarymanagementsystem;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationSendingActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText bookName,bookSub;
    private ProgressBar progressBar;
    private FcmRetrofitDAO retrofitDAO;
    private String bookN;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification_sending);

        bookName=findViewById(R.id.bookname_notifications_activity);
        bookSub=findViewById(R.id.booksubject_notifications_activity);
        progressBar=findViewById(R.id.progress_notifications_activity);

        retrofitDAO = FcmRetrofitDAO.RETROFIT.create(FcmRetrofitDAO.class);

    }

    @Override
    public void onClick(View v) {
        progressBar.setVisibility(View.VISIBLE);
        sendNotification();
    }

    private void sendNotification() {
        bookN = this.bookName.getText().toString().trim();
        String subject = this.bookSub.getText().toString().trim();
        if (bookN.equals("")){
            this.bookName.setError("Please Enter Book Name");
            progressBar.setVisibility(View.GONE);
            return;
        }
        if (subject.equals("")){
            progressBar.setVisibility(View.GONE);
            return;
        }
        bookN=bookN+" is available now.";
        FirebaseFirestore.getInstance().collection("tokens")
                .limit(1)
                .get().addOnSuccessListener(queryDocumentSnapshots -> {
                    if (queryDocumentSnapshots!=null && queryDocumentSnapshots.getDocuments().size()>0){
                        List<String> registration_ids=new ArrayList<>();
                        for (DocumentSnapshot snapshot:queryDocumentSnapshots.getDocuments())
                            registration_ids.add(snapshot.getString("fcm_token"));
                        FCMModel fcmModel=new FCMModel(registration_ids,new NotificationModel(bookN,subject));
                        Call<Void> voidCall = retrofitDAO.sendNotification(getHeaders(), fcmModel);
                        voidCall.enqueue(new Callback<Void>() {
                            @Override
                            public void onResponse(@NotNull Call<Void> call, @NotNull Response<Void> response) {
                                progressBar.setVisibility(View.GONE);
                                if (response.isSuccessful()) Toast.makeText(getApplicationContext(),
                                        "Notification Sent!",Toast.LENGTH_SHORT)
                                .show();
                            }

                            @Override
                            public void onFailure(@NotNull Call<Void> call, @NotNull Throwable t) {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getApplicationContext()
                                ,"Failed to send notification!",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
                    }
                })
                .addOnFailureListener(e -> progressBar.setVisibility(View.GONE));
    }

    private HashMap<String,String> getHeaders(){
        HashMap<String,String> headers=new HashMap<>();
        headers.put("Content-Type","application/json");
        headers.put("Authorization","key=AAAAA-RHFZo:APA91bGrzYbGdia0nPJUbeV8nwBX2i35kl3eJ0p6CmOOuS_OhhnGqG-eAO6s7aZ50Zlhvr40cG7mL95fTeDxk6zhQKN_HBKB2Ifupz55iMZy208FtxjAbvnJF4bsmAiT6WsfUp6n18UK");
        return headers;
    }
}