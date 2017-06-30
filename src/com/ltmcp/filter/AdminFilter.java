package com.ltmcp.filter;

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

import com.ltmcp.comm.Comm;
import com.ltmcp.entity.Admin;

public class AdminFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		String url=req.getRequestURI();
		
		//°²×¿¶Ë
		if(url.indexOf("mobile")>0){
			chain.doFilter(request, response);
			return;
		}
		
		//String[] suffixs=new String[]{".js",".css",".3gp",".jpg",".mp4",".png",".htc",".swf",".gif",".bmp","login","image.jsp","doLogin",".ico","weixinTip-jq.html"};
		String[] suffixs=new String[]{".woff2",".woff",".ttf",".js",".css",".jpg",".png",".htc",".swf",".gif",".bmp","login","image.jsp","doLogin",".ico","weixinTip-ff.html","weixinTip-jq.html"};
		for(int i=0;i<suffixs.length;i++){
			if(url.endsWith(suffixs[i])){
				chain.doFilter(req, res);
				return;
			}
		}
		
		
		HttpSession session=req.getSession();
		Admin admin=(Admin) session.getAttribute(Comm.CURRENT_ADMIN);
		
		if(null==admin){
			res.sendRedirect(req.getContextPath()+"/login");
			return;
		}else{
			chain.doFilter(request, response);
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}
	
}
