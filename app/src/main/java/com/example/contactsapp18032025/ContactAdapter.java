package com.example.contactsapp18032025;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.MyViewHolder> {
    private Context mContext;
    private List<Notes> noteList;  // Store notes in a list
    private NotesDAO noteDao;

    public ContactAdapter(Context mContext) {
        this.mContext = mContext;
        Database database = Database.getInstance(mContext);
        this.noteDao = database.noteDao();
        this.noteList = noteDao.getAllNotes(); // Load data once
    }
    @NonNull
    @Override
    public ContactAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.contact_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ContactAdapter.MyViewHolder holder, int position) {
        Notes note = noteList.get(position);
        holder.et1.setText(note.getFirstName());
        holder.et2.setText(note.getLastName());
        holder.et3.setText(note.getAddress());
        holder.et4.setText(note.getPhone());
    }

    @Override
    public int getItemCount() {
        return noteDao.getAllNotes().size();
    }

    public void updateData(List<Notes> newNotes) {
        this.noteList = newNotes;
        notifyDataSetChanged(); // Refresh RecyclerView
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
