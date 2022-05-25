package android.justinyamamoto.termtracker30.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.justinyamamoto.termtracker30.Database.Repository;
import android.justinyamamoto.termtracker30.Entities.Assessment;
import android.justinyamamoto.termtracker30.R;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;

public class AssessmentAdd extends AppCompatActivity {
    Repository r;

    int assessmentId;
    String assessmentName;
    String assessmentStartDate;
    String assessmentEndDate;
    String assessmentType;
    int courseId;

    EditText addAssessmentName;
    EditText addAssessmentStartDate;
    EditText addAssessmentEndDate;
    RadioGroup addAssessmentRadioGroup;
    RadioButton addAssessmentPerformanceRBtn;
    RadioButton addAssessmentObjectiveRBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_assessment_add);
        //Menu back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    public void checkRadio(View view) {
    }

    public void saveAssessment(View view) {

        assessmentId = getIntent().getIntExtra("assessmentId",-1);

        addAssessmentName = findViewById(R.id.addAssessmentName);
        assessmentName = addAssessmentName.getText().toString();

        addAssessmentStartDate = findViewById(R.id.addAssessmentStartDate);
        assessmentStartDate = addAssessmentStartDate.getText().toString();

        addAssessmentEndDate = findViewById(R.id.addAssessmentEndDate);
        assessmentEndDate = addAssessmentEndDate.getText().toString();

        addAssessmentPerformanceRBtn = findViewById(R.id.addAssessmentPerformanceRBtn);
        addAssessmentObjectiveRBtn = findViewById(R.id.addAssessmentObjectiveRBtn);
        if (addAssessmentObjectiveRBtn.isChecked()){
            assessmentType=addAssessmentObjectiveRBtn.getText().toString();
        }else
            assessmentType=addAssessmentPerformanceRBtn.getText().toString();

        courseId = getIntent().getIntExtra("courseId",-1);

            if (r.getAllAssessments().isEmpty()){
                assessmentId=1;
                Assessment assessment = new Assessment(assessmentId,assessmentName,assessmentStartDate,assessmentEndDate,assessmentType,courseId);
                r.insert(assessment);
            }
            else{
                int newId = r.getAllAssessments().get(r.getAllAssessments().size()-1).getAssessmentId()+1;
                Assessment assessment = new Assessment(newId,assessmentName,assessmentStartDate,assessmentEndDate,assessmentType,courseId);
                r.insert(assessment);

            }


        }


}