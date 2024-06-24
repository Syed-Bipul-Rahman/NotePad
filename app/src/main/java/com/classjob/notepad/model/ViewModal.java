package com.classjob.notepad.model;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.classjob.notepad.NotesRepository;

import java.util.List;

public class ViewModal extends AndroidViewModel {

    // creating a new variable for course repository.
    private NotesRepository repository;

    // below line is to create a variable for live
    // data where all the courses are present.
    private LiveData<List<NotesModel>> allCourses;

    // constructor for our view modal.
    public ViewModal(@NonNull Application application) {
        super(application);
        repository = new NotesRepository(application);
        allCourses = repository.getAllCourses();
    }

    // below method is use to insert the data to our repository.
    public void insert(NotesModel model) {
        repository.insert(model);
    }

    // below line is to update data in our repository.
    public void update(NotesModel model) {
        repository.update(model);
    }

    // below line is to delete the data in our repository.
    public void delete(NotesModel model) {
        repository.delete(model);
    }

    // below method is to delete all the courses in our list.
    public void deleteAllCourses() {
        repository.deleteAllCourses();
    }

    // below method is to get all the courses in our list.
    public LiveData<List<NotesModel>> getAllCourses() {
        return allCourses;
    }
}
