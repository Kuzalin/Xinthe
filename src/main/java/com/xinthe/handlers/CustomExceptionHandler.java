package com.xinthe.handlers;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.xinthe.bean.ErrorBean;
import com.xinthe.custom.exceptions.BusinessException;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class CustomExceptionHandler  extends ResponseEntityExceptionHandler  {	

	
	 @ExceptionHandler(BusinessException.class)
	   protected ResponseEntity<Object> handleBusinessException(BusinessException ex)
	   {		   
		   return buildResponseEntity(new ErrorBean(ex.getStatus(),ex.getBusinessMessage(),ex));
	   }

	   private ResponseEntity<Object> buildResponseEntity(ErrorBean errorBean) {
	       return new ResponseEntity<>(errorBean, errorBean.getStatus());
	   }
	   
	  
	   
	 

}
