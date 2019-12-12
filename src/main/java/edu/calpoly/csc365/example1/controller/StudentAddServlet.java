package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.DaoCommand;
import edu.calpoly.csc365.example1.dao.DaoManager;
import edu.calpoly.csc365.example1.dao.DaoManagerFactory;
import edu.calpoly.csc365.example1.dao.StudentDaoCommandImpl;
import edu.calpoly.csc365.example1.dao.StudentDaoImpl;
import edu.calpoly.csc365.example1.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "StudentAddServlet", urlPatterns = "/create_student")
public class StudentAddServlet extends HttpServlet {
    private DaoManager dm;

    public StudentAddServlet() throws Exception {
        this.dm = DaoManagerFactory.createDaoManager();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("student_form2.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String studentType = (request.getParameter("studentType"));
        Integer id = Integer.parseInt(request.getParameter("id"));
        String firstName = (request.getParameter("firstName"));
        String lastName = (request.getParameter("lastName"));
        Student student = new Student();
        student.setStudentType(studentType);
        student.setId(id);
        student.setFirstName(firstName);
        student.setLastName(lastName);
        DaoCommand daoCommand = new StudentDaoCommandImpl(student);
        Object result = daoCommand.execute(this.dm);
        if (result != null) {
            student = (Student) result;
        }
        PrintWriter out = response.getWriter();
        out.println(student);
        out.close();
    }
}