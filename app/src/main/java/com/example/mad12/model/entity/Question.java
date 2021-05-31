package com.example.mad12.model.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.util.List;

//Thông tin về câu hỏi trong 1 đề thi
@Entity(tableName = "question_table")
public class Question {
    @PrimaryKey
    @NonNull
    private int QuestionID;
    private String Description;
    private String SubjectID;
    @SerializedName("Answer")
    private List<String> answerList;
    private int Result;

    public Question() {
    }

    public Question(int questionID, String description, String subjectID, List<String> answerList, int result) {
        QuestionID = questionID;
        Description = description;
        SubjectID = subjectID;
        answerList = answerList;
        Result = result;
    }

    public int getQuestionID() {
        return QuestionID;
    }

    public void setQuestionID(int questionID) {
        QuestionID = questionID;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(String subjectID) {
        SubjectID = subjectID;
    }

    public List<String> getAnswer() {
        return answerList;
    }

    public void setAnswer(List<String> answer) {
        answerList = answer;
    }

    public int getResult() {
        return Result;
    }

    public void setResult(int result) {
        Result = result;
    }
}
