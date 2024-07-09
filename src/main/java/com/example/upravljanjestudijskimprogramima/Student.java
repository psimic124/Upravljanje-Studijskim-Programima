package com.example.upravljanjestudijskimprogramima;

public class Student extends Person {
    public Student() {
    }

    public Student(String studentId, String name) {
        super(studentId, name);
    }

    public String getStudentId() {
        return getId();
    }

    public void setStudentId(String studentId) {
        setId(studentId);
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }
}
