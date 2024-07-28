package org.example.mvc.view.resolver;

import org.example.mvc.view.JspView;
import org.example.mvc.view.RedirectView;
import org.example.mvc.view.View;

import static org.example.mvc.view.RedirectView.DEFAULT_REDIRECT_PREFIX;

public class JspViewResolver implements ViewResolver{
    @Override
    public View resolveView(String viewName) {
        if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX))
            return new RedirectView(viewName);

        return new JspView(viewName + ".jsp");
    }
}
