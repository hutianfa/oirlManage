package com.ltmcp.filter;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ���������
 * @author Administrator
 *
 */
public class CodeFilter implements Filter{
	protected FilterConfig filterConfig ;  
    String encoding = null;  
      
    public void destroy() {  
        this.filterConfig = null;  
    }  
  
    /** 
     * ��ʼ�� 
     */  
    public void init(FilterConfig filterConfig) {  
        this.filterConfig = filterConfig;  
    }  
  
    /** 
     * �� inStr תΪ UTF-8 �ı�����ʽ 
     *  
     * @param inStr �����ַ��� 
     * @return UTF - 8 �ı�����ʽ���ַ��� 
     * @throws UnsupportedEncodingException 
     */  
    private String toUTF(String inStr) throws UnsupportedEncodingException {  
        String outStr = "";  
        if (inStr != null) {  
            outStr = new String(inStr.getBytes("iso-8859-1"), "UTF-8");  
        }  
        return outStr;  
    }  
  
    /** 
     * ����������˴��� 
     */  
    public void doFilter(ServletRequest servletRequest,ServletResponse servletResponse, FilterChain chain) throws IOException,ServletException {  
        HttpServletRequest request = (HttpServletRequest) servletRequest;  
        HttpServletResponse response = (HttpServletResponse) servletResponse;  
  
        // �������ķ�ʽ (1.post or 2.get), ���ݲ�ͬ����ʽ���в�ͬ����  
        String method = request.getMethod();  
        // 1. �� post ��ʽ�ύ������ , ֱ�����ñ���Ϊ UTF-8  
        if (method.equalsIgnoreCase("post")) {  
            try {  
                request.setCharacterEncoding("UTF-8");  
            } catch (UnsupportedEncodingException e) {  
                e.printStackTrace();  
            }  
        }  
        // 2. �� get ��ʽ�ύ������  
        else {  
            // ȡ���ͻ��ύ�Ĳ�����  
            Enumeration<String> paramNames = request.getParameterNames();  
            // ����������ȡ��ÿ�����������Ƽ�ֵ  
            while (paramNames.hasMoreElements()) {  
                String name = paramNames.nextElement(); // ȡ����������  
                String values[] = request.getParameterValues(name); // ���ݲ�������ȡ����ֵ  
                // �������ֵ����Ϊ��  
                if (values != null) {  
                    // ��������ֵ��  
                    for (int i = 0; i < values.length; i++) {  
                        try {  
                            // ��Ȧ���ν�ÿ��ֵ���� toUTF(values[i]) ����ת������ֵ����Ԫ����  
                            String vlustr = toUTF(values[i]);  
                            values[i] = vlustr;  
                        } catch (UnsupportedEncodingException e) {  
                            e.printStackTrace();  
                        }  
                    }  
                    // ����ֵ�����Ե���ʽ���� request  
                    request.setAttribute(name, values);  
                }  
            }  
  
        }  
        // ������Ӧ��ʽ��֧�����ĵ���Ԫ��  
        response.setContentType("text/html;charset=UTF-8");  
  
        // ����ִ����һ�� filter, ��һ�¸� filter ��ִ������  
        chain.doFilter(request, response);  
    }  

}
