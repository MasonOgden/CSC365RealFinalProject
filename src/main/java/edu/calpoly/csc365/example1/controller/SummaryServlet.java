package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.CheckoutDaoImpl;
import edu.calpoly.csc365.example1.dao.DaoManagerFactory;
import edu.calpoly.csc365.example1.dao.Dao;
import edu.calpoly.csc365.example1.dao.DaoManager;
import edu.calpoly.csc365.example1.entity.BookUsage;
import edu.calpoly.csc365.example1.entity.Checkout;
import edu.calpoly.csc365.example1.service.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

// PROBLEM: I hard-coded 2019 as the year for the summary.
// I'm not sure how to take this as input.

@WebServlet(name = "SummaryServlet", urlPatterns = "/summary")
public class SummaryServlet extends HttpServlet {

    private DaoManager dm;
    private Dao<Checkout> checkoutsDao;

    public SummaryServlet() throws Exception {
        dm = DaoManagerFactory.createDaoManager();
        checkoutsDao = dm.getCheckoutDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String year = request.getParameter("year");
        request.setAttribute("year", year);
        System.out.println("post year - " + year);
        //String year = request.getParameter("year");
        Set<BookUsage> summaryRows = ((CheckoutDaoImpl)checkoutsDao).getUsageSummary(year);
        BookUsage columnTotals = ((CheckoutDaoImpl)checkoutsDao).getUsageSummaryColumnTotals(year);
        ArrayList<BookUsage> orderedRows = new ArrayList<>(summaryRows);
        Collections.sort(orderedRows);
        orderedRows.add(columnTotals);
        System.out.println("get year - " + year);
        request.setAttribute("year",year);
        request.setAttribute("summaries", orderedRows);
        request.getRequestDispatcher("summary.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String year = request.getParameter("year");
        Set<BookUsage> summaryRows = ((CheckoutDaoImpl)checkoutsDao).getUsageSummary(year);
        BookUsage columnTotals = ((CheckoutDaoImpl)checkoutsDao).getUsageSummaryColumnTotals(year);
        ArrayList<BookUsage> orderedRows = new ArrayList<>(summaryRows);
        Collections.sort(orderedRows);
        orderedRows.add(columnTotals);
        System.out.println("get year - " + year);
        request.setAttribute("year",year);
        request.setAttribute("summaries", orderedRows);
        request.getRequestDispatcher("summary.jsp").forward(request, response);
    }
}