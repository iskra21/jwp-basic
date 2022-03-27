package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Question;

public class UpdateFormQuestionController extends AbstractController {
	private QuestionDao questionDao = new QuestionDao();
	private UserDao userDao = new UserDao();

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Question question = questionDao.findById(Long.parseLong(req.getParameter("questionId")));
		if (UserSessionUtils.isSameUser(req.getSession(), userDao.findByUserId(question.getWriter()))) {
			return jspView("/qna/updataForm.jsp").addObject("question", question);
		}
		return jspView("redirect:/users/login");
	}

}
