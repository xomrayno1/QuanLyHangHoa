package com.tampro.service;

import java.util.List;

import com.tampro.dto.MaintenanceDTO;
import com.tampro.utils.Paging;

public interface MaintenanceService {
	void add(MaintenanceDTO maintenanceDTO) throws Exception;
	void delete(MaintenanceDTO maintenanceDTO) ;
	void update(MaintenanceDTO maintenanceDTO) throws Exception;
	List<MaintenanceDTO> getAll(MaintenanceDTO maintenanceDTO , Paging paging);
	List<MaintenanceDTO> getAllByProperty(String property , Object object);
	MaintenanceDTO findById(int id);
	long getCountMaintenaceByNowDate(int status); 
}
