package com.example.studentlessonservlet.manager;

import com.example.studentlessonservlet.db.DBConnectionProvider;
import com.example.studentlessonservlet.model.User;

import java.sql.*;

public class UserManager {
    Connection connection = DBConnectionProvider.getinstsance().getConnection();
    LessonManager lessonManager = new LessonManager();


    public void add(User user) {
        String query = "INSERT INTO user(name,surname,email,password) VALUES(?,?,?,?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getSurname());
            preparedStatement.setString(3, user.getEmail());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.executeUpdate();
            ResultSet generatedkeys = preparedStatement.getGeneratedKeys();
            if (generatedkeys.next()) {
                int id = generatedkeys.getInt(1);
                user.setId(id);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public User getuserByEmail(String email){
        String sql = "SELECT * FROM user WHERE email = '" + email + "'";
        try(Statement statement= connection.createStatement()) {
            ResultSet resultSet= statement.executeQuery(sql);
            while (resultSet.next()){
              return User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password")).build();
            }

        }catch (SQLException e){
            e.printStackTrace();
        }return null;
    }
    public  User getUserById(int id){
        String sql = "SELECT * FROM user WHERE id="+id;
        try(Statement statement= connection.createStatement()) {
            ResultSet resultSet= statement.executeQuery(sql);
            while (resultSet.next()){
                return  User.builder()
                        .id(resultSet.getInt("id"))
                        .name(resultSet.getString("name"))
                        .surname(resultSet.getString("surname"))
                        .email(resultSet.getString("email"))
                        .password(resultSet.getString("password")).build();
            }


        }catch (SQLException e){
            e.printStackTrace();
        }return  null;
    }
}
