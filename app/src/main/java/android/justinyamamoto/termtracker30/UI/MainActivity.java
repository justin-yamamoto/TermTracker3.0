package android.justinyamamoto.termtracker30.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.justinyamamoto.termtracker30.Database.Repository;
import android.justinyamamoto.termtracker30.Entities.Assessment;
import android.justinyamamoto.termtracker30.Entities.Course;
import android.justinyamamoto.termtracker30.Entities.Term;
import android.justinyamamoto.termtracker30.R;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static int DateAlert;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Repository repo = new Repository(getApplication());

       // Course course1 = new Course(1,"Mobile","01/11/1991","02/01/1992","In Progress", "Fake Name","808-610-0396","Fake@email.com","n/a",1);
        //repo.insert(course1);

      // Term term1 = new Term(1,"Term 1","02/01/2020","03/01/2020");
       // repo.insert(term1);

        //Assessment assessment1 = new Assessment(1,"Mobile App","01/01/2020","01/30/2020","Performance",1);
        //repo.insert(assessment1);
    }

    public void goToTermPage(View view) {
        Intent intent = new Intent (this,TermList.class);
        startActivity(intent);
    }
}