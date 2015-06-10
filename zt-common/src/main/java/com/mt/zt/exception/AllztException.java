package com.mt.zt.exception;

public class AllztException extends RuntimeException {
	  
    /**
	 * 
	 */
	private static final long serialVersionUID = 4745840864383282035L;

	public AllztException() { 
    	
    }  
  
    /** 
     * @param message 
     */  
    public AllztException(String message) {  
        super(message);  
    }  
  
    /** 
     * @param cause 
     */  
    public AllztException(Throwable cause) {  
        super(cause);  
    }  
  
    /** 
     * @param message 
     * @param cause 
     */  
    public AllztException(String message, Throwable cause) {  
        super(message, cause);  
    } 
}
