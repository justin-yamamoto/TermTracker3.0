package android.justinyamamoto.termtracker30.UI;

import android.content.Context;
import android.content.Intent;
import android.justinyamamoto.termtracker30.Entities.Assessment;
import android.justinyamamoto.termtracker30.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class AssessmentAdapter extends RecyclerView.Adapter<AssessmentAdapter.AssessmentViewHolder> {

    class AssessmentViewHolder extends RecyclerView.ViewHolder{

        private final TextView termTxtView;
        private  final TextView courseTxtView1;

        private AssessmentViewHolder(View itemView){
            super(itemView);
            termTxtView = itemView.findViewById(R.id.cListTermNameTv);
            courseTxtView1 = itemView.findViewById(R.id.assessmentItemTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final Assessment current = mAssessments.get(position);
                    //send to next screen
                    Intent intent = new Intent(context,AssessmentDetail.class);
                    intent.putExtra("assessmentId",current.getAssessmentId());
                    intent.putExtra("assessmentType",current.getAssessmentType());
                    intent.putExtra("assessmentName", current.getAssessmentName());
                    intent.putExtra("assessmentStartDate", current.getAssessmentStartDate());
                    intent.putExtra("assessmentEndDate", current.getAssessmentEndDate());
                    intent.putExtra("courseId",current.getCourseId());
                    context.startActivity(intent);

                }
            });
        }

    }


    private List<Assessment> mAssessments;
    private final Context context;
    private final LayoutInflater mInflater;

    public AssessmentAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context=context;


    }
    @NonNull
    @Override
    public AssessmentAdapter.AssessmentViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.assessment_list_item,parent,false);
        return new AssessmentAdapter.AssessmentViewHolder(itemView);

    }

    @Override
    public void onBindViewHolder(@NonNull AssessmentAdapter.AssessmentViewHolder holder, int position) {
        if (mAssessments!=null){
            Assessment current = mAssessments.get(position);
            String name = current.getAssessmentName();
            String type = current.getAssessmentType();
            String sDate = current.getAssessmentStartDate();
            String eDate = current.getAssessmentEndDate();
            holder.courseTxtView1.setText(name  + "\n" + "Type: " + type + "\n" + "Start Date: " + sDate + "\n" + "End Date: " + eDate);

        }
        else{
            holder.courseTxtView1.setText("No Term Information");
        }

    }

    public void setAssessments(List<Assessment> assessments){
        mAssessments = assessments;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mAssessments!=null){
            return mAssessments.size();
        }
        else return 0;
    }

}
