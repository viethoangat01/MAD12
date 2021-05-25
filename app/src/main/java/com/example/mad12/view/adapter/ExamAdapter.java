package com.example.mad12.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad12.R;
import com.example.mad12.model.entity.Exam;

public class ExamAdapter extends ListAdapter<Exam, ExamAdapter.ExamHolder> {

    public ExamAdapter() {
        super(DIFF_CALLBACK);
    }

    private static final DiffUtil.ItemCallback<Exam> DIFF_CALLBACK = new DiffUtil.ItemCallback<Exam>() {
        @Override
        public boolean areItemsTheSame(@NonNull Exam oldItem, @NonNull Exam newItem) {
            return oldItem.getExamID() == newItem.getExamID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Exam oldItem, @NonNull Exam newItem) {
            return oldItem.getExamName().equals(newItem.getExamName()) &&
                    oldItem.getTimeStart().equals(newItem.getTimeStart()) &&
                    oldItem.getTimeEnd().equals(newItem.getTimeEnd());
        }
    };

    @NonNull
    @Override
    public ExamHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.line_examonline, parent, false);
        return new ExamHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ExamHolder holder, int position) {
        Exam currentExam = getItem(position);
        holder.txtTitle.setText(currentExam.getExamName());
        holder.txtStart.setText(currentExam.getTimeStart());
        holder.txtEnd.setText(currentExam.getTimeEnd());
    }

    public Exam getExamAt(int position) {
        return getItem(position);
    }

    public class ExamHolder extends RecyclerView.ViewHolder {
        private TextView txtTitle, txtStart, txtEnd;

        public ExamHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.textview_lineexamonline_titleexam);
            txtStart = itemView.findViewById(R.id.textview_lineexamonline_starttime);
            txtEnd = itemView.findViewById(R.id.textview_lineexamonline_endtime);
        }
    }
}
