package com.example.mad12.model.repository;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mad12.model.dao.ExamDao;
import com.example.mad12.model.database.ExamDatabase;
import com.example.mad12.model.entity.Exam;
import com.example.mad12.network.RestApiService;
import com.example.mad12.network.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExamRepository {
    private ExamDao examDao;
    private LiveData<List<Exam>> allExams;

    private List<Exam> exams = new ArrayList<>();
    private MutableLiveData<List<Exam>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public ExamRepository(Application application) {
        ExamDatabase examDatabase = ExamDatabase.getInstance(application);
        examDao = examDatabase.examDao();
        allExams = examDao.getAllExams();
        this.application = application;
    }

    //Get Exam List From Api
    public MutableLiveData<List<Exam>> getMutableLiveData() {
        RestApiService apiService = RetrofitInstance.getApiService();
        Call<List<Exam>> call = apiService.getExamList();
        call.enqueue(new Callback<List<Exam>>() {
            @Override
            public void onResponse(Call<List<Exam>> call, Response<List<Exam>> response) {
                List<Exam> examList = response.body();
                if (examList != null) {
                    mutableLiveData.setValue(examList);
                }
            }

            @Override
            public void onFailure(Call<List<Exam>> call, Throwable t) {
                Log.d("List size", "onFailure: " + t.getMessage());
            }
        });

        return mutableLiveData;
    }

    public void insert(Exam exam) {
        new InsertExamAsyncTask(examDao).execute(exam);
    }

    public void update(Exam exam) {
        new UpdateExamAsyncTask(examDao).execute(exam);
    }

    public void delete(Exam exam) {
        new DeleteExamAsyncTask(examDao).execute(exam);
    }

    public void deleteAllExams() {
        new DeleteAllExamAsyncTask(examDao).execute();
    }

    public static class InsertExamAsyncTask extends AsyncTask<Exam, Void, Void> {
        private ExamDao examDao;

        private InsertExamAsyncTask(ExamDao examDao) {
            this.examDao = examDao;
        }

        @Override
        protected Void doInBackground(Exam... exams) {
            examDao.insert(exams[0]);
            return null;
        }
    }

    public static class UpdateExamAsyncTask extends AsyncTask<Exam, Void, Void> {
        private ExamDao examDao;

        private UpdateExamAsyncTask(ExamDao examDao) {
            this.examDao = examDao;
        }

        @Override
        protected Void doInBackground(Exam... exams) {
            examDao.update(exams[0]);
            return null;
        }
    }

    public static class DeleteExamAsyncTask extends AsyncTask<Exam, Void, Void> {
        private ExamDao examDao;

        public DeleteExamAsyncTask(ExamDao examDao) {
            this.examDao = examDao;
        }

        @Override
        protected Void doInBackground(Exam... exams) {
            examDao.delete(exams[0]);
            return null;
        }
    }

    public static class DeleteAllExamAsyncTask extends AsyncTask<Exam, Void, Void> {
        private ExamDao examDao;

        public DeleteAllExamAsyncTask(ExamDao examDao) {
            this.examDao = examDao;
        }

        @Override
        protected Void doInBackground(Exam... exams) {
            examDao.deleteAllExams();
            return null;
        }
    }
}
