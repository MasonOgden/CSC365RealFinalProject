package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.DaoManagerFactory;
import edu.calpoly.csc365.example1.dao.Dao;
import edu.calpoly.csc365.example1.dao.DaoManager;
import edu.calpoly.csc365.example1.entity.Reservation;
import edu.calpoly.csc365.example1.service.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(name = "ReservationServlet", urlPatterns = "/reservations")
public class ReservationServlet extends HttpServlet {

    private DaoManager dm;
    private Dao<Reservation> reservationsDao;

    public ReservationServlet() throws Exception {
        dm = DaoManagerFactory.createDaoManager();
        reservationsDao = dm.getReservationDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie loginCookie = AuthenticationService.getLoginCookie(request);
        if (loginCookie == null) {
            response.sendRedirect("login");
        } else {
            response.addCookie(loginCookie);
            Set<Reservation> reservations = reservationsDao.getAll();
            request.setAttribute("reservations", reservations);
            request.setAttribute("message", "Hello " + loginCookie.getValue());
            request.getRequestDispatcher("reservations.jsp").forward(request, response);
        }
    }
}
