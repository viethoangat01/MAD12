package com.example.mad12.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mad12.model.entity.Exam;
import com.example.mad12.model.repository.ExamRepository;

import java.util.List;

public class ExamViewModel extends AndroidViewModel {
    private ExamRepository examRepository;

    public ExamViewModel(@NonNull Application application) {
        super(application);
        examRepository = new ExamRepository(application);
    }

    public MutableLiveData<List<Exam>> getAllExams() {
        return examRepository.getMutableLiveData();
    }
}
