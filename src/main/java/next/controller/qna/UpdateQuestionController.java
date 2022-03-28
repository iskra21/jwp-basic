package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Question;

public class UpdateQuestionController extends AbstractController {
    private static final Logger log = LoggerFactory.getLogger(UpdateQuestionController.class);
	private QuestionDao questionDao = new QuestionDao();
	
	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Question question = questionDao.findById(Long.parseLong(req.getParameter("questionId")));
		questionDao.update(new Question(question.getQuestionId(), question.getWriter(),
				req.getParameter("title"), req.getParameter("contents"),
				question.getCreatedDate(), question.getCountOfComment()));
		return jspView("redirect:/");
	}

}
