package com.xinthe.custom.exceptions;

import org.springframework.http.HttpStatus;

public class BusinessException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private  HttpStatus status;
	
	private String businessMessage="Business Error";
	
	public  HttpStatus getStatus() {
		return status;
	}

	public BusinessException(String message)
	{
		super(message);
	}
	
	public BusinessException(String message,HttpStatus status)
	{
		super(message);
		this.status=status;
	}

	public BusinessException(String message,String businessMessage,HttpStatus status)
	{
		super(message);
		this.status=status;
		this.businessMessage=businessMessage;
	}
	
	
	public String getBusinessMessage() {
		return businessMessage;
	}

	public void setBusinessMessage(String businessMessage) {
		this.businessMessage = businessMessage;
	}

}
