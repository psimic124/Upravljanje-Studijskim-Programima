package com.example.upravljanjestudijskimprogramima;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProfessorRepository implements Repository<Professor> {
    private List<Professor> professors = new ArrayList<>();
    private static final String FILE_NAME = "professors.json";

    @Override
    public void add(Professor professor) {
        professors.add(professor);
        save();
    }

    @Override
    public List<Professor> getAll() {
        return new ArrayList<>(professors);
    }

    @Override
    public Professor getById(String id) {
        Optional<Professor> professor = professors.stream().filter(p -> p.getId().equals(id)).findFirst();
        return professor.orElse(null);
    }

    @Override
    public void update(Professor professor) {
        delete(professor.getId());
        add(professor);
    }

    @Override
    public void delete(String id) {
        professors.removeIf(p -> p.getId().equals(id));
        save();
    }

    @Override
    public void save() {
        jsonUtil.saveToJSON(professors, FILE_NAME);
    }

    @Override
    public void load() {
        List<Professor> loadedProfessors = jsonUtil.loadFromJSON(FILE_NAME, Professor.class);
        if (loadedProfessors != null) {
            professors = loadedProfessors;
        }
    }
}
