package com.example.mad12.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mad12.R;
import com.example.mad12.model.entity.Exam;
import com.example.mad12.view.adapter.ExamOnlineAdapter;
import com.example.mad12.viewmodel.ExamViewModel;

import java.util.List;

public class ListExamOnlineActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    SwipeRefreshLayout swipeRefreshLayout;
    private ExamViewModel examViewModel;
    ExamOnlineAdapter examAdapter;
    private TextView txtBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exam_online);
        initView();
        //Set recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        examAdapter = new ExamOnlineAdapter(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(examAdapter);

        examViewModel = ViewModelProviders.of(this).get(ExamViewModel.class);
        //Get data Exam from Api
        getExamList();
        //Event refresh layout
        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getExamList();
            }
        });
        //Event Back
        txtBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListExamOnlineActivity.this, MainActivity.class));
            }
        });
    }

    //Get data Exam from Api
    private void getExamList() {
        swipeRefreshLayout.setRefreshing(true);
        examViewModel.getAllExams().observe(this, new Observer<List<Exam>>() {
            @Override
            public void onChanged(List<Exam> exams) {
                swipeRefreshLayout.setRefreshing(false);
                //Thực hiện kiểm tra thời gian bài thi đã hết hạn hay chưa để hiển thị
                for (int i = 0; i < exams.size(); i++) {
                    long timeNow = System.currentTimeMillis() / 1000;//Lấy timestamp hiện tại của hệ thống
                    long timeStart = Long.parseLong(exams.get(i).getTimeStart());
                    long timeEnd = Long.parseLong(exams.get(i).getTimeEnd());
                    //Kiểm tra thời gian hiện tại đã quá hạn thời gian cuộc thi chưa-> thực hiện xóa cuộc thi
                    if (timeNow > timeEnd) {
                        exams.remove(i);
                    }
                }
                examAdapter.submitList(exams);
            }
        });
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_listexamonline);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout_listexamonline);
        txtBack = (TextView) findViewById(R.id.textview_listexamonline_iconback);
    }
}
