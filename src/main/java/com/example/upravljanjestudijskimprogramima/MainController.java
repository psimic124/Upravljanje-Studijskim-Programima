package com.example.upravljanjestudijskimprogramima;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MainController {

    @FXML
    private Button btnDodajKolegij;

    @FXML
    private Button btnDodajProfesori;

    @FXML
    private Button btnDodajStudenti;

    @FXML
    private Button btnLogout;

    @FXML
    private Button btnLogout1;

    @FXML
    private Button btnLogout2;

    @FXML
    private Button btnObrisiKolegij;

    @FXML
    private Button btnObrisiProfesora;

    @FXML
    private Button btnObrisiStudenta;

    @FXML
    private Button btnPromijeniKolegij;

    @FXML
    private Button btnPromijeniProfesora;

    @FXML
    private Button btnPromijeniStudenta;

    @FXML
    private TextField studentNameField;
    @FXML
    private TextField studentIdField;
    @FXML
    private TableView<Student> tvStudent;
    @FXML
    private TableColumn<Student, String> studentIDColumn;
    @FXML
    private TableColumn<Student, String> studentImeColumn;

    @FXML
    private TextField professorNameField;
    @FXML
    private TextField professorIdField;
    @FXML
    private TableView<Professor> tvProfessor;
    @FXML
    private TableColumn<Professor, String> profesorIDColumn;
    @FXML
    private TableColumn<Professor, String> profesorImeColumn;

    @FXML
    private TextField courseNameField;
    @FXML
    private TextField courseIdField;
    @FXML
    public TextField courseProfessorField;
    @FXML
    private TableView<Course> tvCourse;
    @FXML
    private TableColumn<Course, String> idKolegijaColumn;
    @FXML
    private TableColumn<Course, String> imeKolegijaColumn;
    @FXML
    private TableColumn<Course, String> imeProfesoraColumn;
    private ObservableList<Student> studentData = FXCollections.observableArrayList();
    private ObservableList<Professor> professorData = FXCollections.observableArrayList();
    private ObservableList<Course> courseData = FXCollections.observableArrayList();

    private StudentRepository studentRepository = new StudentRepository();
    private ProfessorRepository professorRepository = new ProfessorRepository();
    private CourseRepository courseRepository = new CourseRepository();

    @FXML
    public void initialize() {

        studentIDColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentImeColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tvStudent.setItems(studentData);

        profesorIDColumn.setCellValueFactory(new PropertyValueFactory<>("professorId"));
        profesorImeColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        tvProfessor.setItems(professorData);

        idKolegijaColumn.setCellValueFactory(new PropertyValueFactory<>("courseId"));
        imeKolegijaColumn.setCellValueFactory(new PropertyValueFactory<>("courseName"));
        imeProfesoraColumn.setCellValueFactory(new PropertyValueFactory<>("professorName"));
        tvCourse.setItems(courseData);

        loadData();
    }

    @FXML
    private void addStudent(ActionEvent event) {
        String studentName = studentNameField.getText();
        String studentId = studentIdField.getText();
        if (!studentName.isEmpty() && !studentId.isEmpty()) {
            Student student = new Student(studentId, studentName);
            studentRepository.add(student);
            studentData.add(student);
            studentNameField.clear();
            studentIdField.clear();
            saveData();
        }
    }
    @FXML
    private void updateStudent(ActionEvent event) {
        Student student = tvStudent.getSelectionModel().getSelectedItem();
        if (student != null) {
            student.setName(studentNameField.getText());
            student.setStudentId(studentIdField.getText());
            studentRepository.update(student);
            tvStudent.refresh();
            saveData();
        }
    }

    @FXML
    private void deleteStudent(ActionEvent event) {
        Student student = tvStudent.getSelectionModel().getSelectedItem();
        if (student != null) {
            studentRepository.delete(student.getStudentId());
            studentData.remove(student);
            saveData();
        }
    }

    @FXML
    private void addProfessor(ActionEvent event) {
        String professorName = professorNameField.getText();
        String professorId = professorIdField.getText();
        if (!professorName.isEmpty() && !professorId.isEmpty()) {
            Professor professor = new Professor(professorId, professorName);
            professorRepository.add(professor);
            professorData.add(professor);
            professorNameField.clear();
            professorIdField.clear();
            saveData();
        }
    }

    @FXML
    private void updateProfessor(ActionEvent event) {
        Professor professor = tvProfessor.getSelectionModel().getSelectedItem();
        if (professor != null) {
            professor.setName(professorNameField.getText());
            professor.setProfessorId(professorIdField.getText());
            professorRepository.update(professor);
            tvProfessor.refresh();
            saveData();
        }
    }

    @FXML
    private void deleteProfessor(ActionEvent event) {
        Professor professor = tvProfessor.getSelectionModel().getSelectedItem();
        if (professor != null) {
            professorRepository.delete(professor.getProfessorId());
            professorData.remove(professor);
            saveData();
        }
    }

    @FXML
    private void addCourse(ActionEvent event) {
        try {
            String courseName = courseNameField.getText();
            String courseId = courseIdField.getText();
            String professorName = courseProfessorField.getText();

            if (courseName == null || courseName.isEmpty() ||
                    courseId == null || courseId.isEmpty() ||
                    professorName == null || professorName.isEmpty()) {
                System.out.println("Sva polja moraju biti popunjena!");
                return;
            }

            Course course = new Course(courseId, courseName, professorName);

            courseRepository.add(course);
            courseData.add(course);
            courseNameField.clear();
            courseIdField.clear();
            courseProfessorField.clear();
            saveData();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Došlo je do greške prilikom dodavanja kolegija.");
        }
    }


    @FXML
    private void updateCourse(ActionEvent event) {
        Course course = tvCourse.getSelectionModel().getSelectedItem();
        if (course != null) {
            course.setCourseName(courseNameField.getText());
            course.setCourseId(courseIdField.getText());
            courseRepository.update(course);
            tvCourse.refresh();
            saveData();
        }
    }

    @FXML
    private void deleteCourse(ActionEvent event) {
        Course course = tvCourse.getSelectionModel().getSelectedItem();
        if (course != null) {
            courseRepository.delete(course.getCourseId());
            courseData.remove(course);
            saveData();
        }
    }

    @FXML
    private void logout(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LoginView.fxml"));
            Parent root = loader.load();
            Stage stage = (Stage) ((Button) event.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveData() {
        studentRepository.save();
        professorRepository.save();
        courseRepository.save();
    }

    private void loadData() {
        studentRepository.load();
        professorRepository.load();
        courseRepository.load();

        studentData.setAll(studentRepository.getAll());
        professorData.setAll(professorRepository.getAll());
        courseData.setAll(courseRepository.getAll());
    }
}
