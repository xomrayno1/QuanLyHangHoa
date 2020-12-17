package com.tampro.dao.impl;

import javax.persistence.Query;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.ProductDAO;
import com.tampro.entity.Product;

@Repository
@Transactional(rollbackFor = Exception.class)
public class ProductDAOImpl extends BaseDAOImpl<Product>implements ProductDAO<Product>{

	@Override
	public Product findByName(String name) {
		// TODO Auto-generated method stub
		Query query = 	factory.getCurrentSession()
				.createQuery(" FROM Product as model where model.activeFlag = 1 and model.name = :name ");
		query.setParameter("name", name);
		if(query.getSingleResult() == null) {
			return null;
		}else {
			return (Product) query.getSingleResult();
		}
	}

}
