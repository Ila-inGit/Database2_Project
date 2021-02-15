package it.polimi.db2.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.tuple.ImmutablePair;

import it.polimi.db2.entities.Answer;
import it.polimi.db2.entities.Question;
import it.polimi.db2.entities.User;
import it.polimi.db2.exceptions.InvalidDataException;
import it.polimi.db2.services.ProductService;
import it.polimi.db2.services.QuestionnaireService;
import it.polimi.db2.services.WordFilterService;

@WebServlet("/questions")
@MultipartConfig
public class MarketingQuestions extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	@EJB(name = "it.polimi.db2.services/ProductService")
	private ProductService prodService;
	
	@EJB(name = "it.polimi.db2.services/WordFilterService")
	private WordFilterService wFilter;
	
	public MarketingQuestions() {
		super();
	}
	
	
	private int[] getTodayProductQuestionIds()
	{
		var questions = prodService.getTodayProduct().getQuestions();
		
		int[] ids = new int[questions.size()];
		for(int i =0; i < questions.size(); i++ )
			ids[i] = questions.get(i).getId();
		
		
		return ids;
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		QuestionnaireService questService  = (QuestionnaireService) request.getSession().getAttribute("questService");
		
		//TODO: check double insert

		if(questService != null)
		{
			
			// use get parameter to customize navigation in the questionnaire
			String action = request.getParameter("action");
			if(action != null)
			{
				// back resets the progress of the questionnaire
				if(action.equals("back"))
				{
					questService.setDisplayStatisticsForm(false);
				}
				
				//abort clears the current questionnaire progress and redirects to home page
				if(action.equals("abort"))
				{
					questService.clearAnswers();
					response.sendRedirect(request.getContextPath());
					return;
				}
			}
			
		    // display page
			
			if(questService.shouldDisplayStatisticsForm())
			{
				request.getRequestDispatcher("/StatisticQuestionsPage.jsp").forward(request, response);
			}
			else
			{

				// open new questionnaire
				if(questService.getMarketingAnswers().size() <= 0)
				{
					var usr = (User) request.getAttribute("usr");
					questService.createQuestionnaireLog(usr.getId(), prodService.getTodayProduct().getId());
				}
				
				// generate complete pairs used to prefill the page
				List<ImmutablePair<Question, String>> questions = new ArrayList<ImmutablePair<Question, String>>();
				
				var qst = prodService.getTodayProduct().getQuestions();
				var answ = questService.getMarketingAnswers();
			
				for(int i =0; i<qst.size(); i++)
				{
					questions.add(new ImmutablePair<Question, String>(qst.get(i), answ.size() > i ? answ.get(i).getBody() : ""));
				}
		
				
				request.setAttribute("marketingQuestions", questions);
				request.getRequestDispatcher("/MarketingQuestionsPage.jsp").forward(request, response);
			}
		}
		else
		{
			// handle error
			request.setAttribute("message", "Something went wrong while fetching the questionnaire, please retry later!");
			request.setAttribute("back_link", request.getContextPath());
			request.getRequestDispatcher("/ResultPage.jsp").forward(request, response);
		}
		
	
	}

	
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// common data
		QuestionnaireService questService  = (QuestionnaireService) request.getSession().getAttribute("questService");
		var usr = (User) request.getAttribute("usr");
		
		if(questService == null)
		{
			request.setAttribute("message", "Something went wrong, please retry later!");
			request.setAttribute("back_link", request.getContextPath());
			request.getRequestDispatcher("/ResultPage.jsp").forward(request, response);
			return;
		}
		
		if(!questService.shouldDisplayStatisticsForm())
		{
		
			// handle post of marketing questions answers
			String answers[] = request.getParameterValues("answers");
			
			if(answers != null)
			{
				var questionIds = getTodayProductQuestionIds();
			
				if(answers.length == questionIds.length)
				{		
					//delete junk data of multiple submits
					questService.clearAnswers();
					
					// add questions to statefull
					questService.addMarketingAnswers(questionIds, usr.getId(), answers);
					// send marketing page
					request.getRequestDispatcher("/StatisticQuestionsPage.jsp").forward(request, response);
				}
				else
				{
					// submit error here
					request.setAttribute("message", "Something went wrong while saving your answers, please retry later!");
					request.setAttribute("back_link", request.getContextPath());
					request.getRequestDispatcher("/ResultPage.jsp").forward(request, response);
				}
				
			}
			else
			{
				request.setAttribute("message", "Please answer to proposed questions!");
				request.setAttribute("back_link", request.getContextPath()+"/questions");
				request.getRequestDispatcher("/ResultPage.jsp").forward(request, response);
			}
		}
		else
		{		
		   //handle post of marketing questions 		
			String genderStr =  StringEscapeUtils.escapeHtml(request.getParameter("gender"));
			String ageStr = StringEscapeUtils.escapeHtml(request.getParameter("age"));
			String levelStr =  StringEscapeUtils.escapeHtml(request.getParameter("expLvl"));
				
			
			// valid statistical answer
			// parse and submit it
			if(genderStr != null || ageStr != null || levelStr != null)
			{
				try
				{
					questService.addStatisticAnswer(prodService.getTodayProduct().getId(), usr.getId(), genderStr, ageStr, levelStr);
				}
				catch(InvalidDataException e)
				{
					request.setAttribute("message", e.getMessage());
					request.setAttribute("back_link", request.getContextPath()+"/questions");
					request.getRequestDispatcher("/ResultPage.jsp").forward(request, response);
					return;
				}
				
							
			}
			
			var answ = questService.getMarketingAnswers();
			boolean badWord = false;
			for (Answer an : answ) {
				badWord = wFilter.ContainsNotAllowedWord(an.getBody());
				if (badWord) {
					
					questService.blockAccount();
					
					request.setAttribute("message", "Your account has been blocked because you have used a not allowed word");
					request.setAttribute("back_link", request.getContextPath()+"/logout");
					request.getRequestDispatcher("/ResultPage.jsp").forward(request, response);
					return;
				}
			}
			
			questService.submit();
			
			request.setAttribute("message", "Thank you for your time!");
			request.setAttribute("success", true);
			request.setAttribute("back_link", request.getContextPath());
			request.getRequestDispatcher("/ResultPage.jsp").forward(request, response);
			
		}
		
	}
	
	
	
	
}
