package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.db.DataBase;
import next.model.User;

public class UpdateFormController implements Controller {

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter("userId");
        User user = DataBase.findUserById(userId);
        if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
            throw new IllegalStateException("You cannot update other user's information.");
        }
        req.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
        return "/user/updateForm.jsp";
	}

}
