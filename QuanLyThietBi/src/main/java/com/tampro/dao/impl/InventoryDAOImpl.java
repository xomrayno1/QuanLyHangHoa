package com.tampro.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.InventoryDAO;
import com.tampro.entity.Inventory;

@Repository
@Transactional(rollbackFor = Exception.class)
public class InventoryDAOImpl extends BaseDAOImpl<Inventory>implements InventoryDAO<Inventory>{

	@Override
	public Inventory findByName(String name) {
		// TODO Auto-generated method stub
		Query query = 	factory.getCurrentSession()
				.createQuery(" FROM Inventory as model where model.activeFlag = 1 and model.name = :name ");
		query.setParameter("name", name);
		if(query.getSingleResult() == null) {
			return null;
		}else {
			return (Inventory) query.getSingleResult();
		}
	}

}
