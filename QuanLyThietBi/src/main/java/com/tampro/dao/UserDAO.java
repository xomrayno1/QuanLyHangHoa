package com.tampro.dao;

import com.tampro.entity.User;

public interface UserDAO<E> extends BaseDAO<E> {

	long getTotalUser();
	
	User findByUsername(String username);
}
