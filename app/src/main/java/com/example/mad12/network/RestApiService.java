package com.example.mad12.network;

import com.example.mad12.model.entity.Exam;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApiService {
    @GET("exams")
    Call<List<Exam>> getExamList();
}
