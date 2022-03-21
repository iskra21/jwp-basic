package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import core.mvc.JspView;
import core.mvc.ModelAndView;
import next.dao.QuestionDao;
import next.model.Question;

public class CreateQuestionController implements Controller {

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		QuestionDao questionDao = new QuestionDao();
		questionDao.insert(new Question(req.getParameter("writer"), req.getParameter("title"), 
				req.getParameter("contents")));
		return new ModelAndView(new JspView("redirect:/"));
		
	}

}
