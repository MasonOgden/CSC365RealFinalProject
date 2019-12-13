package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.*;
import edu.calpoly.csc365.example1.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentAddServlet", urlPatterns = "/create_student")
public class StudentAddServlet extends HttpServlet {
    private DaoManager dm;
    private Dao<Student> studentDao;

    public StudentAddServlet() throws Exception {
        dm = DaoManagerFactory.createDaoManager();
        studentDao = dm.getStudentDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String studentType = request.getParameter("studentType");
        Student student = new Student(studentType, id, firstName, lastName);
        this.studentDao.insert(student);
        request.setAttribute("student", student);
        request.getRequestDispatcher("student_form2.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("student_form2.jsp").forward(request, response);
    }

}