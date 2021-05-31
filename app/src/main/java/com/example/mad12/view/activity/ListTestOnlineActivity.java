package com.example.mad12.view.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad12.R;
import com.example.mad12.model.entity.Test;
import com.example.mad12.view.adapter.TestOnlineAdapter;
import com.example.mad12.viewmodel.TestViewModel;

import java.util.List;

public class ListTestOnlineActivity extends AppCompatActivity {
    private TestViewModel testViewModel;
    RecyclerView recyclerView;
    TestOnlineAdapter testOnlineAdapter;
    private TextView txtExamName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_test_online);
        initView();
        testViewModel = ViewModelProviders.of(this).get(TestViewModel.class);
        //Set recyclerview
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);
        testOnlineAdapter = new TestOnlineAdapter(this);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(testOnlineAdapter);
        getDataBundle();
    }

    private void initView() {
        txtExamName = (TextView) findViewById(R.id.textview_listtestonline_examname);
        recyclerView = (RecyclerView) findViewById(R.id.recyclerview_listtestonline);
    }

    private void getDataBundle() {
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra("com.example.mad12.view.adapter");
        if (bundle != null) {
            String examID = bundle.getString("ExamID");
            String examName = bundle.getString("ExamName");
            txtExamName.setText(examName);
            //Lấy danh sách các bài test trong kì thi
            getListTest(examID);
        }
    }

    private void getListTest(String examID) {
        testViewModel.getAllTestWrappers(examID).observe(this, new Observer<List<Test>>() {
            @Override
            public void onChanged(List<Test> tests) {
                Toast.makeText(ListTestOnlineActivity.this, tests.get(0).getTestName(), Toast.LENGTH_SHORT).show();
                testOnlineAdapter.submitList(tests);
            }
        });
    }
}
