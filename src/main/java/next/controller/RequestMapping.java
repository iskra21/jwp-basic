package next.controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
	private static Map<String,Controller> controllerMap = new HashMap<String,Controller>();
	
	static {
		controllerMap.put("/user/loginForm", controllerMap.put("/user/login", new LoginController()));
		controllerMap.put("/user/logout", new LogoutController());
	}
	
	public static Controller getController(String url) {
		return controllerMap.get(url); 
	}
}