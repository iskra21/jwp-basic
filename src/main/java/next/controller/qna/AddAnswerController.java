package next.controller.qna;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;
import next.view.JsonView;
import next.view.View;

public class AddAnswerController implements Controller {
	private static final Logger log =
			LoggerFactory.getLogger(AddAnswerController.class);
	
	@Override
	public View execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		Answer answer = new Answer(req.getParameter("writer"), 
				req.getParameter("contents"),
				Long.parseLong(req.getParameter("questionId")));
		log.debug("answer : {}", answer);
		
		AnswerDao answerDao = new AnswerDao();
		Answer savedAnswer = answerDao.insert(answer);
		req.setAttribute("answer", savedAnswer);
		return new JsonView();
	}

}
