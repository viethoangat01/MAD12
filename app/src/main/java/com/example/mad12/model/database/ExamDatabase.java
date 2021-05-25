package com.example.mad12.model.database;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.mad12.model.dao.ExamDao;
import com.example.mad12.model.entity.Exam;

@Database(entities = {Exam.class}, version = 1)
public abstract class ExamDatabase extends RoomDatabase {
    private static ExamDatabase instance;

    public abstract ExamDao examDao();

    public static synchronized ExamDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), ExamDatabase.class, "exam_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallBack)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallBack = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateAsyncTask(instance).execute();
        }
    };

    private static class PopulateAsyncTask extends AsyncTask<Void, Void, Void> {
        private ExamDao examDao;

        public PopulateAsyncTask(ExamDatabase db) {
            examDao = db.examDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            //Insert information

            return null;
        }
    }
}
