package android.justinyamamoto.termtracker30.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.justinyamamoto.termtracker30.Database.Repository;
import android.justinyamamoto.termtracker30.R;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

public class AssessmentEdit extends AppCompatActivity {
    Repository r;

    int assessmentId;
    String assessmentName;
    String assessmentStartDate;
    String assessmentEndDate;
    String assessmentType;
    int courseId;

    EditText editAssessmentName;
    EditText editAssessmentStartDate;
    EditText editAssessmentEndDate;
    RadioButton editAssessmentPerformanceRBtn;
    RadioButton editAssessmentObjectiveRBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_edit);
        //Menu back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        r = new Repository(getApplication());

        assessmentId = getIntent().getIntExtra("assessmentId",-1);

        assessmentName = getIntent().getStringExtra("assessmentName");
        editAssessmentName = findViewById(R.id.editAssessmentName);
        editAssessmentName.setText(assessmentName);

        assessmentType = getIntent().getStringExtra("assessmentType");
        editAssessmentObjectiveRBtn = findViewById(R.id.editAssessmentObjectiveRBtn);
        editAssessmentPerformanceRBtn = findViewById(R.id.editAssessmentPerformanceRBtn);
        if (assessmentType.equals("Objective")){
            editAssessmentObjectiveRBtn.setChecked(true);
        }else
            editAssessmentPerformanceRBtn.setChecked(true);

        assessmentStartDate = getIntent().getStringExtra("assessmentStartDate");
        editAssessmentStartDate = findViewById(R.id.editAssessmentStartDate);
        editAssessmentStartDate.setText(assessmentStartDate);

        assessmentEndDate = getIntent().getStringExtra("assessmentEndDate");
        editAssessmentEndDate = findViewById(R.id.editAssessmentEndDate);
        editAssessmentEndDate.setText(assessmentEndDate);

        courseId = getIntent().getIntExtra("courseId",-1);
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

    public void saveEditAssessment(View view) {

        assessmentName = editAssessmentName.getText().toString();

        if (editAssessmentObjectiveRBtn.isChecked()){
            assessmentType = editAssessmentObjectiveRBtn.getText().toString();
        }
        else
            assessmentType = editAssessmentPerformanceRBtn.getText().toString();
        assessmentStartDate = editAssessmentStartDate.getText().toString();
        assessmentEndDate = editAssessmentEndDate.getText().toString();

        r.updateAssessmentById(assessmentId,assessmentName,assessmentStartDate,assessmentEndDate,assessmentType,courseId);

        Intent i = new Intent(this,TermList.class);

        startActivity(i);


    }

    public void checkRadio(View view) {
    }
}