package next.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ForwardController implements Controller {
	private String uri;

	public ForwardController(String uri) {
		this.uri = uri;
	}
	
	@Override
	public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		return this.uri;
	}

}
