package com.example.upravljanjestudijskimprogramima;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StudentRepository implements Repository<Student> {
    private List<Student> students = new ArrayList<>();
    private static final String FILE_NAME = "students.json";

    @Override
    public void add(Student student) {
        students.add(student);
        save();
    }

    @Override
    public List<Student> getAll() {
        return new ArrayList<>(students);
    }

    @Override
    public Student getById(String id) {
        Optional<Student> student = students.stream().filter(s -> s.getId().equals(id)).findFirst();
        return student.orElse(null);
    }

    @Override
    public void update(Student student) {
        delete(student.getId());
        add(student);
    }

    @Override
    public void delete(String id) {
        students.removeIf(s -> s.getId().equals(id));
        save();
    }

    @Override
    public void save() {
        jsonUtil.saveToJSON(students, FILE_NAME);
    }

    @Override
    public void load() {
        List<Student> loadedStudents = jsonUtil.loadFromJSON(FILE_NAME, Student.class);
        if (loadedStudents != null) {
            students = loadedStudents;
        }
    }
}
