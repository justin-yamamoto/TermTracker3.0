package android.justinyamamoto.termtracker30.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.justinyamamoto.termtracker30.Database.Repository;
import android.justinyamamoto.termtracker30.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

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

    SimpleDateFormat sdf;

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
    /**Call menu item. */
    public boolean onCreateOptionsMenu(Menu menu){
        //get menu
        getMenuInflater().inflate(R.menu.menu_coursedetails, menu);
        return true;
    }

/**Share Notes on click of share notes menu item. */
    public void shareNotes(MenuItem item) {
        Intent sendIntent = new Intent();
        sendIntent.setAction(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_TEXT,notes);
        sendIntent.putExtra(Intent.EXTRA_TITLE,courseName + " Notes ");
        sendIntent.setType("text/plain");
        Intent shareIntent=Intent.createChooser(sendIntent,null);
        startActivity(shareIntent);
    }


    public void courseStartNotify(MenuItem item) {
        courseStartDate = detailCStart.getText().toString();

        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);
        Date sDate = null;
        try{
            sDate = format.parse(courseStartDate);

        }catch(ParseException e){
            e.printStackTrace();
        }
        Long sTrigger = sDate.getTime();

        Intent i = new Intent(CourseDetail.this,MyReceiver.class);
        i.putExtra("key",courseName + " Starts " + courseStartDate);
        PendingIntent sSender = PendingIntent.getBroadcast(CourseDetail.this,MainActivity.DateAlert++,i,PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,sTrigger,sSender);
    }

    public void courseEndNotify(MenuItem item) {

        courseEndDate = detailCEnd.getText().toString();
        SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy", Locale.US);

        Date eDate = null;
        try{

             eDate = format.parse(courseEndDate);
        }catch(ParseException e){
            e.printStackTrace();
        }
        Long eTrigger = eDate.getTime();


        Intent i = new Intent(CourseDetail.this,MyReceiver.class);
        i.putExtra("key",courseName + " Ends Today");
        PendingIntent sSender = PendingIntent.getBroadcast(CourseDetail.this,MainActivity.DateAlert++,i,PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
        alarmManager.set(AlarmManager.RTC_WAKEUP,eTrigger,sSender);
    }
}