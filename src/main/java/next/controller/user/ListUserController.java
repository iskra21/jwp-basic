package next.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.controller.UserSessionUtils;
import next.dao.UserDao;
import next.view.JspView;
import next.view.ModelAndView;
import next.view.View;

public class ListUserController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (!UserSessionUtils.isLogined(req.getSession())) {
            return new ModelAndView(new JspView("redirect:/users/loginForm"));
        }

        ModelAndView mav = new ModelAndView(new JspView("/user/list.jsp"));
        UserDao userDao = new UserDao();
        mav.addObject("users", userDao.findAll());
        return mav;
    }
}
