package com.example.mad12.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.os.Bundle;

import com.example.mad12.R;
import com.example.mad12.model.entity.Exam;
import com.example.mad12.view.adapter.ExamAdapter;
import com.example.mad12.viewmodel.ExamViewModel;

import java.util.List;

public class ListExamOnlineActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    ExamAdapter examAdapter;
    SwipeRefreshLayout swipeRefreshLayout;
    private ExamViewModel examViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_exam_online);
        initView();
        //Set recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        examAdapter = new ExamAdapter();
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
    }

    //Get data Exam from Api
    private void getExamList() {
        swipeRefreshLayout.setRefreshing(true);
        examViewModel.getAllExams().observe(this, new Observer<List<Exam>>() {
            @Override
            public void onChanged(List<Exam> exams) {
                swipeRefreshLayout.setRefreshing(false);
                examAdapter.submitList(exams);
            }
        });
    }

    private void initView() {
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_listexamonline);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout_listexamonline);
    }
}
