package it.polimi.db2.pages;

import java.io.IOException;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;

import it.polimi.db2.entities.User;
import it.polimi.db2.services.ProductService;
import it.polimi.db2.services.QuestionnaireService;

@WebServlet("/statistic")
@MultipartConfig
public class StatisticQuestions extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB(name = "it.polimi.db2.services/ProductService")
	private ProductService prodService;
	
	public StatisticQuestions() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		QuestionnaireService questService = (QuestionnaireService) request.getSession().getAttribute("questService");
		
		String userGender = null;
		int age = 0;
		String level = null;
		var usr = (User) request.getAttribute("usr");
		var product = prodService.getTodayProduct();
		
		int prodId = product.getId();
		int userId = usr.getId();

		userGender = StringEscapeUtils.escapeHtml(request.getParameter("gender"));

		age = Integer.parseInt(request.getParameter("age"));
		if (age < 0) {
			request.setAttribute("message", "Invalid age");
		}

		level = StringEscapeUtils.escapeHtml(request.getParameter("expLvl"));

		questService.addStatisticAnswer(prodId, userId, userGender, age, level);

		//if (request.getParameter("Submit") != null) {
			questService.submit();
			
			request.setAttribute("message", "Thank you for submitting your questionnaire.");
			request.setAttribute("success", true);
			request.setAttribute("back_link", request.getContextPath() + "/product");
			request.getRequestDispatcher("/ResultPage.jsp").forward(request, response);
		//}	
	}
	
}
