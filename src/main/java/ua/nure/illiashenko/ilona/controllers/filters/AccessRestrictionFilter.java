package ua.nure.illiashenko.ilona.controllers.filters;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.illiashenko.ilona.dao.entities.Constraint;
import ua.nure.illiashenko.ilona.services.ReadXMLFile;
import ua.nure.illiashenko.ilona.utils.Base64Util;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.List;

import static ua.nure.illiashenko.ilona.constants.ContextConstants.BASE_64_UTIL;

public class AccessRestrictionFilter implements Filter {

    private final Logger logger = LoggerFactory.getLogger(AccessRestrictionFilter.class);
    private List<Constraint> constraints;
    private Base64Util base64Util;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String xmlFileName = filterConfig.getInitParameter("securityXmlFile");
        File securityXmlFile = new File(filterConfig.getServletContext().getRealPath("") + xmlFileName);
        ReadXMLFile readXMLFile = new ReadXMLFile();
        constraints = readXMLFile.readConstraints(securityXmlFile);
        base64Util = (Base64Util) filterConfig.getServletContext().getAttribute(BASE_64_UTIL);
        logger.info("init");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        if (isAccessAllowed(httpServletRequest, httpServletResponse)) {
            filterChain.doFilter(servletRequest, servletResponse);
        }
    }

    private boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String requestURI = request.getRequestURI();
        for (Constraint constraint : constraints) {
            if (constraint.getUrlPattern().matcher(requestURI).find()) {
                System.out.println(constraint.getUrlPattern());
                System.out.println(requestURI);
                String user = request.getHeader("Authorization");
                response.setHeader("Authorization", user);
                System.out.println("57: "+user);
                System.out.println(base64Util.decodeString(user));
                System.out.println(new JSONObject(base64Util.decodeString(user)).get("role"));
                if (user == null) {
                    response.setStatus(401);
                    return false;
                } else if (!constraint.getRoles().contains(new JSONObject(base64Util.decodeString(user)).get("role"))) {
                    response.setStatus(403);
                    return false;
                }
                break;
            }
        }
        return true;
    }

    @Override
    public void destroy() {
        logger.info("destroy");
    }
}
