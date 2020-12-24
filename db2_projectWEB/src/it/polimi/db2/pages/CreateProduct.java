package it.polimi.db2.pages;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang.StringEscapeUtils;

import com.mysql.cj.jdbc.exceptions.PacketTooBigException;

import it.polimi.db2.exceptions.NotAvailableDateException;
import it.polimi.db2.services.ProductService;
import it.polimi.db2.utils.ImageUtils;

/**
 * Servlet implementation class CreateProduct
 */
@WebServlet("/product/new")
@MultipartConfig
public class CreateProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@EJB(name = "it.polimi.db2.services/ProductService")
	private ProductService productService;
	

    public CreateProduct() {
        super();
    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.getRequestDispatcher("/CreateProduct.jsp" ).forward(request, response);
	}


	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String name = StringEscapeUtils.escapeHtml(request.getParameter("fname"));
		if(name == null || name.length() < 1)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product name");
			return;
		}
		
		try {
		
			Part imgFile = request.getPart("fimage");
			InputStream imgContent = imgFile.getInputStream();
			byte[] image = ImageUtils.readImage(imgContent);
			
			String fDate = request.getParameter("fdate");
			Date displayDate = null;
			if(fDate != null && fDate.length() > 0) {
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd"); 
				displayDate = formatter.parse(fDate);
			}
			

			productService.createProduct(name, image, displayDate);
			
			
			request.setAttribute("message", "Created product: "+ name);
			request.getRequestDispatcher("/SuccessPage.jsp" ).forward(request, response);
			return;
			
		} 
		catch(NotAvailableDateException e) 
		{
			
			request.setAttribute("message", e.getMessage());
		}
		catch(Exception e)
		{
			request.setAttribute("message", "Your image size must be less than 4MB.");
			
		}
		

		request.getRequestDispatcher("/ErrorPage.jsp" ).forward(request, response);
		
		
	}

}
