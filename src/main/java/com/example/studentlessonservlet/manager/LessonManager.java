package com.example.studentlessonservlet.manager;

import com.example.studentlessonservlet.db.DBConnectionProvider;
import com.example.studentlessonservlet.model.Lesson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class LessonManager {
    Connection connection= DBConnectionProvider.getinstsance().getConnection();

    public List<Lesson> getallessons(){
        String sql="SELECT * FROM lesson";
        List<Lesson> lessons=new ArrayList<>();
        try(Statement statement= connection.createStatement()) {
            ResultSet resultSet= statement.executeQuery(sql);
            while (resultSet.next()){
                lessons.add(Lesson.builder()
                    .id(resultSet.getInt("lesson_id"))
                    .name(resultSet.getString("lesson_name"))
                    .duration(resultSet.getTime("lesson_duration"))
                    .lecturername(resultSet.getString("lesson_lecturername"))
                    .price(resultSet.getDouble("lesson_price"))
                    .build());
            }

        }catch (SQLException e){
            e.printStackTrace();
        }return  lessons;

    }
    public Lesson getlessonbyid(int id){
        String sql = "SELECT * FROM lesson WHERE lesson_id = " + id;

        try (Statement statement = connection.createStatement()) {
            ResultSet resultSet = statement.executeQuery(sql);
            if (resultSet.next()){
                return
                        Lesson.builder()
                                .id(resultSet.getInt("lesson_id"))
                                .name(resultSet.getString("lesson_name"))
                                .duration(resultSet.getTime("lesson_duration"))
                                .lecturername(resultSet.getString("lesson_lecturername"))
                                .price(resultSet.getDouble("lesson_price"))
                                .build();

            }

        }catch (SQLException e){
            e.printStackTrace();
        }return null;
    }
    public  void add(Lesson lesson){
        String sql="INSERT INTO lesson(lesson_name,lesson_duration,lesson_lecturername,lesson_price) VALUES(?,?,?,?)";
        try(PreparedStatement preparedStatement= connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1,lesson.getName());
            preparedStatement.setTime(2,  lesson.getDuration());
            preparedStatement.setString(3,lesson.getLecturername());
            preparedStatement.setDouble(4,lesson.getPrice());
            preparedStatement.executeUpdate();
            ResultSet resultSet= preparedStatement.getGeneratedKeys();
            if (resultSet.next()){
                lesson.setId(resultSet.getInt(1));
            }

        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void delete(int id) {
        String sql="DELETE FROM lesson WHERE lesson_id="+id;
        try(Statement statement= connection.createStatement()) {
            statement.executeUpdate(sql);

        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
