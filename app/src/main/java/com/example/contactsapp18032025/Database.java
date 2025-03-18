package com.example.contactsapp18032025;

import android.content.Context;

import androidx.room.Room;
import androidx.room.RoomDatabase;
@androidx.room.Database(entities = {Notes.class}, version = 1)
public abstract class Database extends RoomDatabase {
    private static Database instance;

    public abstract NotesDAO noteDao();

    public static synchronized  Database getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    Database.class,
                    "note_database")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
