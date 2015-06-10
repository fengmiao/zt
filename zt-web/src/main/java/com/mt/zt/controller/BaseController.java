package com.mt.zt.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


//@RestController
public class BaseController {

	private static final Logger logger = LoggerFactory.getLogger(BaseController.class);
	
	/**
	@ExceptionHandler
    public RspResultVo<Void> exceptionSolve(HttpServletRequest request, Exception ex) {
		ErrorVo error = new ErrorVo();
        if(ex instanceof JdException) {  
        	error.setMessage(ex.getMessage()); 
        }else  {
        	error.setMessage(ex.toString());
        } 
		RspResultVo<Void>  r = new RspResultVo<Void>(request);
		r.setError(error);
		ex.printStackTrace();
        return r;
    }
    **/
	
	/**
	private void logError(HttpServletRequest request,String msg){
		logger.error("Error! URL:"+request.getServletPath()+" ,Method:"+request.getMethod()+" Msg:"+msg);
	}
	
	private ErrorVo bulidErrorVo(String errorCode){
		ErrorVo error = new ErrorVo();
		String msg = PropertiesUtils.getPropertie(errorCode);
		error.setErrorCode(errorCode);
		error.setMessage(msg);
		return error;
	}
	
	@ExceptionHandler(NullPointerException.class)  
    @ResponseStatus(value = HttpStatus.NOT_FOUND)  
    @ResponseBody  
    public RspResultVo<Void> handleInvalidRequestError(NullPointerException ex,HttpServletRequest request) {
		logError(request, ex.getMessage());
		RspResultVo<Void>  r = new RspResultVo<Void>(request);
		r.setSuccess(false);
		r.setError(bulidErrorVo("E101"));
        return r;  
    }
    **/
	
	
}
