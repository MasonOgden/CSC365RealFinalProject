package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.DaoManagerFactory;
import edu.calpoly.csc365.example1.dao.Dao;
import edu.calpoly.csc365.example1.dao.DaoManager;
import edu.calpoly.csc365.example1.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "StudentEditServlet", urlPatterns = "/edit_student")
public class StudentEditServlet extends HttpServlet {
    private DaoManager dm;
    private Dao<Student> studentDao;

    public StudentEditServlet() throws Exception {
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
        this.studentDao.update(student);
        request.setAttribute("student", student);
        request.getRequestDispatcher("student_form.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Student student = this.studentDao.getById(Integer.parseInt(id));
        request.setAttribute("student", student);
        request.getRequestDispatcher("student_form.jsp").forward(request, response);
    }
}