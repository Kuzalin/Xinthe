package com.xinthe.bean;

public final class FinalResponse {
	
	private final String responseCode = "SUCCESS";
	private Object response;
	
	public FinalResponse(Object response) {		
		this.response=response;		
	}
	
	public Object getResponse()
	{
		return this.response;
	}
	
	public String getResponseCode()
	{
		return this.responseCode;
	}

}
