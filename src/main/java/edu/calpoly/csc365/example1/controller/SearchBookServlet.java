
package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.BookDaoImpl;
import edu.calpoly.csc365.example1.dao.Dao;
import edu.calpoly.csc365.example1.dao.DaoManager;
import edu.calpoly.csc365.example1.dao.DaoManagerFactory;
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
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;

@WebServlet(name = "SearchBookServlet", urlPatterns = "/searchBooks")
public class SearchBookServlet extends HttpServlet {

    private DaoManager dm;
    private Dao<Book> booksDao;
    private BookDaoImpl booksDaoImpl;

    public SearchBookServlet() throws Exception {
        dm = DaoManagerFactory.createDaoManager();
        booksDao = dm.getBookDao();
        booksDaoImpl = new BookDaoImpl(dm.getConnection());
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("search_books.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Cookie loginCookie = AuthenticationService.getLoginCookie(request);

        String title = request.getParameter("bookTitle");
        String author = request.getParameter("author");
        String cat = request.getParameter("category");
        Set<Book> searchBooks = booksDaoImpl.searchBook(title, author, cat);
        ArrayList<Book> booksOrdered = new ArrayList<>(searchBooks);
        Collections.sort(booksOrdered);
        request.setAttribute("books", booksOrdered);

        request.setAttribute("message", "Hello " + loginCookie.getValue());
        request.getRequestDispatcher("search_results.jsp").forward(request, response);
    }
}