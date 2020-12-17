package com.tampro.dao;

import com.tampro.entity.Supplier;

public interface SupplierDAO<E> extends BaseDAO<E> {
	Supplier  findByName(String name);
}
