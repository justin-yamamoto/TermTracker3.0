package android.justinyamamoto.termtracker30.Database;

import android.app.Application;
import android.justinyamamoto.termtracker30.DAO.AssessmentDAO;
import android.justinyamamoto.termtracker30.DAO.CourseDAO;
import android.justinyamamoto.termtracker30.DAO.TermDAO;
import android.justinyamamoto.termtracker30.Entities.Assessment;
import android.justinyamamoto.termtracker30.Entities.Course;
import android.justinyamamoto.termtracker30.Entities.Term;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    private TermDAO mTermDAO;
    private CourseDAO mCourseDAO;
    private AssessmentDAO mAssessmentDAO;
    private List<Term> mAllTerms;
    private List<Course>mAllCourses;
    private List<Assessment> mAllAssessments;
    private static int NUMBER_OF_THREADS=4;
    static final ExecutorService databaseExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public Repository(Application application){
        TTDatabaseBuilder db=TTDatabaseBuilder.getDatabase(application);
        mTermDAO = db.termDAO();
        mCourseDAO = db.courseDAO();
        mAssessmentDAO = db.assessmentDAO();
    }

    /**Get all Terms. */
    public List<Term>getAllTerms(){
        databaseExecutor.execute(()->{
            mAllTerms = mTermDAO.getAllTerms();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllTerms;
    }


    /**Insert a term. */
    public void insert(Term term){

        databaseExecutor.execute(()->{
            mTermDAO.insert(term);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**Update a Term. */
    public void update(Term term){
        databaseExecutor.execute(()->{
            mTermDAO.update(term);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**Delete a Term. */
    public void delete(Term term){
        databaseExecutor.execute(()->{
            mTermDAO.delete(term);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void deleteTerm(int termId){

        databaseExecutor.execute(()->{
            mTermDAO.deleteTerm(termId);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    /**Get all Courses. */
    public List<Course>getAllCourses(){
        databaseExecutor.execute(()->{
            mAllCourses = mCourseDAO.getAllCourses();
        });
        try {
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllCourses;
    }

    public List<Course>findCourseById(int id){
        databaseExecutor.execute(()->{
            mAllCourses = mCourseDAO.findCourseById(id);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllCourses;
    }

    /**Insert a course. */
    public void insert(Course course){

        databaseExecutor.execute(()->{
            mCourseDAO.insert(course);
        });
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        mCourseDAO.insert(course);
    }

    /**Update a Course. */
    public void update(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.update(course);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }



    /**Delete a Course. */
    public void delete(Course course){
        databaseExecutor.execute(()->{
            mCourseDAO.delete(course);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

/**Delete Course by Id. */
    public void deleteCourse(int courseId){

        databaseExecutor.execute(()->{
            mCourseDAO.deleteCourse(courseId);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    public void updateCourseById(int courseId,String courseName,String courseStartDate,String courseEndDate,String courseStatus,String instructorName,String phone,String email,String notes,int termId){

        databaseExecutor.execute(()->{
            mCourseDAO.updateCourseById(courseId,courseName,courseStartDate,courseEndDate,courseStatus,instructorName,phone,email,notes,termId);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**Get all Assessments. */
    public List<Assessment>getAllAssessments(){
        databaseExecutor.execute(()->{
            mAllAssessments = mAssessmentDAO.getAllAssessments();
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllAssessments;
    }

    public List<Assessment>findAssessmentById(int id){
        databaseExecutor.execute(()->{
            mAllAssessments = mAssessmentDAO.findAssessmentById(id);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        return mAllAssessments;
    }



    public void deleteAssessment(int id){
        databaseExecutor.execute(()->{
            mAssessmentDAO.deleteAssessment(id);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }

    /**Insert a assessment. */
    public void insert(Assessment assessment){

        databaseExecutor.execute(()->{
            mAssessmentDAO.insert(assessment);
        });
        try{
            Thread.sleep(2000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        mAssessmentDAO.insert(assessment);
    }

    /**Update a Assessment. */
    public void update(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.updateAssessment(assessment);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }


    /**Delete a Term. */
    public void delete(Assessment assessment){
        databaseExecutor.execute(()->{
            mAssessmentDAO.deleteAssessment(assessment);
        });
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
    public void updateAssessmentById(int assessmentId,String assessmentName,String assessmentStartDate,String assessmentEndDate,String assessmentType,int courseId){

        databaseExecutor.execute(()->{
            mAssessmentDAO.updateAssessmentById(assessmentId,assessmentName,assessmentStartDate,assessmentEndDate,assessmentType,courseId);
        });
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
    }
}
