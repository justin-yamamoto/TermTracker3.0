package android.justinyamamoto.termtracker30.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.justinyamamoto.termtracker30.Database.Repository;
import android.justinyamamoto.termtracker30.Entities.Course;
import android.justinyamamoto.termtracker30.R;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class CourseList extends AppCompatActivity {

    int termId;
    String termName;
    String termStart;
    String termEnd;

    TextView termIdTv;
    TextView termNameTv;
    TextView termStartTv;
    TextView termEndTv;

    int courseId;

    Repository r;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_course_list);
        //Menu back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Set Term Information Area
        termIdTv = findViewById(R.id.cListTermIdTv);
        termId = getIntent().getIntExtra("termId",-1);
        termIdTv.setText(String.valueOf(termId));

        termNameTv = findViewById(R.id.cListTermNameTv);
        termName = getIntent().getStringExtra("termName");
        termNameTv.setText(termName);

        termStartTv = findViewById(R.id.cListTermStartTv);
        termStart = getIntent().getStringExtra("termStartDate");
        termStartTv.setText(termStart);

        termEndTv = findViewById(R.id.cListTermEndTv);
        termEnd = getIntent().getStringExtra("termEndDate");
        termEndTv.setText(termEnd);

        //Set RecyclerView
        RecyclerView recyclerView = findViewById(R.id.courseRecycleView);
        r = new Repository(getApplication());
        List<Course> courses = r.findCourseById(courseId);  //load courses by termId
        final CourseAdapter adapter = new CourseAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setCourses(courses);
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

    public void goToAddCourse(View view) {
        Intent i = new Intent(this,CourseAdd.class);
        i.putExtra("termId",termId);
        startActivity(i);
    }
}