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

@WebServlet(name = "CheckoutNewServlet", urlPatterns = "/create_checkout")
public class CheckoutNewServlet extends HttpServlet {
    private DaoManager dm;
    private Dao<Checkout> checkoutDao;

    public CheckoutNewServlet() throws Exception {
        dm = DaoManagerFactory.createDaoManager();
        checkoutDao = dm.getCheckoutDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("checkout_form.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int studentId = Integer.parseInt(request.getParameter("studentId"));
        int bookId = Integer.parseInt(request.getParameter("bookId"));
        int copyNum = Integer.parseInt(request.getParameter("copyNum"));
        Date startDate = Date.valueOf(request.getParameter("startDate"));
        //Date returnDate = Date.valueOf(request.getParameter("returnDate"));
        Checkout checkout = new Checkout(studentId, bookId, copyNum, startDate, null, false);
        this.checkoutDao.insert(checkout);
        request.setAttribute("checkouts", checkout);
        request.getRequestDispatcher("checkout_form.jsp").forward(request, response);
    }
}