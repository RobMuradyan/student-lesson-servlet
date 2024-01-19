package com.example.studentlessonservlet.sevlet;

import com.example.studentlessonservlet.manager.LessonManager;
import com.example.studentlessonservlet.manager.StudentManager;
import com.example.studentlessonservlet.model.Student;
import com.example.studentlessonservlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import java.io.File;
import java.io.IOException;

@WebServlet(urlPatterns = "/addstudent")
@MultipartConfig(
        maxFileSize = 1024 * 1024 * 5,//5mb
        maxRequestSize = 1024 * 1024 * 10,
        fileSizeThreshold = 1024 * 1024 * 1

)
public class AddStudentServlet extends HttpServlet {
    private StudentManager studentManager = new StudentManager();
    private LessonManager lessonManager = new LessonManager();
    private final String UPLOAD_DIRECTORY = "C:\\Users\\111\\IdeaProjects\\student-lesson-servlet\\uploadDirectory";

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("lessons", lessonManager.getallessons());
        req.getRequestDispatcher("addstudent.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user= (User) req.getSession().getAttribute("user");

        String name = req.getParameter("name");
        String surname = req.getParameter("surname");
        String email = req.getParameter("email");
        String age = req.getParameter("age");
        String lessonId = req.getParameter("lesson_id");
        Part picture = req.getPart("picture");
        String pictureName = null;
        if (picture != null && picture.getSize() > 0) {
            pictureName = System.currentTimeMillis() + "_" + picture.getSubmittedFileName();
            picture.write(UPLOAD_DIRECTORY + File.separator + pictureName);

        }
        studentManager.add(Student.builder()
                .name(name)
                .surname(surname)
                .email(email)
                .age(Integer.parseInt(age))
                .picName(pictureName)
                .lesson(lessonManager.getlessonbyid(Integer.parseInt(lessonId)))
                        .user(user)
                .build());

        resp.sendRedirect("/student");
    }

}
