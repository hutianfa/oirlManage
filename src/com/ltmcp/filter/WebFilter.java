package com.ltmcp.filter;

import java.io.IOException;
import java.util.List;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.ltmcp.comm.Comm;
import com.ltmcp.entity.Power;

public class WebFilter implements Filter{

	@Override
	public void destroy() {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest req=(HttpServletRequest) request;
		HttpServletResponse res=(HttpServletResponse) response;
		
		String url=req.getRequestURI();
		//安卓端
				if(url.indexOf("mobile")>0){
					chain.doFilter(request, response);
					return;
				}
		
		//请求系统资源直接通过
		//String[] suffixs=new String[]{".js",".css",".jpg",".png",".htc",
									//".swf",".gif",".bmp","login","image.jsp","doLogin",".ico","weixinTip-jq.html",".txt",".mp4",".woff",".ttf",".3gp"};
				String[] suffixs=new String[]{".woff2",".woff",".ttf",".js",".css",".jpg",".png",".htc",".swf",".gif",".bmp","login","image.jsp","doLogin",".ico","weixinTip-ff.html","weixinTip-jq.html",".txt"};
		for(int i=0;i<suffixs.length;i++){
			if(url.endsWith(suffixs[i])){
				chain.doFilter(request, response);
				return;
			}
		}
		
		//处理URL权限
		String lastUrl="";
		if(url.indexOf("/")>=0){
			Integer index=url.lastIndexOf("/")+1;
			String uri=url.substring(index);
			if("".equals(uri.trim())){
				chain.doFilter(request, response);
				return;
			}else{
				Integer index2=uri.indexOf("?");
				if(index2>=0){
					String uri2=uri.substring(0,index2);
					if("".equals(uri2)){
						chain.doFilter(request, response);
						return;
					}else{
						lastUrl=uri2;
					}
				}else{
					if(uri.length()>0){
						lastUrl=uri;
					}else{
						chain.doFilter(request, response);
						return;
					}
				}
			}
		}else{
			chain.doFilter(request, response);
			return;
		}

		@SuppressWarnings({ "unchecked" })
		List<Power> powers=(List<Power>) ((HttpServletRequest)request).getSession().getAttribute(Comm.CURRENT_ADMIN_ALL_POWER);
		
		boolean isFound=false;
		for(int i=0;i<powers.size();i++){
			if(lastUrl.equals(powers.get(i).getPoUrl())){
				isFound=true;
				break;
			}
		}
		if(isFound==true){
			chain.doFilter(request, response);
			return;
		}else{
			HttpServletResponse r=(HttpServletResponse) response;
			r.sendError(404);
			return;
		}
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		
	}
	
}
