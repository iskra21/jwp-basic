package next.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Servlet implementation class DispatcherServlet
 */
@WebServlet(name = "dispatcher", urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

	/**
	 * @see HttpServlet#service(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void service(HttpServletRequest request, 
			HttpServletResponse response) throws ServletException, IOException {
		log.debug("getRequestURI(): {}", request.getRequestURI());
		Controller controller = RequestMapping.getController(request.getRequestURI());
		String direction = "/";
		try {
			direction = controller.execute(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (direction.startsWith("redirect:")) {
			response.sendRedirect(direction.substring(9));
			return;
		}
		RequestDispatcher rd = request.getRequestDispatcher(direction);
		rd.forward(request, response);
	}

}
