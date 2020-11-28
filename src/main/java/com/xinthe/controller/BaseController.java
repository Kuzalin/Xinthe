package com.xinthe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xinthe.bean.EmployeeBean;
import com.xinthe.bean.FinalResponse;
import com.xinthe.custom.exceptions.BusinessException;
import com.xinthe.service.BaseServiceInterface;

@RestController
public class BaseController {
	
	private static final  int MAX_HIKE=55;
	private static final String ERROR_MSG_55="Hike cannot be given above 55%. Please correct.";
		
	private static final String PROCESS_UPDATED="Update hike for given data.";
	
	private static final String NO_RECORD_FOUND="No record found for requested data.";
	
	
	@Autowired
	private BaseServiceInterface baseServiceInterface;
	
	@PutMapping("/employee/place/{place}/salary/{percentage}")
	public ResponseEntity<Object> updateSalary(@PathVariable String place, @PathVariable Integer percentage) throws Exception
	{		
				
			if(null!=percentage && percentage<=MAX_HIKE)
			{
				List<EmployeeBean> employeeList = baseServiceInterface.getEmployeeListForGivenPlace(place);
				if(!employeeList.isEmpty()) {
					baseServiceInterface.updateHike(percentage, employeeList);	
					return ResponseEntity.ok(new FinalResponse(PROCESS_UPDATED));
				}
				else
				{
					throw new BusinessException(NO_RECORD_FOUND,HttpStatus.NOT_FOUND);
				}
			}
			else
			{					
				throw new BusinessException(ERROR_MSG_55,HttpStatus.NOT_ACCEPTABLE);
				
			}
		
		
		
	}
	
	
	@GetMapping("/employee/place/{place}")
	public ResponseEntity<Object> getEmployeeListForGivenPlace(@PathVariable String place) throws Exception
	{
		
		
			List<EmployeeBean> employeeList = baseServiceInterface.getEmployeeListForGivenPlace(place);
			if(!employeeList.isEmpty()) {				
				return ResponseEntity.ok(new FinalResponse(employeeList));
			}
			else
			{
				throw new BusinessException(NO_RECORD_FOUND,HttpStatus.NOT_FOUND);				
			}		

	}
	
	@GetMapping("/employee/Competency/{competency}")
	public ResponseEntity<Object> returnSalaryRange(@PathVariable String competency) throws Exception
	{
		String response = baseServiceInterface.getSalaryRangeForGivenCompetency(competency);
			if(null!=response) {
			return ResponseEntity.ok(new FinalResponse(response));
			}
			else
			{
				throw new BusinessException(NO_RECORD_FOUND,HttpStatus.NOT_FOUND);
			}
		}
		
}
	

