package com.mt.zt.exception;

public class SystemException extends RuntimeException {
	  
    /**
	 * 
	 */
	private static final long serialVersionUID = -4507412634412807146L;

	public SystemException() {  
    }  
  
    /** 
     * @param message 
     */  
    public SystemException(String message) {  
        super(message);  
    }  
  
    /** 
     * @param cause 
     */  
    public SystemException(Throwable cause) {  
        super(cause);  
    }  
  
    /** 
     * @param message 
     * @param cause 
     */  
    public SystemException(String message, Throwable cause) {  
        super(message, cause);  
    } 
}
