package com.example.mad12.model.dao;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.mad12.model.entity.Exam;

import java.util.List;

@Dao
public interface ExamDao {
    @Insert
    void insert(Exam exam);

    @Update
    void update(Exam exam);

    @Delete
    void delete(Exam exam);

    @Query("DELETE FROM exam_table")
    void deleteAllExams();

    @Query("SELECT * FROM exam_table")
    LiveData<List<Exam>> getAllExams();
}
