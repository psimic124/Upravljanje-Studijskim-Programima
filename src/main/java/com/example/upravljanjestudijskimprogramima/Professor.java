package com.example.upravljanjestudijskimprogramima;

public class Professor extends Person {
    public Professor() {
    }

    public Professor(String professorId, String name) {
        super(professorId, name);
    }

    public String getProfessorId() {
        return getId();
    }

    public void setProfessorId(String professorId) {
        setId(professorId);
    }

    public String getName() {
        return super.getName();
    }

    public void setName(String name) {
        super.setName(name);
    }
}
