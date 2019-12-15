package com.epam.project.filter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*@WebFilter(filterName = "uri", urlPatterns = "/*")*/
public class UriFilter implements Filter {
    private static final Logger LOGGER = LogManager.getLogger();

   //Index page command value.
    private static final String INDEX_PAGE_COMMAND_VALUE = "index";

    //URI regular expression.
    private static final String URI_REGEX = "((\\/[\\w\\-]*)+)(\\/)?(\\?[a-zA-Z\\d]+\\=[\\w\\-]*)?(\\&[a-zA-z\\d]+\\=[\\w\\-]*)?$";

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletResponse response = (HttpServletResponse) resp;
        HttpServletRequest request = (HttpServletRequest) req;

        String urlQuery = String.valueOf(req.getAttribute("urlQuery"));

        if(urlQuery.startsWith("/assets")) {
            chain.doFilter(req, resp);
            return;
        }

        if (urlQuery.matches(URI_REGEX)) {
            String uri = request.getRequestURI();

            uri = (uri.length() == 1 && uri.startsWith("/")) ? INDEX_PAGE_COMMAND_VALUE :
                    uri.substring(1, uri.length()).replace('/', '.').toLowerCase();

            LOGGER.debug("urlQuery: " + urlQuery);
            LOGGER.debug( "Command uri: " + uri);

            req.setAttribute("command", uri);

            LOGGER.debug("UriFilter has worked.");
            chain.doFilter(req, resp);
        } else {
            response.sendError(404);
        }
    }
}
