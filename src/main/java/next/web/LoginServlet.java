package next.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;

import core.db.DataBase;
import next.model.User;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/user/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static final Logger log = LoggerFactory.getLogger(CreateUserServlet.class);

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		User user = DataBase.findUserById(req.getParameter("userId"));
		String password = req.getParameter("password");
		if(user != null && !Strings.isNullOrEmpty(password) && 
				password.equals(user.getPassword())) {
			HttpSession session = req.getSession();
			session.setAttribute("user", user);
			resp.sendRedirect("/index.jsp");
		} else {		
			resp.sendRedirect("/user/login_failed.html");
		}
	}

}
