package it.polimi.db2.pages;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.inject.Inject;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.polimi.db2.entities.Question;
import it.polimi.db2.entities.User;
import it.polimi.db2.services.ProductService;
import it.polimi.db2.services.QuestionnaireService;

@WebServlet("/questions")
@MultipartConfig
public class MarketingQuestions extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB(name = "it.polimi.db2.services/ProductService")
	private ProductService prodService;
	private boolean first = true;
	
	public MarketingQuestions() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		QuestionnaireService questService = null;
		questService = (QuestionnaireService) request.getSession().getAttribute("questService");
		
		var usr = (User) request.getAttribute("usr");
		var product = prodService.getTodayProduct();
		
		
		int prodIdToday = product.getId();
		int[] questionIds = null;
		int userId = usr.getId();
		
		List<Question> allQuestions = null;
		
		
		allQuestions = questService.findQuestionsOfTheProduct(prodIdToday);

		questService.createQuestionnaireLog(userId, prodIdToday);

		questionIds = new int[allQuestions.size()];
		for (int i = 0; i <= allQuestions.size() - 1; i++) {
			questionIds[i] = allQuestions.get(i).getId();
		}
		
		if(first) {
			first = false;
			request.setAttribute("marketingQuestions", allQuestions);
			request.getRequestDispatcher("/MarketingQuestionsPage.jsp").forward(request, response);
			return;
		}

		String answers[] = request.getParameterValues("answers");
		
		questService.marketingAnswers(questionIds, userId, answers);
		
		request.getRequestDispatcher("/StatisticQuestionsPage.jsp").forward(request, response);
	}
}
