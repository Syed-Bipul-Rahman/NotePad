package com.classjob.notepad.model;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

// below line is for setting table name.
@Entity(tableName = "users_notes")
public class CourseModal {

    // below line is to auto increment
    // id for each course.
    @PrimaryKey(autoGenerate = true)

    // variable for our id.
    private int id;

    // below line is a variable
    // for course name.


    // below line is use for
    // course description.
    private String courseDescription;

    public CourseModal(String courseDescription) {
        this.courseDescription = courseDescription;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourseDescription() {
        return courseDescription;
    }

    public void setCourseDescription(String courseDescription) {
        this.courseDescription = courseDescription;
    }
}
