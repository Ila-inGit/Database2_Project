package it.polimi.db2.pages;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;

import it.polimi.db2.services.QuestionnaireService;

@WebServlet("/StatisticQuestions")
public class StatisticQuestions extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	QuestionnaireService questService;
	
	public StatisticQuestions() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String userGender = null;
		int age = 0;
		String level = null;
		
		//TODO da sistemare
		int prodId = 0;
		int userId = 0;
		
		questService = (QuestionnaireService) request.getSession().getAttribute("questionnaireService");
		
		if(questService != null) {
			userGender = StringEscapeUtils.escapeJava(request.getParameter("gender"));
			age = Integer.parseInt(request.getParameter("age"));
			level = StringEscapeUtils.escapeJava(request.getParameter("gender"));
			
			questService.statisticAnswer(prodId, userId, userGender, age, level);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Questionnaire Service is not responding");
		}
	}
}
