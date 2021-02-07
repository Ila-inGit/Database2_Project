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
import it.polimi.db2.entities.Product;
import it.polimi.db2.entities.Question;
import it.polimi.db2.entities.User;
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
		
		int prodIdToday = product.getId();
		int[] questionIds = null;
		int userId = usr.getId();
		
		List<Question> allQuestions = null;
		String[] answers = null;
		
		questService = (QuestionnaireService) request.getSession().getAttribute("questionnaireService");
		
		allQuestions = questService.findQuestionsOfTheProduct(prodIdToday);

		if (allQuestions != null) {
			questService.createQuestionnaireLog(userId, prodIdToday);
			
			questionIds = new int[allQuestions.size()];
			for(int i = 0; i <= allQuestions.size() - 1; i++) {
				questionIds[i] = allQuestions.get(i).getId();
			}

			request.setAttribute("marketingQuestions", allQuestions);
			request.getRequestDispatcher("/MarketingQuestionsPage.jsp").forward(request, response);
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "There are no questions");
		}

		answers = request.getParameterValues("answers");

		questService.marketingAnswers(questionIds, userId, answers);

	}
}
