package it.polimi.db2.pages;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.apache.commons.lang.StringEscapeUtils;

import it.polimi.db2.entities.Product;
import it.polimi.db2.exceptions.NotAvailableDateException;
import it.polimi.db2.services.ProductService;
import it.polimi.db2.services.QuestionService;
import it.polimi.db2.utils.ImageUtils;

/**
 * Servlet implementation class CreateQuestions
 */
@WebServlet("/questions/new")
@MultipartConfig
public class CreateQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
	@EJB(name = "it.polimi.db2.services/QuestionService")
	private QuestionService questionService;

    public CreateQuestions() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		request.getRequestDispatcher("/CreateQuestions.jsp" ).forward(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String prodId = request.getParameter("id");
		boolean error = false;
		int id = 0;
		
		String question = StringEscapeUtils.escapeHtml(request.getParameter("question"));
		if(question == null || question.length() < 1)
		{
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid question");
			return;
		}
		
		try {
			id = Integer.parseInt(prodId);
			if(id < 0) {
				error = true;
			}	
		} catch (NumberFormatException e)
		{
			error = true;
		}
		
		if (error) {
			request.setAttribute("message", "Invalid product id");
		}
		else {
			questionService.createQuestion(id, question);

			request.setAttribute("message", "Question created");
			request.setAttribute("success", true);
		}
		
		request.setAttribute("back_link", request.getContextPath() + "/dashboard");
		request.getRequestDispatcher("/ResultPage.jsp").forward(request, response);
	}

}
