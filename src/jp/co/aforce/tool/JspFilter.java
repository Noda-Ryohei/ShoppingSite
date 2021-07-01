package jp.co.aforce.tool;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletResponse;

public class JspFilter implements Filter {

	public void doFilter(
			ServletRequest request, ServletResponse response, FilterChain chain) {
		try {
			
			((HttpServletResponse) response).sendRedirect("/ShoppingSite/logout");
			return;

		} catch (Exception e) {

		}

	}

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void destroy() {
	}

}
