package it.polimi.db2.pages;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.polimi.db2.services.QuestionService;

@WebServlet("/questions/delete")
public class DeleteQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@EJB(name = "it.polimi.db2.services/QuestionService")
	private QuestionService questionService;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String prodId = request.getParameter("id"); //TODO getParameter questionId
		boolean err = false;
		
		try {
			
			int id = Integer.parseInt(prodId);
			if(id < 0 || !questionService.deleteAllQuestions(id)) {
				err = true;
			}
			
				
		} catch (NumberFormatException e)
		{
			err = true;
		}
		
		if(err) {
			request.setAttribute("message", "Invalid product id");
		} else {
			request.setAttribute("success", true);
			request.setAttribute("message", "Questionnaire removed successfully");
		}
		
		
		request.setAttribute("back_link", request.getContextPath()+"/dashboard");
		request.getRequestDispatcher("/ResultPage.jsp").forward(request, response);
	}


}
