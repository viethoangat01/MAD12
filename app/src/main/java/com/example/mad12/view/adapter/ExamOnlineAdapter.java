package com.example.mad12.view.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.example.mad12.view.activity.ListSubjectOnlineActivity;
import com.example.mad12.view.adapter.interfaces.ItemClickListener;

public class ExamOnlineAdapter extends ListAdapter<Exam, ExamOnlineAdapter.ExamHolder> {
    Context context;

    public ExamOnlineAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
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
        final Exam currentExam = getItem(position);
        holder.txtTitle.setText(currentExam.getExamName());
        holder.txtStart.setText(currentExam.getTimeStart());
        holder.txtEnd.setText(currentExam.getTimeEnd());
        holder.txtJoin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("ExamID", currentExam.getExamID());
                Intent intent = new Intent(context, ListSubjectOnlineActivity.class);
                intent.putExtra("com.example.mad12.view.adapter", bundle);
                context.startActivity(intent);
                //Gửi thông tin mã cuộc thi sang màn môn thi online
            }
        });

    }

    public Exam getExamAt(int position) {
        return getItem(position);
    }

    public class ExamHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private TextView txtTitle, txtStart, txtEnd, txtJoin;
        private ItemClickListener itemClickListener;

        public ExamHolder(@NonNull View itemView) {
            super(itemView);
            txtTitle = itemView.findViewById(R.id.textview_lineexamonline_titleexam);
            txtStart = itemView.findViewById(R.id.textview_lineexamonline_starttime);
            txtEnd = itemView.findViewById(R.id.textview_lineexamonline_endtime);
            txtJoin = itemView.findViewById(R.id.textview_lineexamonline_join);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }
    }
}
