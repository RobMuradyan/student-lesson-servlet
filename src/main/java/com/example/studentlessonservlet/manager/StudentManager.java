package com.example.studentlessonservlet.manager;

import com.example.studentlessonservlet.db.DBConnectionProvider;
import com.example.studentlessonservlet.model.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentManager {
    Connection connection = DBConnectionProvider.getinstsance().getConnection();
    LessonManager lessonManager = new LessonManager();

    public List<Student> getallstudents() {
        String sql = "SELECT * FROM students";
        List<Student> students = new ArrayList<>();
        try (Statement statement = connection.createStatement();) {
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                students.add(Student.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .age(resultSet.getInt("age"))
                        .lesson(lessonManager.getlessonbyid(resultSet.getInt("lesson_id")))
                        .build());
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    public void add(Student student) {
        String query = "INSERT INTO students(name,surname,email,age,lesson_id) VALUES(?,?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, student.getName());
            preparedStatement.setString(2, student.getSurname());
            preparedStatement.setString(3, student.getEmail());
            preparedStatement.setInt(4, student.getAge());
            preparedStatement.setInt(5, student.getLesson().getId());
            preparedStatement.executeUpdate();
            ResultSet generatedkeys = preparedStatement.getGeneratedKeys();
            if (generatedkeys.next()) {
                int id = generatedkeys.getInt(1);
                student.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql = "DELETE FROM students WHERE id=" + id;
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate(sql);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
