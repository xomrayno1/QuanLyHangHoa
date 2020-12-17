package com.tampro.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.CustomerDAO;
import com.tampro.entity.Customer;

@Repository
@Transactional(rollbackFor = Exception.class)
public class CustomerDAOImpl extends BaseDAOImpl<Customer> implements CustomerDAO<Customer>{

	@Override
	public Customer findByName(String name) {
		// TODO Auto-generated method stub
		Query query = 	factory.getCurrentSession()
				.createQuery(" FROM Customer as model where model.activeFlag = 1 and model.name = :name ");
		query.setParameter("name", name);
		if(query.getSingleResult() == null) {
			return null;
		}else {
			return (Customer) query.getSingleResult();
		}
	}

}
