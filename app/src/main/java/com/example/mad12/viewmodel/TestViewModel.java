package com.example.mad12.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.mad12.model.entity.TestWrapper;
import com.example.mad12.model.entity.TestWrapper2;
import com.example.mad12.model.repository.TestRepository;

import java.util.List;

public class TestViewModel extends AndroidViewModel {
    private TestRepository testRepository;

    public TestViewModel(@NonNull Application application) {
        super(application);
        testRepository = new TestRepository(application);
    }

    public MutableLiveData<List<TestWrapper>> getAllTestWrappers(String examID) {
        return testRepository.getLiveListTest(examID);
    }

    public MutableLiveData<List<TestWrapper2>> getTestWrapper2(String testID) {
        return testRepository.getLiveTest(testID);
    }
}
