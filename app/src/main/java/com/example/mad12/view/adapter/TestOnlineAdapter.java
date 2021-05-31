package com.example.mad12.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad12.R;
import com.example.mad12.model.entity.Test;
import com.example.mad12.view.adapter.interfaces.ItemClickListener;

public class TestOnlineAdapter extends ListAdapter<Test, TestOnlineAdapter.TestHolder> {
    Context context;

    public TestOnlineAdapter(Context context) {
        super(DIFF_CALLBACK);
        this.context = context;
    }

    private static final DiffUtil.ItemCallback<Test> DIFF_CALLBACK = new DiffUtil.ItemCallback<Test>() {
        @Override
        public boolean areItemsTheSame(@NonNull Test oldItem, @NonNull Test newItem) {
            return oldItem.getTestID() == newItem.getTestID();
        }

        @Override
        public boolean areContentsTheSame(@NonNull Test oldItem, @NonNull Test newItem) {
            return oldItem.getTestName().equals(newItem.getTestName()) &&
                    oldItem.getSubjectID().equals(newItem.getSubjectID()) &&
                    oldItem.getTimeStart().equals(newItem.getTimeStart()) &&
                    oldItem.getTimeEnd().equals(newItem.getTimeEnd());
        }
    };

    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.line_testonline, parent, false);
        return new TestHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, int position) {
        Test test = getItem(position);
        holder.txtTestName.setText(test.getTestName());
        holder.txtTimeStart.setText(test.getTimeStart());
        holder.txtTimeEnd.setText(test.getTimeEnd());

    }

    public class TestHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView txtLogo, txtTestName, txtTimeStart, txtTimeEnd;
        private ItemClickListener itemClickListener;

        public TestHolder(@NonNull View itemView) {
            super(itemView);
            txtLogo = itemView.findViewById(R.id.textview_linetestonline_logo);
            txtTestName = itemView.findViewById(R.id.textview_linetestonline_titletest);
            txtTimeStart = itemView.findViewById(R.id.textview_linetestonline_starttime);
            txtTimeEnd = itemView.findViewById(R.id.textview_linetestonline_endtime);
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
