package com.example.upravljanjestudijskimprogramima;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainController {

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
    private void addCourse(ActionEvent event) {
        String courseName = courseNameField.getText();
        String courseId = courseIdField.getText();
        if (!courseName.isEmpty() && !courseId.isEmpty()) {
            Professor professor = professorData.isEmpty() ? null : professorData.get(0);
            String professorName = professor != null ? professor.getName() : "N/A";
            Course course = new Course(courseId, courseName, professorName);
            courseRepository.add(course);
            courseData.add(course);
            courseNameField.clear();
            courseIdField.clear();
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
