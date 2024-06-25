package com.classjob.notepad.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.classjob.notepad.R;

public class NewCourseActivity extends AppCompatActivity {

    // creating a variables for our button and edittext.
    private EditText courseDescEdt;
    private ImageView courseBtn;
    String courseDesc;
    // creating a constant string variable for our
    // course name, description and duration.
    public static final String EXTRA_ID = "EXTRA_ID";
    public static final String EXTRA_DESCRIPTION = "EXTRA_NOTES_DESCRIPTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);

        // initializing our variables for each view.
        courseDescEdt = findViewById(R.id.idEdtCourseDescription);
        courseBtn = findViewById(R.id.idBtnSaveCourse);

        // below line is to get intent as we
        // are getting data via an intent.
        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            // if we get id for our data then we are
            // setting values to our edit text fields.
            courseDescEdt.setText(intent.getStringExtra(EXTRA_DESCRIPTION));
        }

        // adding on click listener for our save button.
        courseBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }

    @Override
    public void onBackPressed() {
        saveData();
    }

    private void saveData() {
        // getting text value from edittext and validating if
        // the text fields are empty or not.
        courseDesc = courseDescEdt.getText().toString();
        if (courseDesc.isEmpty()) {
            Toast.makeText(NewCourseActivity.this, R.string.notes_cannot_be_empty, Toast.LENGTH_SHORT).show();
            return;
        }
        // calling a method to save our course.
        saveCourse(courseDesc);
    }

    private void saveCourse(String courseDescription) {
        // inside this method we are passing
        // all the data via an intent.
        Intent data = new Intent();

        // in below line we are passing all our course detail.
        data.putExtra(EXTRA_DESCRIPTION, courseDescription);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            // in below line we are passing our id.
            data.putExtra(EXTRA_ID, id);
        }

        // at last we are setting result as data.
        setResult(RESULT_OK, data);

        // displaying a toast message after adding the data
        finish();
    }
}
