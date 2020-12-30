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
 * Servlet implementation class DeleteProduct
 */
@WebServlet("/product/delete")
public class DeleteProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB(name = "it.polimi.db2.services/ProductService")
	private ProductService productService;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pid = request.getParameter("id");
		boolean err = false;
		
		try {
			
			int id = Integer.parseInt(pid);
			if(id < 0 || !productService.deleteProduct(id)) {
				err = true;
			}
			
				
		} catch (NumberFormatException e)
		{
			err = true;
		}
		
		if(err) {
			request.setAttribute("message", "Invalid product id");
		} else {
			request.setAttribute("success", true);
			request.setAttribute("message", "Product removed successfully");
		}
		
		
		request.setAttribute("back_link", request.getContextPath()+"/dashboard");
		request.getRequestDispatcher("/ResultPage.jsp").forward(request, response);
	}


}
