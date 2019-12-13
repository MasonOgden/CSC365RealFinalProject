package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.*;
import edu.calpoly.csc365.example1.entity.Book;
import edu.calpoly.csc365.example1.entity.Checkout;
import edu.calpoly.csc365.example1.entity.Student;
import edu.calpoly.csc365.example1.service.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Set;

@WebServlet(name = "StudentBooksServlet", urlPatterns = "/student_books")
public class StudentBooksServlet extends HttpServlet {

    private DaoManager dm;
    private Dao<Book> booksDao;
    private BookDaoImpl booksDaoImpl;
    private Dao<Student> studentDao;
    private CheckoutDaoImpl checkoutDao;

    public StudentBooksServlet() throws Exception {
        dm = DaoManagerFactory.createDaoManager();
        booksDao = dm.getBookDao();
        booksDaoImpl = new BookDaoImpl(dm.getConnection());
        studentDao = dm.getStudentDao();
        checkoutDao = new CheckoutDaoImpl(dm.getConnection());

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));

        Set<Checkout> checkouts;
        if(request.getParameter("current").equals("T")){
            checkouts = checkoutDao.getActiveByStudentId(id);
        }
        else{
            checkouts = checkoutDao.getInactiveByStudentId(id);
        }
        Set<Checkout> activeCheckouts = checkoutDao.getActiveByStudentId(id);
        request.setAttribute("checkouts", checkouts);
        request.getRequestDispatcher("student_books.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie loginCookie = AuthenticationService.getLoginCookie(request);

        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int copyNum = Integer.parseInt(request.getParameter("copyNum"));

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        String date = dateFormat.format(cal.getTime());

        if(request.getParameter("extend").equals("F")) {
            checkoutDao.returnBook(studentId, bookId, copyNum, null, date);
        }
        else{
            checkoutDao.extendReturnDate(studentId, bookId, copyNum, 7);
        }
        request.getRequestDispatcher("student_books.jsp").forward(request, response);

    }
}
