package android.justinyamamoto.termtracker30.Database;

import android.content.Context;
import android.justinyamamoto.termtracker30.DAO.AssessmentDAO;
import android.justinyamamoto.termtracker30.DAO.CourseDAO;
import android.justinyamamoto.termtracker30.DAO.TermDAO;
import android.justinyamamoto.termtracker30.Entities.Assessment;
import android.justinyamamoto.termtracker30.Entities.Course;
import android.justinyamamoto.termtracker30.Entities.Term;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Term.class, Course.class, Assessment.class}, version = 1, exportSchema = false)

public abstract class TTDatabaseBuilder extends RoomDatabase {
    public  abstract TermDAO termDAO();
    public abstract CourseDAO courseDAO();
    public  abstract AssessmentDAO assessmentDAO();

    private static volatile TTDatabaseBuilder INSTANCE;

    static TTDatabaseBuilder getDatabase(final Context context) {
        if (INSTANCE==null){
            synchronized (TTDatabaseBuilder.class) {
                if (INSTANCE == null) {

                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(), TTDatabaseBuilder.class, "TTDatabase")
                            .fallbackToDestructiveMigration()
                            .build();
                }
            }
        }
        return INSTANCE;

    }
}
