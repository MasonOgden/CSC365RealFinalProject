package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.BookDaoImpl;
import edu.calpoly.csc365.example1.dao.DaoManagerFactory;
import edu.calpoly.csc365.example1.dao.Dao;
import edu.calpoly.csc365.example1.dao.DaoManager;
import edu.calpoly.csc365.example1.entity.Book;
import edu.calpoly.csc365.example1.entity.Student;
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

@WebServlet(name = "BookServlet", urlPatterns = "/books")
public class BookServlet extends HttpServlet {

    private DaoManager dm;
    private Dao<Book> booksDao;
    private BookDaoImpl booksDaoImpl;

    public BookServlet() throws Exception {
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
            //Set<Book> searchBooks = booksDaoImpl.searchBook("bert", "", "");
            ArrayList<Book> booksOrdered = new ArrayList<>(books);
            Collections.sort(booksOrdered);
            request.setAttribute("books", booksOrdered);
            request.setAttribute("message", "Hello " + loginCookie.getValue());
            request.getRequestDispatcher("books.jsp").forward(request, response);
        }
    }
}