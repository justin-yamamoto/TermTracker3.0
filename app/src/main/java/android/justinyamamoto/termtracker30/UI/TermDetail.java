package android.justinyamamoto.termtracker30.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.justinyamamoto.termtracker30.R;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class TermDetail extends AppCompatActivity {
    int termId;
    String termName;
    String termStart;
    String termEnd;

    TextView termIdTv;
    TextView termNameTv;
    TextView termStartTv;
    TextView termEndTv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_detail);
        //Menu back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        termName = getIntent().getStringExtra("termName");
        termNameTv = findViewById(R.id.detailTermNameTv);
        termNameTv.setText(termName);

        termStart = getIntent().getStringExtra("termStartDate");
        termStartTv = findViewById(R.id.detailStartDateTv);
        termStartTv.setText(termStart);

        termEnd = getIntent().getStringExtra("termEndDate");
        termEndTv = findViewById(R.id.detailEndDateTv);
        termEndTv.setText(termEnd);
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