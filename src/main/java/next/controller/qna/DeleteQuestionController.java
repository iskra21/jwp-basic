package next.controller.qna;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import next.controller.UserSessionUtils;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.dao.UserDao;
import next.model.Answer;
import next.model.Question;
import next.model.User;

public class DeleteQuestionController extends AbstractController {
	QuestionDao questionDao = new QuestionDao();
	AnswerDao answerDao = new AnswerDao();
	UserDao userDao = new UserDao();

	@Override
	public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 퀘스천 가져오기
		Long questionId = Long.parseLong(req.getParameter("questionId"));
		Question question = questionDao.findById(questionId);
		User owner = userDao.findByUserId(question.getWriter());
		
		// 로그인정보와 퀘스천의 소유자가 다르면 돌아간다.
		if (!UserSessionUtils.isSameUser(req.getSession(), owner)) {
			throw new IllegalStateException("You cannot delete other user's article!");
		}
		
		// 로그인정보 같음: 댓글이 0개이면 삭제하고 돌아간다.
		if (question.getCountOfComment() == 0) {
			questionDao.delete(questionId);
			return jspView("redirect:/");
		}
		
		// 로그인정보 같은 + 댓글이 1개 이상: 모든 댓글을 가져온다. 댓글의 소유자와 원글의 소유자를 비교한다.
		// 하나라도 다르면 삭제하지 않고 돌아간다. 같으면 모든 댓글을 삭제하고 질문도 삭제한다.
		List<Answer> answers = answerDao.findAllByQuestionId(questionId);
		for (Answer answer : answers) {
			if (answer.getWriter() != question.getWriter()) {
				throw new IllegalStateException("You cannot delete the article with other user's comment(s)!");
			}
		}
		
		for (Answer answer : answers) {
			answerDao.delete(answer.getAnswerId());
		}
		questionDao.delete(questionId);
		return jspView("redirect:/");
	}

}
