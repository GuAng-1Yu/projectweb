package com.iscc.propertymanagent.filter;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("/*")
public class LoginFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;

        String uri = request.getRequestURI();

        if (uri.endsWith("/loginnew.html") || uri.endsWith("/stafflogin.do") || uri.endsWith("/login.do")
                || uri.endsWith(".css") || uri.endsWith(".js") || uri.endsWith(".png") || uri.endsWith(".gif")
                || uri.endsWith("/AuthCodeServlet")) {
//            favicon.ico AuthCodeServlet
            chain.doFilter(request, response);
        } else {
            HttpSession session = request.getSession();
            if (session.getAttribute("staff") == null) {
                response.sendRedirect("loginnew.html");
            } else {
                chain.doFilter(request, response);
            }
        }
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
