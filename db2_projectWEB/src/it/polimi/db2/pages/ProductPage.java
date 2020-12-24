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
			byte[] encodeBase64 = Base64.getEncoder().encode(prod.getPhoto());
			img = new String(encodeBase64, "UTF-8");
			// Recognize image type by first byte (this numbers can be found with a search on google)
			switch( prod.getPhoto()[0]) {
				case (byte) 0x89:
					img_extension = "png";
					break;
				case (byte) 0x47:
					img_extension = "gif";
					break;
				case (byte) 0xFF:
					img_extension = "jpg";
					break;
			}  
		}
		
		
		
		request.setAttribute("product", prod);
		request.setAttribute("product_image", img);
		request.setAttribute("product_image_ext", img_extension);
		request.getRequestDispatcher((prod != null)? "/ProductPage.jsp" : "/NoProduct.jsp" ).forward(request, response);

	}

}
