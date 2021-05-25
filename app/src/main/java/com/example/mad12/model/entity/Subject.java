package com.example.mad12.model.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

//Thông tin môn học
@Entity(tableName = "subject_table")
public class Subject {
    @PrimaryKey
    @NonNull
    private String SubjectID;
    private String Name;
    private String GradeID;

    public Subject() {
    }

    public Subject(@NonNull String subjectID, String name, String gradeID) {
        SubjectID = subjectID;
        Name = name;
        GradeID = gradeID;
    }

    @NonNull
    public String getSubjectID() {
        return SubjectID;
    }

    public void setSubjectID(@NonNull String subjectID) {
        SubjectID = subjectID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getGradeID() {
        return GradeID;
    }

    public void setGradeID(String gradeID) {
        GradeID = gradeID;
    }
}
