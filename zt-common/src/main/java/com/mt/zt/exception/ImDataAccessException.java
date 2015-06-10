package com.mt.zt.exception;

public class ImDataAccessException extends RuntimeException {


	/**
	 * 
	 */
	private static final long serialVersionUID = -3180895704563638608L;

	public ImDataAccessException() {
		
	}

	/**
	 * @param message
	 */
	public ImDataAccessException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ImDataAccessException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ImDataAccessException(String message, Throwable cause) {
		super(message, cause);
	}

}
