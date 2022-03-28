package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Question;
import next.model.User;

public class UpdateFormQuestionController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(UpdateFormQuestionController.class);
	private QuestionDao questionDao = new QuestionDao();
	private UserDao userDao = new UserDao();

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Question question = questionDao.findById(Long.parseLong(req.getParameter("questionId")));
		User user = userDao.findByUserId(question.getWriter());
		if (UserSessionUtils.isSameUser(req.getSession(), user)) {
			return jspView("/qna/updateForm.jsp").addObject("question", question).addObject("username", user.getName());
		}
		return jspView("redirect:/");
	}

}
