package android.justinyamamoto.termtracker30.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.justinyamamoto.termtracker30.Database.Repository;
import android.justinyamamoto.termtracker30.R;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class AssessmentDetail extends AppCompatActivity {
    Repository r;

    int assessmentId;
    String assessmentName;
    String assessmentStartDate;
    String assessmentEndDate;
    String assessmentType;
    int courseId;

    TextView aDetailId;
    TextView aDetailName;
    TextView aDetailType;
    TextView aDetailStart;
    TextView aDetailEnd;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_detail);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        assessmentId = getIntent().getIntExtra("assessmentId",-1);
        aDetailId = findViewById(R.id.aDetailId);
        aDetailId.setText(String.valueOf(assessmentId));


        assessmentName = getIntent().getStringExtra("assessmentName");
        aDetailName = findViewById(R.id.aDetailName);
        aDetailName.setText(assessmentName);

        assessmentStartDate = getIntent().getStringExtra("assessmentStartDate");
        aDetailStart = findViewById(R.id.aDetailStart);
        aDetailStart.setText(assessmentStartDate);

        assessmentEndDate = getIntent().getStringExtra("assessmentEndDate");
        aDetailEnd = findViewById(R.id.aDetailEnd);
        aDetailEnd.setText(assessmentEndDate);

        assessmentType = getIntent().getStringExtra("assessmentType");
        aDetailType = findViewById(R.id.aDetailType);
        aDetailType.setText(assessmentType);

        courseId = getIntent().getIntExtra("courseId",-1);
        r = new Repository(getApplication());
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
        getMenuInflater().inflate(R.menu.menu_assessmentdetails, menu);
        return true;
    }

    public void goToEditAssessment(MenuItem item) {
        Intent i = new Intent(this,AssessmentEdit.class);
        i.putExtra("assessmentId",assessmentId);
        i.putExtra("assessmentName",assessmentName);
        i.putExtra("assessmentType",assessmentType);
        i.putExtra("assessmentStartDate",assessmentStartDate);
        i.putExtra("assessmentEndDate",assessmentEndDate);
        i.putExtra("courseId",courseId);
        startActivity(i);
    }

    public void deleteAssessment(MenuItem item) {
        r.deleteAssessment(assessmentId);
        Intent i = new Intent(this,TermList.class);

        startActivity(i);
    }
}