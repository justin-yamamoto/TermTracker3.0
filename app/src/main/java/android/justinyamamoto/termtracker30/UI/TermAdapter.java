package android.justinyamamoto.termtracker30.UI;

import android.content.Context;
import android.content.Intent;
import android.justinyamamoto.termtracker30.Entities.Term;
import android.justinyamamoto.termtracker30.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TermAdapter extends RecyclerView.Adapter<TermAdapter.TermViewHolder> {

    class TermViewHolder extends RecyclerView.ViewHolder{

        private final TextView termItemView;

        private TermViewHolder(View itemView){
            super(itemView);
            termItemView = itemView.findViewById(R.id.listItemTextView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    final Term current = mTerms.get(position);
                    //send to next screen
                    Intent intent = new Intent(context,CourseList.class);
                    intent.putExtra("termId",current.getTermId());
                    intent.putExtra("termName", current.getTermName());
                    intent.putExtra("termStartDate", current.getTermStartDate());
                    intent.putExtra("termEndDate", current.getTermEndDate());
                    context.startActivity(intent);

                }
            });
        }
    }


    private List<Term> mTerms;
    private final Context context;
    private final LayoutInflater mInflater;

    public TermAdapter(Context context){
        mInflater = LayoutInflater.from(context);
        this.context=context;
    }

    @NonNull
    @Override
    public TermAdapter.TermViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.term_list_item,parent,false);
        return new TermViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TermAdapter.TermViewHolder holder, int position) {
        if (mTerms!=null){
            Term current = mTerms.get(position);
            int termId = current.getTermId();
            String termName = current.getTermName();
            String termStartDate = current.getTermStartDate();
            String termEndDate = current.getTermEndDate();

            holder.termItemView.setText(termId +": " +termName  + "\n" + "Start Date: " + termStartDate + "\n" + "End Date: " + termEndDate);


        }
        else{
            holder.termItemView.setText("No Term Information");
        }

    }

    public void setTerms(List<Term> terms){
        mTerms = terms;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        if (mTerms!=null){
            return mTerms.size();
        }
        else return 0;
    }
}
