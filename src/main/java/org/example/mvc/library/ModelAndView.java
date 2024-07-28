package org.example.mvc.library;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    private final Object view;
    private Map<String, ?> model = new HashMap<>();

    public ModelAndView(String viewName) {
        this.view = viewName;
    }

    public Map<String, ?> getModel() {
        return Collections.unmodifiableMap(model);
    }

    public Object getView() {
        return view;
    }

    public String getViewName() {
        return (this.view instanceof String ? (String) view : null);
    }
}
