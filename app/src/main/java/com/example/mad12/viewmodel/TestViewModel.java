package com.example.mad12.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mad12.model.entity.Test;
import com.example.mad12.model.repository.TestRepository;

import java.util.List;

public class TestViewModel extends AndroidViewModel {
    private TestRepository testRepository;

    public TestViewModel(@NonNull Application application) {
        super(application);
        testRepository = new TestRepository(application);
    }

    public MutableLiveData<List<Test>> getAllTestWrappers(String examID) {
        return testRepository.getMutableLiveData(examID);
    }
}
