package it.polimi.db2.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import it.polimi.db2.entities.User;

public class UserSessionUtils {

	/**
	 * Return current session user, null if none present
	 */
	public static User getSessionUser(HttpServletRequest request) {
		HttpSession session = request.getSession(false);
		
		if(session == null) {
			return null;
		}
			
		return (User) request.getSession().getAttribute("user");
		
	}
}
