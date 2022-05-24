package android.justinyamamoto.termtracker30.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.justinyamamoto.termtracker30.Database.Repository;
import android.justinyamamoto.termtracker30.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AssessmentList extends AppCompatActivity {

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

    TextView aListCourseNameTv;
    TextView aListCourseIdTv;
    TextView aListCourseStartTv;
    TextView aListCourseEndTv;
    TextView aListCourseStatusTv;
    TextView aListInstructorTv;
    TextView aListPhoneTv;
    TextView aListEmailTv;

    Repository r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_list);
        //Menu back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        r = new Repository(getApplication());

        courseId = getIntent().getIntExtra("courseId",-1);
        aListCourseIdTv = findViewById(R.id.aListCourseIdTv);
        aListCourseIdTv.setText(String.valueOf(courseId));

        courseName = getIntent().getStringExtra("courseName");
        aListCourseNameTv = findViewById(R.id.aListCourseNameTv);
        aListCourseNameTv.setText(courseName);

        courseStartDate = getIntent().getStringExtra("courseStartDate");
        aListCourseStartTv = findViewById(R.id.aListCourseStartTv);
        aListCourseStartTv.setText(courseStartDate);

        courseEndDate = getIntent().getStringExtra("courseEndDate");
        aListCourseEndTv = findViewById(R.id.aListCourseEndTv);
        aListCourseEndTv.setText(courseEndDate);

        courseStatus = getIntent().getStringExtra("courseStatus");
        aListCourseStatusTv = findViewById(R.id.aListCourseStatusTv);
        aListCourseStatusTv.setText(courseStatus);

        instructorName = getIntent().getStringExtra("instructorName");
        aListInstructorTv = findViewById(R.id.aListInstructorTv);
        aListInstructorTv.setText(instructorName);

        phone = getIntent().getStringExtra("phone");
        aListPhoneTv = findViewById(R.id.aListPhoneTv);
        aListPhoneTv.setText(phone);

        email = getIntent().getStringExtra("email");
        aListEmailTv = findViewById(R.id.aListEmailTv);
        aListEmailTv.setText(email);

        notes = getIntent().getStringExtra("notes");

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

    /**Call menu item. */
    public boolean onCreateOptionsMenu(Menu menu){
        //get menu
        getMenuInflater().inflate(R.menu.menu_assessmentlist, menu);
        return true;
    }

    public void goToCourseDetails(MenuItem item) {
        Intent i = new Intent(this,CourseDetail.class);
        //send course data to course detail
        i.putExtra("courseId",courseId);
        i.putExtra("courseName",courseName);
        i.putExtra("courseStartDate",courseStartDate);
        i.putExtra("courseEndDate",courseEndDate);
        i.putExtra("courseStatus",courseStatus);
        i.putExtra("instructorName",instructorName);
        i.putExtra("phone",phone);
        i.putExtra("email",email);
        i.putExtra("notes",notes);

        startActivity(i);
    }

    public void goToEditCourse(MenuItem item) {
        Intent i = new Intent(this,CourseEdit.class);

        //send course data to course edit
        i.putExtra("courseId",courseId);
        i.putExtra("courseName",courseName);
        i.putExtra("courseStartDate",courseStartDate);
        i.putExtra("courseEndDate",courseEndDate);
        i.putExtra("courseStatus",courseStatus);
        i.putExtra("instructorName",instructorName);
        i.putExtra("phone",phone);
        i.putExtra("email",email);
        i.putExtra("notes",notes);
        i.putExtra("termId",termId);

        startActivity(i);
    }

    public void deleteCourse(MenuItem item) {
        r.deleteCourse(courseId);
        Intent i = new Intent(this,TermList.class);

        startActivity(i);

    }
}