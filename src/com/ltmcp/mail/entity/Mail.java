package com.ltmcp.mail.entity;

public class Mail {
      
    /** 发信人 */  
    private String from;  
    /** 收信人 */  
    private String to;  
    /** 主题 */  
    private String subject;  
    /** 正文 */  
    private String body;  
      
    private String copyto;
    
    private String[] attachFileNames;	//附件名称
    
    

    public Mail() {   
          
    }

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getTo() {
		return to;
	}

	public void setTo(String to) {
		this.to = to;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String object) {
		this.body = object;
	}

	public String getCopyto() {
		return copyto;
	}

	public void setCopyto(String copyto) {
		this.copyto = copyto;
	}

	public String[] getAttachFileNames() {
		return attachFileNames;
	}

	public void setAttachFileNames(String[] attachFileNames) {
		this.attachFileNames = attachFileNames;
	}

}
