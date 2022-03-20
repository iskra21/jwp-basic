package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.Question;
import next.view.JspView;
import next.view.ModelAndView;

public class AddQuestionController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Question question = new Question(req.getParameter("writer"), req.getParameter("title"),
				req.getParameter("contents"));
		QuestionDao qestionDao = new QuestionDao();
		qestionDao.insert(question);
        return new ModelAndView(new JspView("redirect:/"));
	}

}
