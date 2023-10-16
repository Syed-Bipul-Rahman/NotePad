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
    public static final String EXTRA_ID = "com.gtappdevelopers.gfgroomdatabase.EXTRA_ID";
    public static final String EXTRA_DESCRIPTION = "com.gtappdevelopers.gfgroomdatabase.EXTRA_COURSE_DESCRIPTION";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_course);
//
//        Toolbar customAppBar = findViewById(R.layout.custom_app_bar);
//
//        // Set the custom app bar as the support action bar
//        setSupportActionBar(customAppBar);


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
                // getting text value from edittext and validating if
                // the text fields are empty or not.
                courseDesc = courseDescEdt.getText().toString();
                if (courseDesc.isEmpty()) {
                    Toast.makeText(NewCourseActivity.this, "Please enter the valid course details.", Toast.LENGTH_SHORT).show();
                    return;
                }
                // calling a method to save our course.
                saveCourse(courseDesc);

                //hiding keyboard
//                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
//                imm.hideSoftInputFromWindow(courseDescEdt.getWindowToken(), 0);
                finish();
            }
        });
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
        //alertdialog

        // Toast.makeText(this, "Course has been saved to Room Database. ", Toast.LENGTH_SHORT).show();
    }


}
