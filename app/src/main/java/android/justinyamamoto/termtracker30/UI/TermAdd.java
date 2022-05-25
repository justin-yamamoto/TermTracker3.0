package android.justinyamamoto.termtracker30.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.justinyamamoto.termtracker30.Database.Repository;
import android.justinyamamoto.termtracker30.Entities.Term;
import android.justinyamamoto.termtracker30.R;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;


public class TermAdd extends AppCompatActivity {

    Repository r;
    int termId;
    String termName;
    String termStartDate;
    String termEndDate;
    EditText termIdEt;
    EditText termNameEt;
    EditText termStartDateEt;
    EditText termEndDateEt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_add);
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

    public void saveTerm(View view) {

        termId = getIntent().getIntExtra("termId",-1);

        termNameEt = findViewById(R.id.addTermNameEt);
        termName = termNameEt.getText().toString();

        termStartDateEt = findViewById(R.id.addTermStartDateEt);
        termStartDate = termStartDateEt.getText().toString();

        termEndDateEt = findViewById(R.id.addTermEndDateEt);
        termEndDate = termEndDateEt.getText().toString();


      if (r.getAllTerms().isEmpty()){
          termId =1;
          Term term = new Term(termId,termName,termStartDate,termEndDate);
          r.insert(term);

          Intent i = new Intent(this,TermList.class);
          startActivity(i);

      }
      else{
          int newId = r.getAllTerms().get(r.getAllTerms().size()-1).getTermId()+1;
          Term term = new Term(newId,termName,termStartDate,termEndDate);
          r.insert(term);

          Intent i = new Intent(this,TermList.class);
          startActivity(i);
      }
    }
}