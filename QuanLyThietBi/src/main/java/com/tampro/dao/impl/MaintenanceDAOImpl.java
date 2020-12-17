package com.tampro.dao.impl;

import java.time.LocalDateTime;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.MaintenanceDAO;
import com.tampro.entity.Maintenance;

@Repository
@Transactional(rollbackFor = Exception.class)
public class MaintenanceDAOImpl  extends BaseDAOImpl<Maintenance>implements MaintenanceDAO<Maintenance>{

	public long getCountMaintenaceByNowDate(int status) {
		// TODO Auto-generated method stub
		StringBuilder queryCount = new StringBuilder();
		queryCount.append("SELECT COUNT(*) FROM ")
		.append(getGenericName()).append(" as model where model.activeFlag = 1  ")
		.append("and MONTH(model.createDate) =:month and Year(model.createDate) =:year ");
		 
		if(status != 0) {
			queryCount.append(" and model.status =:status ");
			
		}
		Query queryNumber = factory.getCurrentSession().createQuery(queryCount.toString());
		queryNumber.setParameter("year", LocalDateTime.now().getYear());
		queryNumber.setParameter("month", LocalDateTime.now().getMonthValue());
		if(status != 0) {
			queryNumber.setParameter("status",  status);
		}
		long count = (Long) queryNumber.uniqueResult();
		
		
		return count;
	}
	
	 
	
}
