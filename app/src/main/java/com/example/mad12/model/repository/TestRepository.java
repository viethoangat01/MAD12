package com.example.mad12.model.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mad12.model.entity.Exam;
import com.example.mad12.model.entity.Test;
import com.example.mad12.network.RestApiService;
import com.example.mad12.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestRepository {
    private List<Exam> exams = new ArrayList<>();
    private MutableLiveData<List<Test>> mutableLiveData = new MutableLiveData<List<Test>>();
    private Application application;

    public TestRepository(Application application) {
        this.application = application;
    }

    //Get Test from API Exam
    public MutableLiveData<List<Test>> getMutableLiveData(String examID) {
        RestApiService apiService = RetrofitInstance.getApiService();
        Call<List<Test>> call = apiService.getListTestByExamID(examID);
        call.enqueue(new Callback<List<Test>>() {
            @Override
            public void onResponse(Call<List<Test>> call, Response<List<Test>> response) {
                List<Test> testList = response.body();
                if (testList != null) {
                    mutableLiveData.setValue(testList);
                }
            }

            @Override
            public void onFailure(Call<List<Test>> call, Throwable t) {
                Log.d("List size", "onFailure: " + t.getMessage());
            }
        });

        return mutableLiveData;

    }
}
