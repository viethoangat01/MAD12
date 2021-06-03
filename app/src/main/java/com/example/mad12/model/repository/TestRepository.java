package com.example.mad12.model.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mad12.model.entity.Exam;
import com.example.mad12.model.entity.Test;
import com.example.mad12.model.entity.TestWrapper;
import com.example.mad12.model.entity.TestWrapper2;
import com.example.mad12.network.RestApiService;
import com.example.mad12.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TestRepository {

    private MutableLiveData<List<TestWrapper>> mutableListTest = new MutableLiveData<List<TestWrapper>>();
    private MutableLiveData<List<TestWrapper2>> mutableTest = new MutableLiveData<>();
    private Application application;

    public TestRepository(Application application) {
        this.application = application;
    }

    //Get List Test from API Exam
    public MutableLiveData<List<TestWrapper>> getLiveListTest(String examID) {
        RestApiService apiService = RetrofitInstance.getApiService();
        Call<List<TestWrapper>> call = apiService.getListTestByExamID(examID);
        call.enqueue(new Callback<List<TestWrapper>>() {
            @Override
            public void onResponse(Call<List<TestWrapper>> call, Response<List<TestWrapper>> response) {
                List<TestWrapper> testList = response.body();
                if (testList != null) {
                    mutableListTest.setValue(testList);
                }
            }

            @Override
            public void onFailure(Call<List<TestWrapper>> call, Throwable t) {
                Log.d("Get List test", "onFailure: " + t.getMessage());
            }
        });
        return mutableListTest;
    }

    //Get infor Test By ID
    public MutableLiveData<List<TestWrapper2>> getLiveTest(String testID) {
        RestApiService apiService = RetrofitInstance.getApiService();
        Call<List<TestWrapper2>> call = apiService.getTestByID(testID);
        call.enqueue(new Callback<List<TestWrapper2>>() {
            @Override
            public void onResponse(Call<List<TestWrapper2>> call, Response<List<TestWrapper2>> response) {
                List<TestWrapper2> testList = response.body();
                if (testList != null) {
                    mutableTest.setValue(testList);
                }
            }

            @Override
            public void onFailure(Call<List<TestWrapper2>> call, Throwable t) {
                Log.d("Get Test", "onFailure: " + t.getMessage());
            }
        });
        return mutableTest;
    }
}
