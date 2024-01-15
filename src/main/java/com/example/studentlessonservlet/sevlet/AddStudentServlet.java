package com.example.studentlessonservlet.sevlet;

import com.example.studentlessonservlet.manager.LessonManager;
import com.example.studentlessonservlet.manager.StudentManager;
import com.example.studentlessonservlet.model.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/addstudent")
public class AddStudentServlet extends HttpServlet {
    private StudentManager studentManager = new StudentManager();
    private LessonManager lessonManager = new LessonManager();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("lessons", lessonManager.getallessons());
        req.getRequestDispatcher("addstudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("student_name");
        String surname = req.getParameter("student_surname");
        String email = req.getParameter("student_email");
        String age = req.getParameter("student_age");
        String lessonId = req.getParameter("student_lesson_id");
        studentManager.add(Student.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .age(Integer.parseInt(age))
                .lesson(lessonManager.getlessonbyid(Integer.parseInt(lessonId)))
                .build());

        resp.sendRedirect("/student");
    }

}