package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.Dao;
import edu.calpoly.csc365.example1.dao.DaoManager;
import edu.calpoly.csc365.example1.dao.DaoManagerFactory;
import edu.calpoly.csc365.example1.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "SelectYearServlet", urlPatterns = "/select_year")
public class SelectYearServlet extends HttpServlet {
    private DaoManager dm;
    private Dao<Student> studentDao;

    public SelectYearServlet() throws Exception {
        dm = DaoManagerFactory.createDaoManager();
        studentDao = dm.getStudentDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String year = request.getParameter("year");
        request.setAttribute("year", year);
        System.out.println("select year post = " + year);

        request.getRequestDispatcher("select_year.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String year = request.getParameter("year");
        request.setAttribute("year", year);
        System.out.println("select year get = " + year);

        request.getRequestDispatcher("select_year.jsp").forward(request, response);
    }

}