package com.example.librarymanagementsystem;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;

public class adapter extends FirestoreRecyclerAdapter<Recyclermodel,adapter.myviewholder>
{

    public adapter(@NonNull FirestoreRecyclerOptions<Recyclermodel> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull myviewholder holder, int position, @NonNull Recyclermodel model) {
        holder.Bookname.setText(model.getBookname());
        holder.Bookid.setText(model.getBookid());
        holder.Booksubject.setText(model.getBookSubject());
        Log.d("taggg", "onBindViewHolder: "+model.getBookImage());
        Glide.with(holder.itemView.getContext())
                .load(model.getBookImage())
                .into(holder.img);
    }

    @NonNull
    @Override
    public myviewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.bookcard,
                parent,false);
        return new myviewholder(view);
    }

    static class myviewholder extends RecyclerView.ViewHolder{
        TextView Bookname;
        TextView Bookid;
        TextView Booksubject;
        ImageView img;
        public myviewholder(@NonNull View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.booksimageview1);
            Bookname = itemView.findViewById(R.id.booksnames);
            Bookid = itemView.findViewById(R.id.booksids);
            Booksubject = itemView.findViewById(R.id.bookssubjets);
        }
    }
}
