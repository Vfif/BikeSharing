package com.epam.project.filter;

import com.epam.project.resource.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebFilter(filterName = "referer", urlPatterns = {"/*"})
public class CurrentPageFilter implements Filter {
    private static final String REFERER = "referer";

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpSession session = request.getSession(true);
        String url = request.getHeader(REFERER);
        if(url == null) request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.error"))
                .forward(request,resp);
        else chain.doFilter(req,resp);
        /*String path = substringPathWithRegex(url);
        session.setAttribute("current page", path);
        chain.doFilter(req, resp);*/
    }
}
