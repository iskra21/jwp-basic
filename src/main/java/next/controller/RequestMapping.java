package next.controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
	private static Map<String,Controller> controllerMap = new HashMap<String,Controller>();
	
	static {
		controllerMap.put("/", new HomeController());
		controllerMap.put("/users/create", new CreateUserController());
		controllerMap.put("/user/form.jsp", controllerMap.put("/user/login.jsp", new ForwardController()));
		controllerMap.put("/users/login", new LoginController());
		controllerMap.put("/users/logout", new LogoutController());
		controllerMap.put("/users", new ListUserController());
		controllerMap.put("/users/profile", new ProfileController());
		controllerMap.put("/users/update", new UpdateUserController());
		controllerMap.put("/users/updateForm", new UpdateFormController());
	}
	
	public static Controller getController(String url) {
		return controllerMap.get(url); 
	}
}