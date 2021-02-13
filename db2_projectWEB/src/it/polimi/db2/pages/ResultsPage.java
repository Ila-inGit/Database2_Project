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

@WebServlet("/results")
public class ResultsPage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	@EJB(name = "it.polimi.db2.services/ProductService")
	private ProductService prodService;
	
    public ResultsPage() {
        super();
        
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setAttribute("prodId", 3);
		
		List<Question> allQuestions = new ArrayList<>();
		int prodId = (int) request.getAttribute("prodId");
		
		/*mi devo prendere tutte le domande del questionario*/ 
		Product prod = prodService.getProductById(prodId);
		allQuestions = prod.getQuestions();
		
		request.setAttribute("questions", allQuestions);
		
		
		//mi devo prendere tutte le risposte per ciascuna domanda 
		Map<Integer,List<Answer>> questAnswers = new HashMap<>();
	
		
		allQuestions.stream().forEach((q) -> { 
			
			List<Answer> allAnswers = prodService.getResultForQuestion(q.getId(), prodId);
			//stampo usr e risposta associata per ogni domanda 
				questAnswers.put(q.getId(),allAnswers);
			}
		);
		
		request.setAttribute("questAnswers", questAnswers);
		
		/*mi devo prendere tutti gli user che hanno risposto al questionario*/
		
		List<Answer> value = questAnswers.values().iterator().next();
		
		List<String> users = new ArrayList<String>();
		
		for(Answer a: value) {
			users.add(a.getUser().getUserName());
		}
		
		//devo stampare gli user di una domanda solo perchè tanto le devono rispondere a tutte obbligatoriamente
		request.setAttribute("users", users);
	
	
		//recupera le cose dal prodService che ha i questionnaire log del prodotto
		
		List<User> deletedUsers = prodService.getUsersOfDeletedQuestionnaire(prodId);
		
		request.setAttribute("deletedUsers", deletedUsers);
		
		request.getRequestDispatcher("/QuestResults.jsp" ).forward(request, response);
	}
	
}
