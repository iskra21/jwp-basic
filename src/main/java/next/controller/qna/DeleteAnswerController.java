package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Result;
import next.view.JsonView;
import next.view.ModelAndView;
import next.view.View;

public class DeleteAnswerController implements Controller {
	private static final Logger log =
			LoggerFactory.getLogger(DeleteAnswerController.class);

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		AnswerDao answerDao = new AnswerDao();
		Long answerId = Long.parseLong(req.getParameter("answerId"));

		answerDao.delete(answerId);
		ModelAndView mav = new ModelAndView(new JsonView());
		mav.addObject("result", Result.ok());
		
		return mav;
	}

}
