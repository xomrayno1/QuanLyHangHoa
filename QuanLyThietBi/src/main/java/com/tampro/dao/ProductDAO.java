package com.tampro.dao;

import com.tampro.entity.Product;

public interface ProductDAO<E> extends BaseDAO<E>{
	Product  findByName(String name);
}
