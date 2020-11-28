package com.xinthe.service;

import java.util.List;
import java.util.SortedSet;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xinthe.DAO.BaseDAOImplementation;
import com.xinthe.bean.EmployeeBean;

@Service
public class BaseServiceImplementation implements BaseServiceInterface {
	
	@Autowired
	private BaseDAOImplementation baseDAOImplementation;

	@Override
	public void updateHike(Integer hikePercentage,List<EmployeeBean> employeeList) throws Exception {		
		for(EmployeeBean employee:employeeList)
		{
			int currentSalary = employee.getSalary();
			int hikeSalary = currentSalary+(currentSalary*hikePercentage/100);
			employee.setSalary(hikeSalary);
			baseDAOImplementation.update(employee);
		}
		
	}

	@Override
	public List<EmployeeBean> getEmployeeListForGivenPlace(String place) {

		return baseDAOImplementation.getRecordsByPlace(place);
	}

	@Override
	public String getSalaryRangeForGivenCompetency(String competency) {
		
		List<Integer> salaryList=baseDAOImplementation.getSalaryForCompetency(competency);
		if(!salaryList.isEmpty()) {
		SortedSet<Integer> sSet=new TreeSet<>(salaryList);
		Integer min_salary=sSet.first();
		Integer max_salary=sSet.last();
		return "{ min: "+ min_salary+", max: "+max_salary+"}";
		}
		return null;
	}

}
