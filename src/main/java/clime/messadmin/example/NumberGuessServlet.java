package clime.messadmin.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Sample Servlet which does nothing!
 * @author C&eacute;drik LIME
 */
public class NumberGuessServlet extends BaseSample {
	protected int maxNumber = 10;

	/**
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public NumberGuessServlet() {
		super();
	}

	/**
	 * {@inheritDoc}
	 */
	public void init() throws ServletException {
		super.init();
		String maxNumberStr = getServletContext().getInitParameter("numberGuess.maxNumber");
		if (maxNumberStr != null) {
			try {
				maxNumber = Integer.parseInt(maxNumberStr);
			} catch (NumberFormatException nfe) {
				log("Bad init parameter 'numberGuess.maxNumber':", nfe);
			}
		}
		getServletContext().setAttribute("maxNumber", new Integer(maxNumber));
	}

	/**
	 * {@inheritDoc}
	 */
	public void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doSomeWork(request, response);
		HttpSession session = request.getSession();
		Integer nTries = (Integer) session.getAttribute("nTries");
		Integer numberToGuess = (Integer) session.getAttribute("numberToGuess");
		String guessedNumberStr = request.getParameter("number");
		String message = "";
		if (nTries == null || numberToGuess == null) {
			// new game
			nTries = new Integer(0);
			numberToGuess = new Integer((int)(Math.random()*maxNumber)+1);
			session.setAttribute("numberToGuess", numberToGuess);
			message = "";
		} else {
			// existing game
			if (guessedNumberStr != null && !"".equals(guessedNumberStr.trim())) {
				nTries = new Integer(nTries.intValue() + 1);
				try {
					int guessedNumber = Integer.parseInt(guessedNumberStr.trim());
					if (guessedNumber < numberToGuess.intValue()) {
						message = ""+guessedNumber + ": too low!";
					} else if (guessedNumber > numberToGuess.intValue()) {
						message = ""+guessedNumber + ": too high!";
					} else { // ==
						message = "Congratulations, you won in " + nTries.intValue() + " tries!";
						session.removeAttribute("numberToGuess");
						session.removeAttribute("nTries");
					}
				} catch (NumberFormatException nfe) {
					message = nfe.toString();
				}
			}
		}
		session.setAttribute("nTries", nTries);
		request.setAttribute("message", message);
		if (getServletContext().getAttribute("maxNumber") == null) {
			// fix JSP display it someone played with admin webapp and removed it... :-)
			getServletContext().setAttribute("maxNumber", new Integer(maxNumber));
		}
		request.getRequestDispatcher("/numberGuess.jsp").forward(request, response); //$NON-NLS-1$
	}
}
