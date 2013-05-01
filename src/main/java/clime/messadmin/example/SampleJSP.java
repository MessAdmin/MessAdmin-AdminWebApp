package clime.messadmin.example;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Sample Servlet which does nothing!
 * @author C&eacute;drik LIME
 */
public class SampleJSP extends BaseSample {

	/**
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public SampleJSP() {
		super();
	}	   
	
	/**
	 * {@inheritDoc}
	 */
	protected void process(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
		doSomeWork(req, resp);
		if (req.getRequestURI().indexOf("forward") != -1) { //$NON-NLS-1$
			req.getRequestDispatcher("/test.jsp").forward(req, resp); //$NON-NLS-1$
		} else if (req.getRequestURI().indexOf("include") != -1) { //$NON-NLS-1$
			req.getRequestDispatcher("/test.jsp").include(req, resp); //$NON-NLS-1$
		} else {
			throw new IllegalArgumentException(req.getRequestURI());
		}
	}
}