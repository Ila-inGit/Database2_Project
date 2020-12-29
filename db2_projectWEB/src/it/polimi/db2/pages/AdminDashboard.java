package it.polimi.db2.pages;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.polimi.db2.services.ProductService;

/**
 * Servlet implementation class AdminDashboard
 */
@WebServlet("/dashboard")
public class AdminDashboard extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	@EJB(name = "it.polimi.db2.services/ProductService")
	private ProductService productService;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("products", productService.getNextProducts());
		
		request.getRequestDispatcher("/AdminDashboard.jsp").forward(request, response);
	}



}
