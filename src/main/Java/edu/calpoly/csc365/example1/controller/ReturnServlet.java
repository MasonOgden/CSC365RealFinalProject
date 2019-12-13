package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.DaoManagerFactory;
import edu.calpoly.csc365.example1.dao.Dao;
import edu.calpoly.csc365.example1.dao.DaoManager;
import edu.calpoly.csc365.example1.entity.Checkout;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(name = "ReturnServlet", urlPatterns = "/returns")
public class ReturnServlet extends HttpServlet {
    private DaoManager dm;
    private Dao<Checkout> checkoutDao;

    public ReturnServlet() throws Exception {
        dm = DaoManagerFactory.createDaoManager();
        checkoutDao = dm.getCheckoutDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int copyNum = Integer.parseInt(request.getParameter("copyNum"));
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        Date dayReturned = null;
        Date dueBack = null;
        boolean ddExtended = false;
        Checkout checkout = new Checkout(studentId, bookId, copyNum, startDate, dayReturned, dueBack, ddExtended);
        this.checkoutDao.update(checkout);
        request.setAttribute("checkouts", checkout);
        request.getRequestDispatcher("return.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("return.jsp").forward(request, response);
    }

}