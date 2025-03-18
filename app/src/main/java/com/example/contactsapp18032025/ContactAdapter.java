package com.example.contactsapp18032025;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    Context mContext;
    Database database = Database.getInstance(mContext);
    NotesDAO noteDao = database.noteDao();
    @NonNull
    @Override
    public ContactAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater.from(mContext).inflate(R.layout.contact_item, viewGroup, false);

        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.MyViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return noteDao.getAllNotes().size();
    }

    public ContactAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView et1, et2, et3, et4;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            this.et1 = itemView.findViewById(R.id.textView);
            this.et2 = itemView.findViewById(R.id.textView2);
            this.et3 = itemView.findViewById(R.id.textView3);
            this.et4 = itemView.findViewById(R.id.textView4);
        }
    }
}
