package com.tampro.dao;

public interface MaintenanceDAO<E> extends BaseDAO<E> {
	long getCountMaintenaceByNowDate(int status); 
}
