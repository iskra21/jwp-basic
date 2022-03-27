package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.model.User;

public class QuestionFormController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		User user = UserSessionUtils.getUserFromSession(req.getSession());
		if (user != null) {
			ModelAndView mav = new ModelAndView(new JspView("/qna/form.jsp"));
			mav.addObject("user", user);
			return mav;
		}
		return new ModelAndView(new JspView("redirect:/"));
	}

}