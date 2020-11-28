package com.xinthe.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.xinthe.bean.EmployeeBean;

@Repository
public class BaseDAOImplementation extends JpaDAO<Object, EmployeeBean> implements BaseDAOInterface {

		
	@SuppressWarnings("unchecked")
	@Override
	public List<EmployeeBean> getRecordsByPlace(String place) {
		EntityManager entityManager=getEntityManager();
		List<EmployeeBean> result=null;
		String sqlString = "Select * from Employee where UPPER(Place) = ?";		
		Query query=entityManager.createNativeQuery(sqlString,EmployeeBean.class);
		query.setParameter(1, place.toUpperCase());
		result=query.getResultList();		
		return result;
		
	}

	public void update(EmployeeBean employee) throws Exception {	
		try {
		super.merge(employee);
		}
		catch(Exception exp)
		{
			super.rollbackTransaction();
			throw new Exception("Exception Occured While Updating Records",exp);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Integer> getSalaryForCompetency(String competency) {
		EntityManager entityManager=getEntityManager();
		List<Integer> result=null;
		String sqlString = "Select salary from Employee where UPPER(Competencies) = ?";		
		Query query=entityManager.createNativeQuery(sqlString);
		query.setParameter(1, competency.toUpperCase());
		result=query.getResultList();		
		return result;
	}

}
