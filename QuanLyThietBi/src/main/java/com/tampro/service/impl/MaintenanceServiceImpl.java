package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tampro.dao.MaintenanceDAO;
import com.tampro.dto.MaintenanceDTO;
import com.tampro.entity.Maintenance;
import com.tampro.entity.Product;
import com.tampro.service.MaintenanceService;
import com.tampro.utils.ConvertDTO;
import com.tampro.utils.Paging;

@Service
public class MaintenanceServiceImpl  implements MaintenanceService{
	
	@Autowired
	MaintenanceDAO<Maintenance> maintenanceDAO;

	public void add(MaintenanceDTO maintenanceDTO) throws Exception {
		// TODO Auto-generated method stub
		Maintenance maintenance = new Maintenance();
		maintenance.setActiveFlag(1);
		maintenance.setCreateDate(new Date());
		maintenance.setDescription(maintenanceDTO.getDescription());
		maintenance.setProduct(new Product(maintenanceDTO.getIdProduct()));
		maintenance.setStatus(maintenanceDTO.getStatus());
		maintenance.setUpdateDate(new Date());
		maintenance.setName(maintenanceDTO.getName());
		maintenanceDAO.insert(maintenance);
	}

	public void delete(MaintenanceDTO maintenanceDTO) {
		// TODO Auto-generated method stub
		Maintenance maintenance = new Maintenance();
		maintenance.setActiveFlag(0);
		maintenance.setCreateDate(maintenanceDTO.getCreateDate());
		maintenance.setDescription(maintenanceDTO.getDescription());
		maintenance.setId(maintenanceDTO.getId());
		maintenance.setProduct(new Product(maintenanceDTO.getIdProduct()));
		maintenance.setStatus(maintenanceDTO.getStatus());
		maintenance.setUpdateDate(new Date());
		maintenance.setName(maintenanceDTO.getName());
		maintenanceDAO.delete(maintenance);
	}

	public void update(MaintenanceDTO maintenanceDTO) throws Exception {
		// TODO Auto-generated method stub
		Maintenance maintenance = new Maintenance();
		maintenance.setActiveFlag(maintenanceDTO.getActiveFlag());
		maintenance.setCreateDate(maintenanceDTO.getCreateDate());
		maintenance.setDescription(maintenanceDTO.getDescription());
		maintenance.setId(maintenanceDTO.getId());
		maintenance.setProduct(new Product(maintenanceDTO.getIdProduct()));
		maintenance.setStatus(maintenanceDTO.getStatus());
		maintenance.setUpdateDate(new Date());
		maintenance.setName(maintenanceDTO.getName());
		maintenanceDAO.update(maintenance);
	}

	public List<MaintenanceDTO> getAll(MaintenanceDTO maintenanceDTO, Paging paging) {
		// TODO Auto-generated method stub
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		StringBuilder queryStr = new StringBuilder();
		if(maintenanceDTO != null) {
			if(maintenanceDTO.getStatus() !=0) {
				queryStr.append(" and model.status = :status ");
				mapParams.put("status", maintenanceDTO.getStatus());
			}
			if(maintenanceDTO.getIdProduct() != 0) {
				queryStr.append(" and model.product.id = :productId ");
				mapParams.put("productId", maintenanceDTO.getIdProduct());
			}
			if(!StringUtils.isEmpty(maintenanceDTO.getName())) {
				queryStr.append(" and model.name like :name ");
				mapParams.put("name", "%"+maintenanceDTO.getName()+"%");
			}
			if(maintenanceDTO.getDateTo() != null && maintenanceDTO.getDateFrom() != null) {
				queryStr.append(" and date(model.createDate) between  :dateTo and :dateFrom ");
				mapParams.put("dateTo", maintenanceDTO.getDateTo());
				mapParams.put("dateFrom", maintenanceDTO.getDateFrom());
			}
			
		}
		queryStr.append(" ORDER BY(model.status) ASC ");
		List<MaintenanceDTO> list = new ArrayList<MaintenanceDTO>();
		for(Maintenance maintenance : maintenanceDAO.findAll(mapParams, queryStr.toString(), paging)) {
			MaintenanceDTO dto = ConvertDTO.convertMaintenanceToDTO(maintenance);
			list.add(dto);
		}
		return list;
	}

	public List<MaintenanceDTO> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<MaintenanceDTO> list = new ArrayList<MaintenanceDTO>();
		for(Maintenance maintenance : maintenanceDAO.findByProperty(property, object)) {
			MaintenanceDTO dto = ConvertDTO.convertMaintenanceToDTO(maintenance);
			list.add(dto);
		}
		return list;
	}

	public MaintenanceDTO findById(int id) {
		// TODO Auto-generated method stub
		Maintenance maintenance = maintenanceDAO.findById(Maintenance.class, id);
		MaintenanceDTO dto = ConvertDTO.convertMaintenanceToDTO(maintenance);
		return dto;
	}

	public long getCountMaintenaceByNowDate(int status) {
		// TODO Auto-generated method stub
		return maintenanceDAO.getCountMaintenaceByNowDate(status);
	}
}
