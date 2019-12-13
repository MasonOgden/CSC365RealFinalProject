package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.CheckoutDaoImpl;
import edu.calpoly.csc365.example1.dao.Dao;
import edu.calpoly.csc365.example1.dao.DaoManager;
import edu.calpoly.csc365.example1.dao.DaoManagerFactory;
import edu.calpoly.csc365.example1.entity.Checkout;
import edu.calpoly.csc365.example1.service.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.Set;

@WebServlet(name = "PastDueServlet", urlPatterns = "/past_due")
public class PastDueCheckout extends HttpServlet {

    private DaoManager dm;
    private Dao<Checkout> checkoutsDao;
    private CheckoutDaoImpl daoImpl;

    public PastDueCheckout() throws Exception {
        dm = DaoManagerFactory.createDaoManager();
        checkoutsDao = dm.getCheckoutDao();
        daoImpl = new CheckoutDaoImpl(dm.getConnection());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie loginCookie = AuthenticationService.getLoginCookie(request);
        if (loginCookie == null) {
            response.sendRedirect("login");
        } else {
            response.addCookie(loginCookie);
            Calendar cal = Calendar.getInstance();
            Set<Checkout> pastDue = daoImpl.getPastDueCheckouts(cal.getTime());
            request.setAttribute("checkouts", pastDue);
            request.setAttribute("message", "Hello " + loginCookie.getValue());
            request.getRequestDispatcher("past_due.jsp").forward(request, response);
        }
    }
}
