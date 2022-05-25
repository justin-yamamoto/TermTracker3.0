package android.justinyamamoto.termtracker30.UI;

import android.content.Context;
import android.content.Intent;
import android.justinyamamoto.termtracker30.Entities.Course;
import android.justinyamamoto.termtracker30.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CourseAdapter extends RecyclerView.Adapter<CourseAdapter.CourseViewHolder>{


    class CourseViewHolder extends RecyclerView.ViewHolder{

        private final TextView termTxtView;
        private  final TextView courseTxtView;

        private CourseViewHolder(View itemView){
            super(itemView);
            termTxtView = itemView.findViewById(R.id.cListTermNameTv);
            courseTxtView = itemView.findViewById(R.id.courseItemTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final Course current = mCourses.get(position);
                    //send to next screen
                    Intent intent = new Intent(context,AssessmentList.class);
                    intent.putExtra("courseId",current.getCourseId());
                    intent.putExtra("courseName", current.getCourseName());
                    intent.putExtra("courseStartDate", current.getCourseStartDate());
                    intent.putExtra("courseEndDate", current.getCourseEndDate());
                    intent.putExtra("courseStatus",current.getCourseStatus());
                    intent.putExtra("instructorName",current.getInstructorName());
                    intent.putExtra("phone",current.getPhone());
                    intent.putExtra("email", current.getEmail());
                    intent.putExtra("notes", current.getNotes());
                    intent.putExtra("termId",current.getTermId());
                    context.startActivity(intent);

                }
            });
        }

    }


    private List<Course> mCourses;
    private final Context context;
    private final LayoutInflater mInflater;

    public CourseAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context=context;


    }
    @NonNull
    @Override
    public CourseAdapter.CourseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.course_list_item,parent,false);
        return new CourseAdapter.CourseViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull CourseAdapter.CourseViewHolder holder, int position) {
        if (mCourses!=null){
            Course current = mCourses.get(position);
            int courseId = current.getCourseId();
            String name = current.getCourseName();
            String sDate = current.getCourseStartDate();
            String eDate = current.getCourseEndDate();
            int termId = current.getTermId();
            holder.courseTxtView.setText(courseId + ": " + name  + "\n" + "Start Date: " + sDate + "\n" + "End Date: " + eDate);

        }
        else{
            holder.courseTxtView.setText("No Term Information");
        }

    }

    public void setCourses(List<Course> courses){
        mCourses = courses;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mCourses!=null){
            return mCourses.size();
        }
        else return 0;
    }

}
