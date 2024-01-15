package com.example.studentlessonservlet.sevlet;


import com.example.studentlessonservlet.manager.StudentManager;
import com.example.studentlessonservlet.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/student")
public class StudentServlet extends HttpServlet {
    StudentManager studentManager=new StudentManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Student> students=studentManager.getallstudents();
        req.setAttribute("students",students);
        req.getRequestDispatcher("/student.jsp").forward(req,resp);
    }
}
