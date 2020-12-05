package it.polimi.db2.pages;

import java.io.IOException;
import java.util.Base64;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.polimi.db2.services.ProductService;

/**
 * Servlet implementation class ProductPage
 */
@WebServlet("/product")
public class ProductPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@EJB(name = "it.polimi.db2.services/ProductService")
	private ProductService productService;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ProductPage() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		var prod = productService.getTodayProduct();
		
		String img = null;
		if(prod.getPhoto() != null)
		{
			byte[] encodeBase64 = Base64.getEncoder().encode(prod.getPhoto());
			img = new String(encodeBase64, "UTF-8");		
		}
		
		
		request.setAttribute("product", prod);
		request.setAttribute("product_image", img);
		request.getRequestDispatcher((prod != null)? "/ProductPage.jsp" : "/NoProduct.jsp" ).forward(request, response);

	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		doGet(request, response);
	}

}
