package core.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import next.view.View;

public interface Controller {
    View execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
