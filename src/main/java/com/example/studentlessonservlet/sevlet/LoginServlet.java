package com.example.studentlessonservlet.sevlet;

import com.example.studentlessonservlet.manager.UserManager;
import com.example.studentlessonservlet.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/login")
public class LoginServlet extends HttpServlet {
UserManager userManager=new UserManager();
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");
        User user = userManager.getuserByEmail(email);
        if (user!=null&&password.equals(user.getPassword())){
  resp.sendRedirect("/home");
        }else {
            req.getSession().setAttribute("msg","invalid email or password");
       req.getRequestDispatcher("/").forward(req,resp);
        }

    }
}
