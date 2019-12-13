package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.BookDaoImpl;
import edu.calpoly.csc365.example1.dao.Dao;
import edu.calpoly.csc365.example1.dao.DaoManager;
import edu.calpoly.csc365.example1.dao.DaoManagerFactory;
import edu.calpoly.csc365.example1.entity.Book;
import edu.calpoly.csc365.example1.service.AuthenticationService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Set;

@WebServlet(name = "UserCheckoutServlet", urlPatterns = "/user_checkout")
public class UserCheckoutServlet extends HttpServlet {

    private DaoManager dm;
    private Dao<Book> booksDao;
    private BookDaoImpl booksDaoImpl;

    public UserCheckoutServlet() throws Exception {
        dm = DaoManagerFactory.createDaoManager();
        booksDao = dm.getBookDao();
        booksDaoImpl = new BookDaoImpl(dm.getConnection());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie loginCookie = AuthenticationService.getLoginCookie(request);
        if (loginCookie == null) {
            response.sendRedirect("login");
        } else {
            response.addCookie(loginCookie);
            Set<Book> books = booksDao.getAll();
            request.setAttribute("books", books);
            request.setAttribute("message", "Hello " + loginCookie.getValue());
            request.getRequestDispatcher("user_checkout.jsp").forward(request, response);
        }
    }
}
