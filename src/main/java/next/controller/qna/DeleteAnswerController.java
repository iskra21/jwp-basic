package next.controller.qna;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.model.Answer;
import next.model.Result;

public class DeleteAnswerController implements Controller {
	private static final Logger log =
			LoggerFactory.getLogger(DeleteAnswerController.class);

	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		AnswerDao answerDao = new AnswerDao();
		Long answerId = Long.parseLong(req.getParameter("answerId"));
		
		answerDao.delete(answerId);
		
		ObjectMapper mapper = new ObjectMapper();
		resp.setContentType("application/json;charset=UTF-8");
		PrintWriter out = resp.getWriter();
		out.print(mapper.writeValueAsString(Result.ok()));
		return null;
	}

}
