package com.example.studentlessonservlet.sevlet;

import com.example.studentlessonservlet.manager.LessonManager;
import com.example.studentlessonservlet.model.Lesson;
import com.example.studentlessonservlet.model.User;
import com.example.studentlessonservlet.util.TimeUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;

@WebServlet(urlPatterns = "/addLesson")
public class AddLessonservlet extends HttpServlet {
    LessonManager lessonManager = new LessonManager();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("addlesson.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user= (User) req.getSession().getAttribute("user");

        String name = req.getParameter("name");
        String duration = req.getParameter("duration");
        String lecturername = req.getParameter("lecturername");
        String price = req.getParameter("price");
        try {
            lessonManager.add(Lesson.builder()
                    .name(name)
                    .duration(TimeUtil.stringToTime(duration))
                    .lecturername(lecturername)
                    .price(Double.parseDouble(price))
                            .user(user)
                    .build());
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        resp.sendRedirect("/lesson");
    }
}
