package com.ltmcp.test;  
import java.io.UnsupportedEncodingException;
import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
@SuppressWarnings("unused")
public class SendDuanxin {

	public void send(String phone,String message)throws Exception
	{
	
		HttpClient client = new HttpClient();
		PostMethod post = new PostMethod("http://gbk.sms.webchinese.cn"); 
		post.addRequestHeader("Content-Type","application/x-www-form-urlencoded;charset=gbk");//在头文件中设置转码"15882279820"
		NameValuePair[] data ={ new NameValuePair("Uid", "javaee3"),new NameValuePair("Key", "21d0e6108dfe2e0fce01"),new NameValuePair("smsMob",phone),new NameValuePair("smsText",message)};
		post.setRequestBody(data);
	
		client.executeMethod(post);
		Header[] headers = post.getResponseHeaders();
		int statusCode = post.getStatusCode();
		System.out.println("statusCode:"+statusCode);
		for(Header h : headers)
		{
			System.out.println(h.toString());
		}
		String result = new String(post.getResponseBodyAsString().getBytes("gbk")); 
		System.out.println(result);
		post.releaseConnection();
	
	}
}