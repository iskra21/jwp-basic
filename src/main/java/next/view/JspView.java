package next.view;

import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class JspView implements View {
    private static final String DEFAULT_REDIRECT_PREFIX = "redirect:";
	private String url;
	
	public JspView(String url) {
		this.url = url;
	}

	@Override
	public void render(Map<String,?> model, HttpServletRequest req, HttpServletResponse resp) throws Exception {
		if (url.startsWith(DEFAULT_REDIRECT_PREFIX)) {
			resp.sendRedirect(url.substring(DEFAULT_REDIRECT_PREFIX.length()));
			return;
		}
		
		Set<String> keySet = model.keySet();
		for (String key : keySet) {
			req.setAttribute(key, model.get(key));
		}

		RequestDispatcher rd = req.getRequestDispatcher(url);
		rd.forward(req, resp);
	}

}
