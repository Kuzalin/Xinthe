package com.xinthe.DAO;

import java.util.List;

import com.xinthe.bean.EmployeeBean;

public interface BaseDAOInterface {
	
	public List<EmployeeBean> getRecordsByPlace(String place);
	public List<Integer> getSalaryForCompetency(String competency); 
}
