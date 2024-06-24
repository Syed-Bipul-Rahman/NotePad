package com.classjob.notepad;

import androidx.lifecycle.LiveData;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.classjob.notepad.model.NotesModel;

import java.util.List;

// Adding annotation
// to our Dao class
@androidx.room.Dao
public interface Dao {

    // below method is use to
    // add data to database.
    @Insert
    void insert(NotesModel model);

    // below method is use to update
    // the data in our database.
    @Update
    void update(NotesModel model);

    // below line is use to delete a
    // specific course in our database.
    @Delete
    void delete(NotesModel model);

    // on below line we are making query to
    // delete all courses from our database.
    @Query("DELETE FROM users_notes")
    void deleteAllCourses();

    // below line is to read all the courses from our database.
    // in this we are ordering our courses in ascending order
    // with our course name.
    @Query("SELECT * FROM users_notes ORDER BY id DESC")
    LiveData<List<NotesModel>> getAllCourses();
}
