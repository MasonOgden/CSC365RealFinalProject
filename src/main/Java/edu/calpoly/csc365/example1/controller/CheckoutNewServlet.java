package edu.calpoly.csc365.example1.controller;


import edu.calpoly.csc365.example1.dao.DaoCommand;
import edu.calpoly.csc365.example1.dao.CheckoutDaoCommandImpl;
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
import java.io.PrintWriter;
import java.sql.*;

@WebServlet(name = "CheckoutNewServlet", urlPatterns = "/new_checkout")
public class CheckoutNewServlet extends HttpServlet {
    private DaoManager dm;
    private Dao<Checkout> checkoutDao;

    public CheckoutNewServlet() throws Exception {
        dm = DaoManagerFactory.createDaoManager();
        checkoutDao = dm.getCheckoutDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("transaction.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer studentId = Integer.parseInt(request.getParameter("studentId"));
        Integer bookId = Integer.parseInt(request.getParameter("bookId"));
        Integer copyNum = Integer.parseInt(request.getParameter("copyNum"));
        Date startDate = Date.valueOf(request.getParameter("date"));
        System.out.println(startDate);
        Date returnDate = Date.valueOf(request.getParameter("returnDate"));
        Date dueBack = Date.valueOf(request.getParameter("dueBack"));
        Boolean ddExtended = Boolean.valueOf(request.getParameter("ddExtended"));
        Checkout checkout = new Checkout(0, 0, 0, null, null, false);
        checkout.setStudentId(studentId);
        checkout.setBookId(bookId);
        checkout.setCopyNum(copyNum);
        checkout.setStartDate(startDate);
        checkout.setReturnDate(returnDate);
        checkout.setDueBack(dueBack);
        checkout.setDdExtended(ddExtended);
        DaoCommand daoCommand = new CheckoutDaoCommandImpl(checkout);
        Object result = daoCommand.execute(this.dm);
        if (result != null) {
            checkout = (Checkout) result;
        }
        PrintWriter out = response.getWriter();
        out.println(checkout);
        out.close();
    }

}