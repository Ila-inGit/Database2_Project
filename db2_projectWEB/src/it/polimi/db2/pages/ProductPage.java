package it.polimi.db2.pages;

import java.io.IOException;
import java.util.Base64;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.polimi.db2.entities.User;
import it.polimi.db2.services.ProductService;
import it.polimi.db2.utils.ImageUtils;
import it.polimi.db2.utils.UserSessionUtils;


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
		
		String img_extension = "";
		
		String img = null;
		if(prod != null && prod.getPhoto() != null && prod.getPhoto().length > 0)
		{
			img = ImageUtils.toBase64(prod.getPhoto());
			img_extension = ImageUtils.getImageExtension(prod.getPhoto());		
		}		
		
		request.setAttribute("product", prod);
		request.setAttribute("product_image", img);
		request.setAttribute("product_image_ext", img_extension);
		request.getRequestDispatcher("/ProductPage.jsp").forward(request, response);

	}

}
