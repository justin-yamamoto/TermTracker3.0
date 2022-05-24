package android.justinyamamoto.termtracker30.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.justinyamamoto.termtracker30.Database.Repository;
import android.justinyamamoto.termtracker30.Entities.Course;
import android.justinyamamoto.termtracker30.R;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CourseEdit extends AppCompatActivity {

    int courseId;
    String courseName;
    String courseStartDate;
    String courseEndDate;
    String courseStatus;
    String instructorName;
    String phone;
    String email;
    String notes;
    int termId;

    EditText editCourseNameEt;
    EditText editCourseStartEt;
    EditText editCourseEndEt;
    RadioGroup editCourseRadioGroup;
    RadioButton editCourserBtnInProgress;
    RadioButton editCourserBtnCompleted;
    RadioButton editCourserBtnDropped;
    RadioButton editCourserBtnPlanned;
    EditText editCourseInstructorEt;
    EditText editCoursePhoneEt;
    EditText editCourseEmailEt;
    EditText editCourseNotesEt;

    Repository r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_edit);
        //Menu back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        r=new Repository(getApplication());

        courseName = getIntent().getStringExtra("courseName");
        editCourseNameEt = findViewById(R.id.editCourseNameEt);
        editCourseNameEt.setText(courseName);

        courseStartDate = getIntent().getStringExtra("courseStartDate");
        editCourseStartEt = findViewById(R.id.editCourseStartEt);
        editCourseStartEt.setText(courseStartDate);

        courseEndDate = getIntent().getStringExtra("courseEndDate");
        editCourseEndEt = findViewById(R.id.editCourseEndEt);
        editCourseEndEt.setText(courseEndDate);

        courseStatus = getIntent().getStringExtra("courseStatus");
        editCourserBtnInProgress = findViewById(R.id.editCourserBtnInProgress);
        editCourserBtnCompleted = findViewById(R.id.editCourserBtnCompleted);
        editCourserBtnDropped = findViewById(R.id.editCourserBtnDropped);
        editCourserBtnPlanned = findViewById(R.id.editCourserBtnPlanned);

        if (courseStatus.equals("In Progress")){
            editCourserBtnInProgress.setChecked(true);
        }
        if (courseStatus.equals("Completed")){
            editCourserBtnCompleted.setChecked(true);
        }
        if (courseStatus.equals("Dropped")){
            editCourserBtnDropped.setChecked(true);
        }
        if (courseStatus.equals("Planned To Take")){
            editCourserBtnPlanned.setChecked(true);
        }


        instructorName = getIntent().getStringExtra("instructorName");
        editCourseInstructorEt = findViewById(R.id.editCourseInstructorEt);
        editCourseInstructorEt.setText(instructorName);

        phone = getIntent().getStringExtra("phone");
        editCoursePhoneEt = findViewById(R.id.editCoursePhoneEt);
        editCoursePhoneEt.setText(phone);

        email = getIntent().getStringExtra("email");
        editCourseEmailEt = findViewById(R.id.editCourseEmailEt);
        editCourseEmailEt.setText(email);

        notes = getIntent().getStringExtra("notes");
        editCourseNotesEt = findViewById(R.id.editCourseNotesEt);
        editCourseNotesEt.setText(notes);

        termId = getIntent().getIntExtra("termId",-1);



    }

    /**Return to previous activity. */
    public boolean onOptionsItemSelected( MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    public void checkRadio(View view) {
    }

    public void saveEditCourse(View view) {
        courseId = getIntent().getIntExtra("courseId",-1);
        courseName = editCourseNameEt.getText().toString();
        courseStartDate = editCourseStartEt.getText().toString();
        courseEndDate = editCourseEndEt.getText().toString();
        if (editCourserBtnInProgress.isChecked()){
            courseStatus = editCourserBtnInProgress.getText().toString();
        }
        if (editCourserBtnCompleted.isChecked()){
            courseStatus = editCourserBtnCompleted.getText().toString();
        }
        if (editCourserBtnDropped.isChecked()){
            courseStatus = editCourserBtnDropped.getText().toString();
        }
        if (editCourserBtnPlanned.isChecked()){
            courseStatus = editCourserBtnPlanned.getText().toString();
        }
        instructorName = editCourseInstructorEt.getText().toString();
        phone = editCoursePhoneEt.getText().toString();
        email = editCourseEmailEt.getText().toString();
        notes = editCourseNotesEt.getText().toString();
        termId = getIntent().getIntExtra("termId",-1);

       // Course course = new Course(courseId,courseName,courseStartDate,courseEndDate,courseStatus,instructorName,phone,email,notes,termId);
       // r.update(course);
        r.updateCourseById(courseId,courseName,courseStartDate,courseEndDate,courseStatus,instructorName,phone,email,notes,termId);
        Intent i = new Intent(this,TermList.class);

        startActivity(i);


    }
}