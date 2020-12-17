package com.tampro.dao.impl;

import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.UserDAO;
import com.tampro.entity.User;

@Repository
@Transactional(rollbackFor = Exception.class)
public class UserDAOImpl extends BaseDAOImpl<User>implements UserDAO<User>{

	public long getTotalUser() {
		// TODO Auto-generated method stub
		StringBuilder queryCount =
				new StringBuilder("SELECT COUNT(*) FROM User as model where model.activeFlag = 1 ");
		Query   queryNumber = factory.getCurrentSession().createQuery(queryCount.toString());
		long count = (Long) queryNumber.uniqueResult();
		return count;
	}

	public User findByUsername(String username) {
		// TODO Auto-generated method stub
		StringBuilder queryCount =
				new StringBuilder("SELECT *  FROM User as model where model.activeFlag = 1 and model.username = "+username);
		Query   queryNumber = factory.getCurrentSession().createQuery(queryCount.toString());
		User user = (User)  queryNumber.getSingleResult();
		return  user;
	}

}
