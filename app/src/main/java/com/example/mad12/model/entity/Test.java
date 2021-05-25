package com.example.mad12.model.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//Thông tin chi tiết về bài thi online
@Entity(tableName = "test_table")
public class Test {
    @PrimaryKey
    @SerializedName("_id")
    private String TestID;
    private String TestName;
    private String SubjectID;
    private List<String> TestType;
    @SerializedName("Question")
    private List<Question> questionList;
    private double TimeTotal;
    private String TimeStart;
    private String TimeEnd;
    private Boolean Status;

    public Test() {
    }

    public Test(String testID, String testName, String subjectID, List<String> testType, List<Question> questionList, double timeTotal, String timeStart, String timeEnd, Boolean status) {
        TestID = testID;
        TestName = testName;
        SubjectID = subjectID;
        TestType = testType;
        this.questionList = questionList;
        TimeTotal = timeTotal;
        TimeStart = timeStart;
        TimeEnd = timeEnd;
        Status = status;
    }

    public String getTestID() {
        return TestID;
    }

    public void setTestID(String testID) {
        TestID = testID;
    }

    public String getTestName() {
        return TestName;
    }

    public void setTestName(String testName) {
        TestName = testName;
    }

    public String getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(String subjectID) {
        SubjectID = subjectID;
    }

    public List<String> getTestType() {
        return TestType;
    }

    public void setTestType(List<String> testType) {
        TestType = testType;
    }

    public List<Question> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<Question> questionList) {
        this.questionList = questionList;
    }

    public double getTimeTotal() {
        return TimeTotal;
    }

    public void setTimeTotal(double timeTotal) {
        TimeTotal = timeTotal;
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

    public Boolean getStatus() {
        return Status;
    }

    public void setStatus(Boolean status) {
        Status = status;
    }
}
