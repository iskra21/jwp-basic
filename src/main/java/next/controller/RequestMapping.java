package next.controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
	private static Map<String,Controller> controllerMap = new HashMap<String,Controller>();
	
	static {
		controllerMap.put("/", new HomeController());
		controllerMap.put("/user/create", new CreateUserController());
		controllerMap.put("/user/login", new LoginController());
		controllerMap.put("/user/logout", new LogoutController());
		controllerMap.put("/user/list", new ListUserController());
		controllerMap.put("/user/profile", new ProfileController());
		controllerMap.put("/user/update", new UpdateUserController());
		controllerMap.put("/user/updateForm", new UpdateFormController());
	}
	
	public static Controller getController(String url) {
		return controllerMap.get(url); 
	}
}