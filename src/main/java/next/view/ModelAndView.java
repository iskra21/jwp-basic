package next.view;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import next.model.Answer;

public class ModelAndView {
	private View view;
	private Map<String,Object> model = new HashMap<String,Object>();

	public ModelAndView(View view) {
		this.view = view;
	}

	public View getView() {
		return view;
	}
	
	public Map<String,Object> getModel() {
		return Collections.unmodifiableMap(model);
	}

	public void addObject(String key, Object obj) {
		model.put(key, obj);
	}
	
}
