package com.example.studentlessonservlet.sevlet;

import com.example.studentlessonservlet.manager.LessonManager;
import com.example.studentlessonservlet.model.Lesson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/lesson")
public class LessonServlet extends HttpServlet {
    LessonManager lessonManager=new LessonManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Lesson> lessons=lessonManager.getallessons();
        req.setAttribute("lessons1",lessons);
        req.getRequestDispatcher("/lesson.jsp").forward(req,resp);
    }
}
