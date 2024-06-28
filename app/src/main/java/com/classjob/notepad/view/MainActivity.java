package com.classjob.notepad.view;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//import com.classjob.notepad.databinding.ActivityMainBinding;
import com.bipulsoft.notepad.databinding.ActivityMainBinding;
import com.classjob.notepad.model.NotesModel;
import com.classjob.notepad.NotesRVAdapter;
import com.classjob.notepad.model.ViewModal;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding mBinding;

    // creating a variables for our recycler view.
    private static final int ADD_NOTES_REQUEST = 1;
    private static final int EDIT_NOTES_REQUEST = 2;
    private ViewModal viewmodal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mBinding = ActivityMainBinding.inflate(getLayoutInflater());

        setContentView(mBinding.getRoot());
        // adding on click listener for floating action button.
        mBinding.idFABAdd.setOnClickListener(v -> {
            // starting a new activity for adding a new course
            // and passing a constant value in it.
            Intent intent = new Intent(MainActivity.this, NewNotesActivity.class);
            startActivityForResult(intent, ADD_NOTES_REQUEST);
        });

        // setting layout manager to our adapter class.
        mBinding.idRVNotes.setLayoutManager(new LinearLayoutManager(this));
        mBinding.idRVNotes.setHasFixedSize(true);

        // initializing adapter for recycler view.
        final NotesRVAdapter adapter = new NotesRVAdapter();

        // setting adapter class for recycler view.
        mBinding.idRVNotes.setAdapter(adapter);

        // passing a data from view modal.
        viewmodal = ViewModelProviders.of(this).get(ViewModal.class);

        // below line is use to get all the courses from view modal.
        viewmodal.getAllCourses().observe(this, models -> {
            // when the data is changed in our models we are
            // adding that list to our adapter class.
            adapter.submitList(models);
        });
        // below method is use to add swipe to delete method for item of recycler view.
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                // on recycler view item swiped then we are deleting the item of our recycler view.
                viewmodal.delete(adapter.getCourseAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Notes deleted", Toast.LENGTH_SHORT).show();
            }
        }).
                // below line is use to attach this to recycler view.
                        attachToRecyclerView( mBinding.idRVNotes);
        // below line is use to set item click listener for our item of recycler view.
        adapter.setOnItemClickListener(new NotesRVAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(NotesModel model) {
                // after clicking on item of recycler view
                // we are opening a new activity and passing
                // a data to our activity.
                Intent intent = new Intent(MainActivity.this, NewNotesActivity.class);
                intent.putExtra(NewNotesActivity.EXTRA_ID, model.getId());

                intent.putExtra(NewNotesActivity.EXTRA_DESCRIPTION, model.getCourseDescription());

                // below line is to start a new activity and
                // adding a edit course constant.
                startActivityForResult(intent, EDIT_NOTES_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ADD_NOTES_REQUEST && resultCode == RESULT_OK) {

            String courseDescription = data.getStringExtra(NewNotesActivity.EXTRA_DESCRIPTION);

            NotesModel model = new NotesModel(courseDescription);
            viewmodal.insert(model);
            Toast.makeText(this, "Notes saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_NOTES_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(NewNotesActivity.EXTRA_ID, -1);
            if (id == -1) {
                Toast.makeText(this, "Notes can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String courseDesc = data.getStringExtra(NewNotesActivity.EXTRA_DESCRIPTION);

            NotesModel model = new NotesModel(courseDesc);
            model.setId(id);
            viewmodal.update(model);
            Toast.makeText(this, "Notes updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Notes not saved", Toast.LENGTH_SHORT).show();
        }
    }
}
