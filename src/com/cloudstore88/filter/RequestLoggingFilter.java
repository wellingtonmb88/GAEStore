package com.cloudstore88.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet Filter implementation class RequestLoggingFilter
 */ 
public class RequestLoggingFilter implements Filter {

    /**
     * Default constructor. 
     */
    public RequestLoggingFilter() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
		
		HttpSession session = req.getSession(false);

		if(session != null){
 			
			if(session.getAttribute("usuarioNome") == null){

				res.sendRedirect("login.html");
			}else{
				
				//Limpa dados do cache para evitar que acesse a session atraves do back button.
				res.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
		        res.setHeader("Pragma", "no-cache"); // HTTP 1.0.
		        res.setDateHeader("Expires", 0);
				// pass the request along the filter chain
				chain.doFilter(request, response);
			}
			
		} 

	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
