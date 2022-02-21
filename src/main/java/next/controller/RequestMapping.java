package next.controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
	private static Map<String,Controller> requestMap = new HashMap<String,Controller>();
	
	static {
		requestMap.put("/user/login", new LoginController());
		requestMap.put("/user/logout", new LogoutController());
	}
	
	public static Controller getController(int url) {
		return requestMap.get(url); 
	}
}