package android.justinyamamoto.termtracker30.DAO;

import android.justinyamamoto.termtracker30.Entities.Assessment;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface AssessmentDAO {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Assessment assessment);

    @Update
    void updateAssessment(Assessment assessment);

    @Delete
    void deleteAssessment(Assessment assessment);

    @Query("SELECT * FROM assessments ORDER BY assessmentId;")
    List<Assessment> getAllAssessments();


    @Query("DELETE FROM assessments WHERE assessmentId=:id")
    void deleteAssessment(int id);

    @Query("SELECT * FROM assessments WHERE courseId=:id")
    List<Assessment> findAssessmentById(int id);

    @Query("UPDATE assessments SET assessmentName=:assessmentName,assessmentStartDate=:assessmentStartDate,assessmentEndDate=:assessmentEndDate,assessmentType=:assessmentType,courseId=:courseId WHERE assessmentId=:assessmentId")
    void updateAssessmentById(int assessmentId,String assessmentName,String assessmentStartDate,String assessmentEndDate,String assessmentType,int courseId);

}
