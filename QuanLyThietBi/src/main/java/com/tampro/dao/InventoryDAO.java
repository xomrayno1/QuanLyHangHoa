package com.tampro.dao;

import com.tampro.entity.Inventory;

public interface InventoryDAO<E> extends BaseDAO<E> {
	Inventory  findByName(String name);
}
