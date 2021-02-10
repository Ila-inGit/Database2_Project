package it.polimi.db2.pages;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import it.polimi.db2.services.QuestionnaireService;

/**
 * Servlet implementation class LogOut
 */
@WebServlet("/logout")
public class LogOut extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		
		HttpSession session = request.getSession(false);
		
		if(session != null) {
			QuestionnaireService qs = (QuestionnaireService) session.getAttribute("questService");
			if (qs != null) qs.remove();
			session.invalidate();
		}
		
		response.sendRedirect(request.getContextPath()); // redirect to home page	
		
	}
}
