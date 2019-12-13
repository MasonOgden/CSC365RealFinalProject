package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.DaoManagerFactory;
import edu.calpoly.csc365.example1.dao.Dao;
import edu.calpoly.csc365.example1.dao.DaoManager;
import edu.calpoly.csc365.example1.entity.Student;
import edu.calpoly.csc365.example1.service.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

@WebServlet(name = "StudentServlet", urlPatterns = "/students")
public class StudentServlet extends HttpServlet {

    private DaoManager dm;
    private Dao<Student> studentsDao;

    public StudentServlet() throws Exception {
        dm = DaoManagerFactory.createDaoManager();
        studentsDao = dm.getStudentDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie loginCookie = AuthenticationService.getLoginCookie(request);
        if (loginCookie == null) {
            response.sendRedirect("login");
        } else {
            response.addCookie(loginCookie);
            Set<Student> students = studentsDao.getAll();
            ArrayList<Student> studentsOrdered = new ArrayList<>(students);
            Collections.sort(studentsOrdered);
            request.setAttribute("students", students);
            request.setAttribute("message", "Hello " + loginCookie.getValue());
            request.getRequestDispatcher("students.jsp").forward(request, response);
        }
    }
}
