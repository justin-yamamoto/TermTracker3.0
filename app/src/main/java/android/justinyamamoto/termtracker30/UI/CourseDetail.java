package android.justinyamamoto.termtracker30.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.justinyamamoto.termtracker30.Database.Repository;
import android.justinyamamoto.termtracker30.R;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class CourseDetail extends AppCompatActivity {

    int courseId;
    String courseName;
    String courseStartDate;
    String courseEndDate;
    String courseStatus;
    String instructorName;
    String phone;
    String email;
    String notes;

    TextView detailCName;
    TextView detailCStart;
    TextView detailCEnd;
    TextView detailStatus;
    TextView detailInstructor;
    TextView detailPhone;
    TextView detailEmail;
    TextView detailNotes;

    Repository r;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_detail);
        //Menu back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        courseName = getIntent().getStringExtra("courseName");
        detailCName = findViewById(R.id.detailCName);
        detailCName.setText(courseName);

        courseStartDate = getIntent().getStringExtra("courseStartDate");
        detailCStart = findViewById(R.id.detailCStart);
        detailCStart.setText(courseStartDate);

        courseEndDate = getIntent().getStringExtra("courseEndDate");
        detailCEnd = findViewById(R.id.detailCEnd);
        detailCEnd.setText(courseEndDate);

        courseStatus = getIntent().getStringExtra("courseStatus");
        detailStatus = findViewById(R.id.detailStatus);
        detailStatus.setText(courseStatus);

        instructorName = getIntent().getStringExtra("instructorName");
        detailInstructor = findViewById(R.id.detailInstructor);
        detailInstructor.setText(instructorName);

        phone = getIntent().getStringExtra("phone");
        detailPhone = findViewById(R.id.detailPhone);
        detailPhone.setText(phone);

        email = getIntent().getStringExtra("email");
        detailEmail = findViewById(R.id.detailEmail);
        detailEmail.setText(email);

        notes = getIntent().getStringExtra("notes");
        detailNotes = findViewById(R.id.detailNotes);
        detailNotes.setText(notes);


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
}