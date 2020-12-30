package it.polimi.db2.pages;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.polimi.db2.utils.UserSessionUtils;

/**
 * Servlet implementation class HomePage
 */
@WebServlet("/")
public class HomePage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		var usr = UserSessionUtils.getSessionUser(request);
		if(usr == null)
		{
			 request.getRequestDispatcher("/login").forward(request, response);
		} else {
			request.setAttribute("usr", usr);
			request.getRequestDispatcher("/product").forward(request, response);
		}
		
	}


}
