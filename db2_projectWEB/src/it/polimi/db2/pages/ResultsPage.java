package it.polimi.db2.pages;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.polimi.db2.entities.Answer;
import it.polimi.db2.entities.Product;
import it.polimi.db2.entities.Question;
import it.polimi.db2.entities.User;
import it.polimi.db2.services.ProductService;

@WebServlet("/questions/results")
public class ResultsPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	@EJB(name = "it.polimi.db2.services/ProductService")
	private ProductService prodService;
	
    public ResultsPage() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		List<Question> allQuestions = new ArrayList<>();
		String pid = request.getParameter("id");
		int prodId = -1;
		
		boolean err = false;
		
		if(pid == null)
		{
			err = true;
		}
		
		try
		{
			prodId = Integer.parseInt(pid);
		} catch(NumberFormatException e)
		{
			err = true;
		}
		
		/*mi devo prendere tutte le domande del questionario*/ 
		Product prod = prodService.getProductById(prodId);
		if(prod == null)
		{
			err = true;
		}
		
		if(err)
		{
			request.setAttribute("message", "Wrong product id");
			request.setAttribute("back_link", request.getContextPath());
			request.getRequestDispatcher("/ResultPage.jsp").forward(request, response);
			return;
		}
		
		allQuestions = prod.getQuestions();
		
		request.setAttribute("product", prod);
		request.setAttribute("questions", allQuestions);
		
		
		//mi devo prendere tutte le risposte per ciascuna domanda 
		Map<Integer,List<Answer>> questAnswers = new HashMap<>();
	
		
		for(var q: allQuestions) 
		{ 
			questAnswers.put(q.getId(), q.getAnswers());
		}
		
		request.setAttribute("questAnswers", questAnswers);
		
		//mi devo prendere tutti gli user che hanno risposto al questionario
		
		
		Map<User, Boolean> users = prodService.getUsersQuestionnaire(prodId);
		
		request.setAttribute("users", users);
		
		
		request.getRequestDispatcher("/QuestResults.jsp" ).forward(request, response);
	}
	
}
