package com.example.contactsapp18032025;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NotesDAO {
    @Insert
    void insert(Notes note);

    @Delete
    void delete(Notes note);

    @Query("SELECT * FROM notes")
    List<Notes> getAllNotes();

    @Query("SELECT * FROM notes WHERE id = :id")
    Notes getNoteByID(int id);
}
