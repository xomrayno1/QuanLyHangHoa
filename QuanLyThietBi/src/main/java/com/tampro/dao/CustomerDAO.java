package com.tampro.dao;

import com.tampro.entity.Customer;

public interface CustomerDAO<E> extends BaseDAO<E>{
	Customer  findByName(String name);
}
