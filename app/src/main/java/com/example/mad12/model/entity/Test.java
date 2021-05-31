package com.example.mad12.model.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Test implements Serializable {
    @SerializedName("_id")
    private String TestID;
    private String TestName;
    private String SubjectID;
    private String TestType;
    @SerializedName("Question")
    private List<String> questionList;
    private double TimeTotal;
    private String TimeStart;
    private String TimeEnd;

    public Test() {
    }

    public Test(String testID, String testName, String subjectID, String testType, List<String> questionList, double timeTotal, String timeStart, String timeEnd) {
        TestID = testID;
        TestName = testName;
        SubjectID = subjectID;
        TestType = testType;
        this.questionList = questionList;
        TimeTotal = timeTotal;
        TimeStart = timeStart;
        TimeEnd = timeEnd;
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

    public String getTestType() {
        return TestType;
    }

    public void setTestType(String testType) {
        TestType = testType;
    }

    public List<String> getQuestionList() {
        return questionList;
    }

    public void setQuestionList(List<String> questionList) {
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
}
