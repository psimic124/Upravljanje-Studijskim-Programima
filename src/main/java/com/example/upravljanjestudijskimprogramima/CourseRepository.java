package com.example.upravljanjestudijskimprogramima;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseRepository implements Repository<Course> {
    private List<Course> courses = new ArrayList<>();
    private static final String FILE_NAME = "courses.json";

    @Override
    public void add(Course course) {
        courses.add(course);
        save();
    }

    @Override
    public List<Course> getAll() {
        return new ArrayList<>(courses);
    }

    @Override
    public Course getById(String id) {
        Optional<Course> course = courses.stream().filter(c -> c.getCourseId().equals(id)).findFirst();
        return course.orElse(null);
    }

    @Override
    public void update(Course course) {
        delete(course.getCourseId());
        add(course);
    }

    @Override
    public void delete(String id) {
        courses.removeIf(c -> c.getCourseId().equals(id));
        save();
    }

    @Override
    public void save() {
        jsonUtil.saveToJSON(courses, FILE_NAME);
    }

    @Override
    public void load() {
        List<Course> loadedCourses = jsonUtil.loadFromJSON(FILE_NAME, Course.class);
        if (loadedCourses != null) {
            courses = loadedCourses;
        }
    }
}
