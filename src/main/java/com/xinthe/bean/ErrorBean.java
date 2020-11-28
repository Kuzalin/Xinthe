package com.xinthe.bean;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ErrorBean {
	
	

		   private HttpStatus status;		

		@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
		   private LocalDateTime timestamp;
		   private String message;
		   private String debugMessage;
		   

		   private ErrorBean() {
		       timestamp = LocalDateTime.now();
		   }

		   public ErrorBean(HttpStatus status) {
		       this();
		       this.status = status;
		   }

		  public ErrorBean(HttpStatus status, Throwable ex) {
		       this();
		       this.status = status;
		       this.message = "Unexpected error";
		       this.debugMessage = ex.getLocalizedMessage();
		   }

		   public ErrorBean(HttpStatus status, String message, Throwable ex) {
		       this();
		       this.status = status;
		       this.message = message;
		       this.debugMessage = ex.getLocalizedMessage();
		   }
		
		   public HttpStatus getStatus() {
				return status;
			}

		public String getMessage() {
			return message;
		}		

		public String getDebugMessage() {
			return debugMessage;
		}

		
		   
		   
}
