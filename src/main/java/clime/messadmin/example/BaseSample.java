/**
 * 
 */
package clime.messadmin.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Locale;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @author C&eacute;drik LIME
 */
abstract class BaseSample extends HttpServlet {

	/**
	 * Using Struts Locale location for demonstration purposes: {@value}
	 * @see org.apache.struts.Globals#LOCALE_KEY
	 */
	private static final String STRUTS_LOCALE_KEY = "org.apache.struts.action.LOCALE";//$NON-NLS-1$
	/**
	 * Maximum workload simulation time: {@value} ms.
	 */
	private static final long MAX_WORKLOAD_TIME = 400;

	/**
	 * @see javax.servlet.http.HttpServlet#HttpServlet()
	 */
	public BaseSample() {
		super();
	}
	
	/**
	 * {@inheritDoc}
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}
	
	/**
	 * {@inheritDoc}
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		process(request, response);
	}

	/**
	 * 
	 * @param req
	 * @param resp
	 * @throws IOException
	 * @throws ServletException
	 */
	protected abstract void process(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException;

	protected Locale getLocale(HttpServletRequest request) {
		return (Locale) request.getSession().getAttribute(STRUTS_LOCALE_KEY);
	}

	protected Long getWorkload(HttpServletRequest request) {
		return (Long) request.getAttribute("workload");//$NON-NLS-1$
	}

	/**
	 * @param req
	 * @param resp
	 */
	protected void doSomeWork(HttpServletRequest request, HttpServletResponse response) {
		// Create HttpSession and set Struts java.util.Locale
		HttpSession session = request.getSession(true);
		Locale locale = (Locale) session.getAttribute(STRUTS_LOCALE_KEY);
		if (null == locale) {
			Locale[] allLocales = Locale.getAvailableLocales();
			locale = allLocales[(int) (Math.random() * allLocales.length)];
			session.setAttribute(STRUTS_LOCALE_KEY, locale);
		}
		// simulate workload
		long workload = (long) (MAX_WORKLOAD_TIME * Math.random());
		try {
			Thread.sleep(workload);
		} catch (InterruptedException ise) {
			ise.printStackTrace();
		}
		request.setAttribute("workload", new Long(workload));//$NON-NLS-1$
		// set a non-serializable attribute at random
		if (Math.random() < 0.4) {
			Collection hiddenNonSerializableObject = new ArrayList(1);
			hiddenNonSerializableObject.add(NonSerializableObject.INSTANCE);
			session.setAttribute("Non-Serializable attr.", hiddenNonSerializableObject);
//		} else {
//			session.removeAttribute("Non-Serializable attr.");
		}
		response.setContentType("text/html");//$NON-NLS-1$
		setNoCache(response);
	}

	protected void setNoCache(HttpServletResponse request) {
		// <strong>NOTE</strong> - This header will be overridden
		// automatically if a <code>RequestDispatcher.forward()</code> call is
		// ultimately invoked.
		//request.setHeader("Pragma", "No-cache"); // HTTP 1.0 //$NON-NLS-1$ //$NON-NLS-2$
		request.setHeader("Cache-Control", "no-cache,no-store,max-age=0"); // HTTP 1.1 //$NON-NLS-1$ //$NON-NLS-2$
		request.setDateHeader("Expires", 0); // 0 means now //$NON-NLS-1$
		// should we decide to enable caching, here are the current vary:
		request.addHeader("Vary", "Accept-Language,Accept-Encoding,Accept-Charset");
	}

	/**
	 * This class is for demonstrating the flagging of HttpSessions with non-serializable objects attributes.
	 * It has no usefulness except that it's not Serializable.
	 * @author C&eacute;drik LIME
	 */
	private static class NonSerializableObject {
		private static final NonSerializableObject INSTANCE2 = new NonSerializableObject();
		public static final NonSerializableObject INSTANCE = new NonSerializableObject();
		static {
			INSTANCE.recursiveReference = INSTANCE;
			INSTANCE.cyclicReference = INSTANCE2;
			INSTANCE2.recursiveReference = INSTANCE2;
			INSTANCE2.cyclicReference = INSTANCE;
		}
		private NonSerializableObject cyclicReference;
		private NonSerializableObject recursiveReference;
	}
}
