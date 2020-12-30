package it.polimi.db2.pages;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;

import it.polimi.db2.services.UserService;

/**
 * Servlet implementation class RegisterUser
 */
@WebServlet("/register")
public class RegisterUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB(name = "it.polimi.db2.services/UserService")
	private UserService usrService;

	public RegisterUser() {
		super();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/RegisterPage.jsp").forward(request, response);
	}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String email = null;
		String usrn = null;
		String pwd = null;

		email = StringEscapeUtils.escapeJava(request.getParameter("email"));
		usrn = StringEscapeUtils.escapeJava(request.getParameter("username"));
		pwd = StringEscapeUtils.escapeJava(request.getParameter("pwd"));

		if (usrn == null || pwd == null || email == null || usrn.isEmpty() || pwd.isEmpty() || email.isBlank()) {
			
			String message = "Missing attribute";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/RegisterPage.jsp").forward(request, response);
			return;
		}

		boolean result = usrService.registerUser(email, usrn, pwd);

		/// user must not been already registered

		if (result) {
			String message = "You are registered...will be redirected to homepage";
			request.setAttribute("success", message);
			
			// login after register
			// TODO: return user directly from register
			var usr = usrService.checkCredentials(usrn, pwd);
			request.getSession().setAttribute("user", usr);			
			response.sendRedirect(request.getContextPath()); // redirect to home page
			
			return;
		} else {
			String message = "Another user with same email!";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/RegisterPage.jsp").forward(request, response);
			return;
		}

	}

}
