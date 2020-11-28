package com.xinthe.service;

import java.util.List;

import com.xinthe.bean.EmployeeBean;

public interface BaseServiceInterface {

	public void updateHike(Integer hikePercentage,List<EmployeeBean> employeeList) throws Exception;

	public List<EmployeeBean> getEmployeeListForGivenPlace(String place);

	public String getSalaryRangeForGivenCompetency(String competency);
	
}
