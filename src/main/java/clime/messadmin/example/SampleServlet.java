package clime.messadmin.example;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import clime.messadmin.core.Constants;

/**
 * Sample Servlet which does nothing!
 * @author C&eacute;drik LIME
 */
public class SampleServlet extends BaseSample {

	/**
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public SampleServlet() {
		super();
	}	   

	/**
	 * {@inheritDoc}
	 */
	public void process(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		doSomeWork(req, resp);
		PrintWriter out = resp.getWriter();
		out.println("<html>");
		out.println("<head><title>Hello, world (Servlet version)!</title>");
		out.println("<body>");
		out.println("<h1>Hello, World (Servlet version)!</h1>");
		out.print("My Session Id: ");
		out.println(req.getSession().getId());
		out.println("<br />");
		out.print("My Locale: ");
		out.println(getLocale(req));
		out.println("<br />");
		out.print("Global attribute present: ");
		out.println(getServletContext().getAttribute(Constants.GLOBAL_MESSAGE_KEY) != null);
		out.println("<br />");
		out.print("Session attribute present: ");
		out.println(req.getSession().getAttribute(Constants.SESSION_MESSAGE_KEY) != null);
		out.println("<br />");
		out.print("Current simulated workload: ");
		out.print(getWorkload(req));
		out.println(" ms");
		out.println("</body></html>");
	}
}