package com.beyzatopcu.todolist.config;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;

@Component
public class CustomFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		res.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Allow-Methods", "POST,GET,OPTIONS,DELETE,PUT");		
		res.setHeader("Access-Control-Allow-Headers", "Content-Type, Access-Control-Allow-Headers, Access-Control-Allow-Origin");
		res.setHeader("Access-Control-Max-Age", "3600");
		res.setHeader("Access-Control-Allow-Credentials", "true");
		res.setHeader("Access-Control-Expose-Headers", "Authorization");
		res.addHeader("Access-Control-Expose-Headers", "responseType");
		res.addHeader("Access-Control-Expose-Headers", "observe");
		res.setStatus(HttpServletResponse.SC_OK);

		chain.doFilter(req, res);
		System.out.println(">>>>><<<<<filter");
		
	}

}
