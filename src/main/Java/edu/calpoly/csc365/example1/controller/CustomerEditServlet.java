package edu.calpoly.csc365.example1.controller;

import edu.calpoly.csc365.example1.dao.DaoManagerFactory;
import edu.calpoly.csc365.example1.dao.Dao;
import edu.calpoly.csc365.example1.dao.DaoManager;
import edu.calpoly.csc365.example1.entity.Customer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CustomerEditServlet", urlPatterns = "/edit_customer")
public class CustomerEditServlet extends HttpServlet {
    private DaoManager dm;
    private Dao<Customer> customerDao;

    public CustomerEditServlet() throws Exception {
        dm = DaoManagerFactory.createDaoManager();
        customerDao = dm.getCustomerDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String ssn = request.getParameter("ssn");
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String phone = request.getParameter("phoneNumber");
        Customer customer = new Customer(id, ssn, name, address, phone);
        this.customerDao.update(customer);
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("customer_form.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        Customer customer = this.customerDao.getById(Integer.parseInt(id));
        request.setAttribute("customer", customer);
        request.getRequestDispatcher("customer_form.jsp").forward(request, response);
    }
}