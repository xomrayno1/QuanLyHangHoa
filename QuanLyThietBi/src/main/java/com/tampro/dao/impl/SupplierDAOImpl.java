package com.tampro.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.SupplierDAO;
import com.tampro.entity.Supplier;
@Repository
@Transactional(rollbackFor = Exception.class)
public class SupplierDAOImpl  extends BaseDAOImpl<Supplier> implements SupplierDAO<Supplier>{

	@Override
	public Supplier findByName(String name) {
		// TODO Auto-generated method stub
		Query query = 	factory.getCurrentSession()
				.createQuery(" FROM Supplier as model where model.activeFlag = 1 and model.name = :name ");
		query.setParameter("name", name);
		if(query.getSingleResult() == null) {
			return null;
		}else {
			return (Supplier) query.getSingleResult();
		}
	}

}
