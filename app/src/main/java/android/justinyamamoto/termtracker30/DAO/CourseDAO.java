package android.justinyamamoto.termtracker30.DAO;

import android.justinyamamoto.termtracker30.Entities.Course;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao

public interface CourseDAO {

        @Insert(onConflict = OnConflictStrategy.IGNORE)
        void  insert(Course course);

        @Update
        void update(Course course);

        @Delete
        void delete(Course course);

        @Query("SELECT * FROM courses ORDER BY courseId;")
        List<Course> getAllCourses();

        @Query("SELECT * FROM courses WHERE termId=:id")
        List<Course> findCourseById(int id);

        @Query("DELETE FROM courses WHERE courseId=:courseId")
        void deleteCourse(int courseId);

       // @Query("UPDATE courses SET courseName='courseName',courseStartDate=:courseStartDate,courseEndDate=:courseEndDate,courseStatus=:courseStatus,instructorName=:instructorName,phone=:phone,email=:email,notes=:notes,termId=:termId WHERE courseId= 'courseId' )
            //    void updateCourseById (int courseId,String courseName,String courseStartDate, String courseEndDate,String courseStatus,String instructorName,String phone, String email,String notes,int termId);

        @Query("UPDATE courses SET courseName=:courseName,courseStartDate=:courseStartDate,courseEndDate=:courseEndDate, courseStatus=:courseStatus,instructorName=:instructorName,phone=:phone,email=:email,notes =:notes, termId=:termId  WHERE courseId=:courseId")
        void updateCourseById(int courseId,String courseName,String courseStartDate,String courseEndDate,String courseStatus,String instructorName,String phone,String email,String notes,int termId);
    }
