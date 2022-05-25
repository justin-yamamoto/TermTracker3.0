package android.justinyamamoto.termtracker30.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Intent;
import android.justinyamamoto.termtracker30.Database.Repository;
import android.justinyamamoto.termtracker30.Entities.Course;
import android.justinyamamoto.termtracker30.R;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
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
        List<Course> courses = r.findCourseById(termId);  //load courses by termId
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

    /**Call menu item. */
    public boolean onCreateOptionsMenu(Menu menu){
        //get menu
        getMenuInflater().inflate(R.menu.menu_courselist, menu);
        return true;
    }

    public void goToAddCourse(View view) {
        Intent i = new Intent(this,CourseAdd.class);
        i.putExtra("termId",termId);
        startActivity(i);
    }

    public void goToTermDetails(MenuItem item) {
        Intent i = new Intent(this,TermDetail.class);
        i.putExtra("termId",termId);
        i.putExtra("termName",termName);
        i.putExtra("termStartDate",termStart);
        i.putExtra("termEndDate",termEnd);
        startActivity(i);
    }

    public void deleteTerm(MenuItem item) {
        int termId1 = getIntent().getIntExtra("termId",-1);
        r.deleteTerm(termId1);
        Intent i = new Intent(this,TermList.class);
        startActivity(i);

     /**   List<Course> courses1 = r.getAllCourses();

        termId= termIdTv.getId();

        for (Course course : courses1){
            if (course.getTermId()!=termId){

                r.deleteTerm(termId);
               // Intent intent = new Intent (this,TermList.class);
               // startActivity(intent);
            }
            if (course.getTermId()==termId){
                NotificationCompat.Builder builder = new NotificationCompat.Builder(CourseList.this,"Notify");
                builder.setContentTitle("Alert!!!");
                builder.setContentText("Cannot Delete Term With Courses Associated to it.");
                builder.setSmallIcon(R.drawable.ic_launcher_background);
                builder.setAutoCancel(true);

                NotificationManagerCompat managerCompat = NotificationManagerCompat.from(CourseList.this);
                managerCompat.notify(1, builder.build());
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
                    NotificationChannel channel = new NotificationChannel("Notify", "blah blah", NotificationManager.IMPORTANCE_DEFAULT);
                    NotificationManager manager = getSystemService(NotificationManager.class);
                    manager.createNotificationChannel(channel);
                }

            }

        }*/

    }
}