package it.polimi.db2.pages;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ejb.EJB;
import javax.persistence.NonUniqueResultException;

import org.apache.commons.lang.StringEscapeUtils;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import it.polimi.db2.entities.User;
import it.polimi.db2.services.UserService;


@WebServlet("/")
public class CheckLogin extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	//private TemplateEngine templateEngine;
	
	
	@EJB(name = "it.polimi.db2.services/UserService")
	private UserService usrService;

	public CheckLogin() {
		super();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// obtain and escape params
		String usrn = null;
		String pwd = null;
		try {
			usrn = StringEscapeUtils.escapeJava(request.getParameter("username"));
			pwd = StringEscapeUtils.escapeJava(request.getParameter("pwd"));
			
			/*
			 * if (usrn == null || pwd == null || usrn.isEmpty() || pwd.isEmpty()) { throw
			 * new Exception("Missing or empty credential value"); }
			 */

			/*
			 * PrintWriter writer = response.getWriter(); writer.println("<h1>Hello " + usrn
			 * + "</h1>"); writer.close();
			 */
			
			
		} catch (Exception e) {
			PrintWriter writer = response.getWriter();
			writer.println("missing credential values");
			writer.close();
			return;
		}
		User user;
		try {
			// query db to authenticate for user
			user = usrService.checkCredentials(usrn, pwd);
			
			
		} catch (NonUniqueResultException e) {
			PrintWriter writer = response.getWriter();
			writer.println("more than one user with the same name");
			writer.close();
			return;
		}

		// If the user exists, add info to the session and go to home page, otherwise
		// show login page with error message

		String path;
		if (user == null) {
			
			
			PrintWriter writer = response.getWriter();
			writer.println("user null");
			writer.close();
			/*
			 * ServletContext servletContext = getServletContext(); final WebContext ctx =
			 * new WebContext(request, response, servletContext, request.getLocale());
			 * ctx.setVariable("errorMsg", "Incorrect username or password"); path =
			 * "/index.html"; templateEngine.process(path, ctx, response.getWriter());
			 */
		} else {
			
			
			PrintWriter writer = response.getWriter();
			writer.println("You are registered! ");
			writer.close();
			/*
			 * request.getSession().setAttribute("user", user); path =
			 * getServletContext().getContextPath() + "/Home"; response.sendRedirect(path);
			 */
		}

	}
}