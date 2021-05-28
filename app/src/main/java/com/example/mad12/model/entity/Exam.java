package com.example.mad12.model.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//Thông tin về cuộc thi
@Entity(tableName = "exam_table")
public class Exam {
    @PrimaryKey
    @NonNull
    @SerializedName("_id")
    private String ExamID;
    private String ExamName;
    private String TimeStart;
    private String TimeEnd;

    public Exam() {
    }

    public Exam(@NonNull String examID, String examName, String timeStart, String timeEnd) {
        ExamID = examID;
        ExamName = examName;
        TimeStart = timeStart;
        TimeEnd = timeEnd;
    }

    @NonNull
    public String getExamID() {
        return ExamID;
    }

    public void setExamID(@NonNull String examID) {
        ExamID = examID;
    }

    public String getExamName() {
        return ExamName;
    }

    public void setExamName(String examName) {
        ExamName = examName;
    }

    public String getTimeStart() {
        return TimeStart;
    }

    public void setTimeStart(String timeStart) {
        TimeStart = timeStart;
    }

    public String getTimeEnd() {
        return TimeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        TimeEnd = timeEnd;
    }
}
