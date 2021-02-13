package it.polimi.db2.pages;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.NonUniqueResultException;

import org.apache.commons.lang.StringEscapeUtils;

import it.polimi.db2.entities.User;
import it.polimi.db2.services.QuestionnaireService;
import it.polimi.db2.services.UserService;


@WebServlet("/login")
@EJB(name="QuestionnaireService", beanInterface = QuestionnaireService.class)
public class CheckLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	
	@EJB(name = "it.polimi.db2.services/UserService")
	private UserService usrService;

	public CheckLogin() {
		super();
	}

		
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.getRequestDispatcher("/LoginPage.jsp").forward(request, response);
	}



	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		
		String usrn = null;
		String pwd = null;
		
		usrn = StringEscapeUtils.escapeJava(request.getParameter("username"));
		pwd = StringEscapeUtils.escapeJava(request.getParameter("pwd"));
		
		if (usrn == null || pwd == null || usrn.isEmpty() || pwd.isEmpty()) { 
			 
			 String message = "Missing attribute";
			 request.setAttribute("message", message);
			 request.getRequestDispatcher("/LoginPage.jsp").forward(request, response);
			 return;
		 }

			
		User user;
		try {
			// query db to authenticate for user
			user = usrService.checkCredentials(usrn, pwd);
			
			
		} catch (NonUniqueResultException e) {
			String message = "More than one user";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/LoginPage.jsp").forward(request, response);
			return;
		}

		// If the user exists, add info to the session and go to home page, otherwise
		// show login page with error message

		if (user == null) {
			
			String message = "User is null...not registered yet";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/LoginPage.jsp").forward(request, response);
			return;
			
		} else {
			
			QuestionnaireService questService = null;
			try {
				//questService = (QuestionnaireService) new InitialContext().lookup("java:/openejb/local/QuestionnaireServiceLocalBean");
				questService = (QuestionnaireService) new InitialContext().lookup("java:comp/env/QuestionnaireService");
			} catch (NamingException e) {
				e.printStackTrace();
			}
			
			request.getSession().setAttribute("user", user);
			request.getSession().setAttribute("questService", questService);
			response.sendRedirect(request.getContextPath()); // redirect to home page		
			
			return;
		}

	}
}