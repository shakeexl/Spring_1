package ru.geekbrains;

import ru.geekbrains.persist.Product;
import ru.geekbrains.persist.ProductRepository;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebListener;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(urlPatterns = "/product/*")
public class ProductServlet extends HttpServlet {

    private ProductRepository productRepository;

    @Override
    public void init() throws ServletException {
        productRepository = (ProductRepository) getServletContext().getAttribute("productRepository");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter pw = resp.getWriter();
        try {
            if ((req.getPathInfo() == null || req.getPathInfo().equals("/")) && req.getParameter("id") == null) {
                viewProducts(resp);

                pw.println("</table>");
            } else if (req.getParameter("id") != null && productRepository.findById(Long.parseLong(req.getParameter("id"))) != null) {
                viewTable(resp);
                pw.println("<tr>");
                pw.println("<td>" + req.getParameter("id") + "</td>");
                pw.println("<td>" + productRepository.findById(Long.parseLong(req.getParameter("id"))).getName() + "</td>");
                pw.println("</tr>");
            } else {viewProducts(resp);}
        } catch (Exception exception) {
            pw.println("<h1>Error in your path!<h/1>");
        }
    }

    private void viewTable(HttpServletResponse resp) throws IOException {
        PrintWriter pw = resp.getWriter();
        pw.println("<table>");

        pw.println("<tr>");
        pw.println("<th>Id</th>");
        pw.println("<th>Name</th>");
        pw.println("</tr>");
    }

    private void viewProducts(HttpServletResponse resp) throws IOException {
        PrintWriter pw = resp.getWriter();
        viewTable(resp);

        for (Product product: productRepository.findAll()) {
            pw.println("<tr>");
            pw.println("<td>" + product.getId() + "</td>");
            pw.println("<td>" + product.getName() + "</td>");
            pw.println("</tr>");
        }
    }
}
