package com.example.mad12.network;

import com.example.mad12.model.entity.Exam;
import com.example.mad12.model.entity.Test;
import com.example.mad12.model.entity.TestWrapper;
import com.example.mad12.model.entity.TestWrapper2;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface RestApiService {
    @GET("exams")
    Call<List<Exam>> getExamList();

    @GET("exams/{examID}/tests")
    Call<List<TestWrapper>> getListTestByExamID(@Path("examID") String examId);

    @GET("tests/{testID}")
    Call<List<TestWrapper2>> getTestByID(@Path("testID") String testId);

}
