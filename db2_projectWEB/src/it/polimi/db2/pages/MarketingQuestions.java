package it.polimi.db2.pages;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ServletContextTemplateResolver;

import it.polimi.db2.entities.Question;
import it.polimi.db2.services.QuestionnaireService;

@WebServlet("/Questionnaire")
@MultipartConfig
public class MarketingQuestions extends HttpServlet {
	//TODO da sistemare per jsp
	private static final long serialVersionUID = 1L;
	private TemplateEngine templateEngine;
	
	public MarketingQuestions() {
		super();
	}
	
	public void init() throws ServletException {
		ServletContext servletContext = getServletContext();
		ServletContextTemplateResolver templateResolver = new ServletContextTemplateResolver(servletContext);
		templateResolver.setTemplateMode(TemplateMode.HTML);
		this.templateEngine = new TemplateEngine();
		this.templateEngine.setTemplateResolver(templateResolver);
		templateResolver.setSuffix(".html");
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		//TODO ho bisogno dell'id del prodotto di oggi
		int prodIdToday = 0;
		
		String path = "/WEB-INF/MarketingQuestions.html";
		List<Question> allQuestions;
		QuestionnaireService questService = null;
		questService = (QuestionnaireService) request.getSession().getAttribute("questionnaireService");
		ServletContext servletContext = getServletContext();
		final WebContext webContext = new WebContext(request, response, servletContext, request.getLocale());
		allQuestions = questService.findQuestionsOfTheProduct(prodIdToday);
		webContext.setVariable("questions", allQuestions);
		webContext.setVariable("prodIdToday", prodIdToday);
		webContext.setVariable("currentPage", questService.getCurrentPage());
		templateEngine.process(path, webContext, response.getWriter());
	}
}
