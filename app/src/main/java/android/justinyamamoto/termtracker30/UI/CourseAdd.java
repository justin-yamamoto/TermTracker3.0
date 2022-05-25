package android.justinyamamoto.termtracker30.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.justinyamamoto.termtracker30.Database.Repository;
import android.justinyamamoto.termtracker30.Entities.Course;
import android.justinyamamoto.termtracker30.R;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class CourseAdd extends AppCompatActivity {

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

    EditText addCourseNameEt;
    EditText addCourseStartEt;
    EditText addCourseEndEt;
    RadioGroup addCourseRadioGroup;
    RadioButton addCourserBtnInProgress;
    RadioButton addCourserBtnCompleted;
    RadioButton addCourserBtnDropped;
    RadioButton addCourserBtnPlanned;
    EditText addCourseInstructorEt;
    EditText addCoursePhoneEt;
    EditText addCourseEmailEt;
    EditText addCourseNotesEt;
    Repository r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_add);

        termId = getIntent().getIntExtra("termId",-1);

        r= new Repository(getApplication());
    }

    public void checkRadio(View view) {
    }

    public void saveCourse(View view) {

        courseId = getIntent().getIntExtra("courseId",-1);

        addCourseNameEt = findViewById(R.id.addCourseNameEt);
        courseName = addCourseNameEt.getText().toString();

        addCourseStartEt = findViewById(R.id.addCourseStartEt);
        courseStartDate = addCourseStartEt.getText().toString();

        addCourseEndEt = findViewById(R.id.addCourseEndEt);
        courseEndDate = addCourseEndEt.getText().toString();

        addCourserBtnCompleted = findViewById(R.id.addCourserBtnCompleted);
        addCourserBtnDropped = findViewById(R.id.addCourserBtnDropped);
        addCourserBtnPlanned = findViewById(R.id.addCourserBtnPlanned);
        addCourserBtnInProgress = findViewById(R.id.addCourserBtnInProgress);

        if (addCourserBtnCompleted.isChecked()){
            courseStatus= addCourserBtnCompleted.getText().toString();
        }
        if (addCourserBtnInProgress.isChecked()){
            courseStatus = addCourserBtnInProgress.getText().toString();
        }
        if (addCourserBtnPlanned.isChecked()){
            courseStatus = addCourserBtnPlanned.getText().toString();
        }
        if (addCourserBtnDropped.isChecked()){
            courseStatus = addCourserBtnDropped.getText().toString();
        }

        addCourseInstructorEt = findViewById(R.id.addCourseInstructorEt);
        instructorName = addCourseInstructorEt.getText().toString();

        addCoursePhoneEt = findViewById(R.id.addCoursePhoneEt);
        phone = addCoursePhoneEt.getText().toString();

        addCourseEmailEt = findViewById(R.id.addCourseEmailEt);
        email = addCourseEmailEt.getText().toString();

        addCourseNotesEt = findViewById(R.id.addCourseNotesEt);
        if (addCourseNotesEt.getText().toString().isEmpty()){
            notes = "null";
        }
        else{
            notes = addCourseNotesEt.getText().toString();

        }

        if (r.getAllCourses().isEmpty()){
            courseId=1;
            Course course = new Course(courseId,courseName,courseStartDate,courseEndDate,courseStatus,instructorName,phone,email,notes,termId);
            r.insert(course);
        }else{
            int newId = r.getAllCourses().get(r.getAllCourses().size()-1).getCourseId()+1;
            Course course = new Course(newId,courseName,courseStartDate,courseEndDate,courseStatus,instructorName,phone,email,notes,termId);
            r.insert(course);
        }

    }
}