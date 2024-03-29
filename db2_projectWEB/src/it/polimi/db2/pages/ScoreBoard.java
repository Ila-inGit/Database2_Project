package it.polimi.db2.pages;

import java.io.IOException;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.polimi.db2.services.ScoreService;


/**
 * Servlet implementation class ScoreBoard
 */
@WebServlet("/scoreboard")
public class ScoreBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	
	@EJB(name = "it.polimi.db2.services/ScoreService")
	private ScoreService scoreService;
 
    public ScoreBoard() {
        super();
    }

    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if(scoreService!= null) {
			Map<String, String> scoreBoard = scoreService.createScoreBoard();
		
			String message = "Something goes wrong";
			String success = "That's strange, everything went right";
		
			if(scoreBoard != null) {
				request.setAttribute("scoreBoard", scoreBoard);
				request.setAttribute("success", success);
				request.getRequestDispatcher("/ScoreBoardPage.jsp").forward(request, response);
			}else {
				request.setAttribute("message", message);
				request.getRequestDispatcher("/ScoreBoardPage.jsp").forward(request, response);
			}
		}else {
			String message = "Something goes wrong";
			request.setAttribute("message", message);
			request.getRequestDispatcher("/ScoreBoardPage.jsp").forward(request, response);
		}
	}

}














