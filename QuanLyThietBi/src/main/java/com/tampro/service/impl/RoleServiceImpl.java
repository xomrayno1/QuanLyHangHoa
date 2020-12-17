package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tampro.dao.RoleDAO;
import com.tampro.dto.RolesDTO;
import com.tampro.entity.Roles;
import com.tampro.service.RoleService;
import com.tampro.utils.ConvertDTO;

@Service
public class RoleServiceImpl  implements RoleService{

	@Autowired
	RoleDAO<Roles> rolesDAO;
	
	@Override
	public List<RolesDTO> getAll() {
		// TODO Auto-generated method stub
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		StringBuilder queryStr = new StringBuilder();
		
		List<RolesDTO> list = new ArrayList<RolesDTO>();
		for(Roles roles : rolesDAO.findAll(null, null, null)) {
			RolesDTO dto = ConvertDTO.convertRoleToDTO(roles);
			list.add(dto);
		}
		return list;
	}

	@Override
	public RolesDTO findById(int id) {
		// TODO Auto-generated method stub
		Roles roles = rolesDAO.findById(Roles.class, id);
		RolesDTO dto = ConvertDTO.convertRoleToDTO(roles);
		return dto;
	}

}
