package com.mt.zt.exception;

public class BusinessException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;  
	
	private String errorCode;
	
	public BusinessException(String errorCode,String message) {
		super(message);
		this.errorCode = errorCode;
    }
	
    public BusinessException() { 
    	
    }  
  
    /** 
     * @param message 
     */  
    public BusinessException(String errorCode) {  
    	this.errorCode = errorCode;
    }  
  
    /** 
     * @param cause 
     */  
    public BusinessException(Throwable cause) {  
        super(cause);  
    }  
  
    /** 
     * @param message 
     * @param cause 
     */  
    public BusinessException(String message, Throwable cause) {  
        super(message, cause);  
    }

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	} 
    
}
