package jp.co.aforce.tool;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class AdminFilter implements Filter {
	
	public void doFilter(
			ServletRequest request, ServletResponse response, FilterChain chain) {
		
		try {
			String target = ((HttpServletRequest) request).getRequestURI();
			
			HttpSession session = ((HttpServletRequest) request).getSession(false);
			
			if (session == null) {
				//まだ認証されていない
				session = ((HttpServletRequest) request).getSession(true);
				session.setAttribute("target", target);
				
				((HttpServletResponse) response).sendRedirect("/ShoppingSite/login");
				
				return;
				
			} else {
				Object loginCheck = session.getAttribute("login");
				if ( loginCheck == null || !loginCheck.equals("admin") ) {
					//まだ認証されていない
					session.setAttribute("login", null);
					session.setAttribute("target", target);
					((HttpServletResponse) response).sendRedirect("/ShoppingSite/login");
					return;
					
				} 
				
			}
			
			chain.doFilter(request, response);
			
		} catch (Exception e) {
		}
	}
	
	public void init(FilterConfig filterConfig) throws ServletException {
	}
	
	public void destroy() {
	}

}
