package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.BookDaoImpl;
import edu.calpoly.csc365.example1.dao.Dao;
import edu.calpoly.csc365.example1.dao.DaoManager;
import edu.calpoly.csc365.example1.dao.DaoManagerFactory;
import edu.calpoly.csc365.example1.entity.Book;
import edu.calpoly.csc365.example1.entity.Checkout;
import edu.calpoly.csc365.example1.entity.Reservation;
import edu.calpoly.csc365.example1.entity.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@WebServlet(name = "CheckoutBookServlet", urlPatterns = "/checkout_book")
public class CheckoutBookServlet extends HttpServlet {
    private DaoManager dm;
    private Dao<Student> studentDao;
    private Dao<Book> bookDao;
    private BookDaoImpl bookDAO;
    private Dao<Checkout> checkoutDao;

    public CheckoutBookServlet() throws Exception {
        dm = DaoManagerFactory.createDaoManager();
        studentDao = dm.getStudentDao();
        checkoutDao = dm.getCheckoutDao();
        bookDao = dm.getBookDao();
        bookDAO = new BookDaoImpl(dm.getConnection());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int copyNum = Integer.parseInt(request.getParameter("copyId"));
        String start = dateFormat.format(cal.getTime());
        java.sql.Date startDate = java.sql.Date.valueOf(start);
        if(studentDao.getById(studentId).getStudentType().equals("ug")) {
            cal.add(Calendar.DATE, 7);
        }
        else{
            cal.add(Calendar.DATE, 14);
        }
        String end = dateFormat.format(cal.getTime());
        java.sql.Date endDate = java.sql.Date.valueOf(end);
        Checkout checkout = new Checkout(studentId, bookId, copyNum, startDate, endDate, false);
        this.checkoutDao.insert(checkout);
        request.setAttribute("checkout", checkout);
        request.getRequestDispatcher("checkout_book.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String copyId = request.getParameter("copyNum");
        Book book = this.bookDAO.getByIdAndCopy(Integer.parseInt(id), Integer.parseInt(copyId));
        request.setAttribute("book", book);
        request.getRequestDispatcher("checkout_book.jsp").forward(request, response);
    }

}