package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.*;
import edu.calpoly.csc365.example1.entity.*;

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

@WebServlet(name = "ReserveBookServlet", urlPatterns = "/reserve_book")
public class ReserveBookServlet extends HttpServlet {
    private DaoManager dm;
    private Dao<Student> studentDao;
    private Dao<Book> bookDao;
    private BookDaoImpl bookDAO;
    private Dao<Reservation> resDao;

    public ReserveBookServlet() throws Exception {
        dm = DaoManagerFactory.createDaoManager();
        studentDao = dm.getStudentDao();
        resDao = dm.getReservationDao();
        bookDao = dm.getBookDao();
        bookDAO = new BookDaoImpl(dm.getConnection());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();

        Date date = new Date();
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int copyNum = Integer.parseInt(request.getParameter("copyId"));
        String start = dateFormat.format(cal.getTime());
        java.sql.Date startDate = java.sql.Date.valueOf(start);
        Reservation res = new Reservation(studentId, bookId, copyNum, startDate, null, false, false);
        this.resDao.insert(res);
        request.setAttribute("reservation", res);
        request.getRequestDispatcher("reserve_book.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String copyId = request.getParameter("copyNum");
        Book book = this.bookDAO.getByIdAndCopy(Integer.parseInt(id), Integer.parseInt(copyId));
        request.setAttribute("book", book);
        request.getRequestDispatcher("reserve_book.jsp").forward(request, response);
    }

}