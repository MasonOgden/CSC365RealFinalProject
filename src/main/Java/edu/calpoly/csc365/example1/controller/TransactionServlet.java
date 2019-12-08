package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.DaoCommand;
import edu.calpoly.csc365.example1.dao.DaoManager;
import edu.calpoly.csc365.example1.dao.DaoManagerFactory;
import edu.calpoly.csc365.example1.dao.TransactionDaoCommandImpl;
import edu.calpoly.csc365.example1.entity.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;

@WebServlet(name = "TransactionServlet", urlPatterns = "/transaction")
public class TransactionServlet extends HttpServlet {
    private DaoManager dm;

    public TransactionServlet() throws Exception {
        this.dm = DaoManagerFactory.createDaoManager();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("transaction.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer customerId = Integer.parseInt(request.getParameter("customerId"));
        Integer cardNumber = Integer.parseInt(request.getParameter("cardNumber"));
        Integer vendorId = Integer.parseInt(request.getParameter("vendorId"));
        Date tdate = Date.valueOf(request.getParameter("date"));
        System.out.println(tdate);
        Double amount = Double.parseDouble(request.getParameter("amount"));
        Transaction transaction = new Transaction();
        transaction.setCustomerId(customerId);
        transaction.setCardNumber(cardNumber);
        transaction.setVendorId(vendorId);
        transaction.setDate(tdate);
        transaction.setAmount(amount);
        DaoCommand daoCommand = new TransactionDaoCommandImpl(transaction);
        Object result = daoCommand.execute(this.dm);
        if (result != null) {
            transaction = (Transaction) result;
        }
        PrintWriter out = response.getWriter();
        out.println(transaction);
        out.close();
    }
}