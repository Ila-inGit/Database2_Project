package it.polimi.db2.pages;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringEscapeUtils;

import it.polimi.db2.entities.Answer;
import it.polimi.db2.entities.Product;
import it.polimi.db2.entities.Question;
import it.polimi.db2.entities.User;
import it.polimi.db2.services.ProductService;
import it.polimi.db2.services.QuestionnaireService;

@WebServlet("/questions")
@MultipartConfig
public class MarketingQuestions extends HttpServlet {
	
	@EJB(name = "it.polimi.db2.services/QuestionnaireService")
	private static final long serialVersionUID = 1L;
	private QuestionnaireService questService;
	
	public MarketingQuestions() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		var usr = (User) request.getAttribute("usr");
		var product = (Product) request.getAttribute("product");
		
		//TODO da sistemare
		int prodIdToday = product.getId();
		int questionId = 0;
		int userId = usr.getId();
		
		List<Question> allQuestions = null;
		String answer = null;
		
		questService = (QuestionnaireService) request.getSession().getAttribute("questionnaireService");
		
		if(questService != null) {
			allQuestions = questService.findQuestionsOfTheProduct(prodIdToday);

			if (allQuestions != null) {
				request.setAttribute("marketingQuestions", allQuestions);
				request.getRequestDispatcher("/MarketingQuestionsPage.jsp").forward(request, response);
			} else {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST, "There are no questions");
			}

			answer = StringEscapeUtils.escapeJava(request.getParameter("answer"));

			if (answer == null || answer.isBlank()) {
				String message = "Missing answer";
				request.setAttribute("message", message);
				request.getRequestDispatcher("/MarketingQuestions.jsp").forward(request, response);
				return;
			}
			
			questService.marketingAnswers(questionId, userId, answer);
			
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Questionnaire Service is not responding");
		}
	}
}
