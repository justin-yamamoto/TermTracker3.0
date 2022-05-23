package android.justinyamamoto.termtracker30.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.justinyamamoto.termtracker30.Database.Repository;
import android.justinyamamoto.termtracker30.Entities.Term;
import android.justinyamamoto.termtracker30.R;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

public class TermList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_term_list);
        //Menu back arrow
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        // set to Review
        RecyclerView recyclerView = findViewById(R.id.termRecycleView);
        Repository r = new Repository(getApplication());
        List<Term> terms = r.getAllTerms();
        final TermAdapter adapter = new TermAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter.setTerms(terms);
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

    public void goToAddTerm(View view) {

        Intent intent = new Intent (this,TermAdd.class);


        startActivity(intent);
    }
}