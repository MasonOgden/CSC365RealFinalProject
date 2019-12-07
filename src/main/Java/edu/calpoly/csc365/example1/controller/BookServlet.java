package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.DaoManagerFactory;
import edu.calpoly.csc365.example1.dao.Dao;
import edu.calpoly.csc365.example1.dao.DaoManager;
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

@WebServlet(name = "BookServlet", urlPatterns = "/books")
public class BookServlet extends HttpServlet {

    private DaoManager dm;
    private Dao<Book> booksDao;

    public BookServlet() throws Exception {
        dm = DaoManagerFactory.createDaoManager();
        booksDao = dm.getBookDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie loginCookie = AuthenticationService.getLoginCookie(request);
        if (loginCookie == null) {
            response.sendRedirect("login");
        } else {
            response.addCookie(loginCookie);
            Set<Book> students = booksDao.getAll();
            request.setAttribute("books", students);
            request.setAttribute("message", "Hello " + loginCookie.getValue());
            request.getRequestDispatcher("books.jsp").forward(request, response);
        }
    }
}
