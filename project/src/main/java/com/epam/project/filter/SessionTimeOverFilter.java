package com.epam.project.filter;

import com.epam.project.resource.ConfigurationManager;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(filterName = "session", urlPatterns = {"/*"})
public class SessionTimeOverFilter implements Filter{

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpSession session = request.getSession(false);
        if(session != null && !session.isNew()) {
            chain.doFilter(servletRequest, servletResponse);
        } else {
            request.getRequestDispatcher(ConfigurationManager.getProperty("path.page.index"))
                    .forward(servletRequest,servletResponse);
        }
    }
}
