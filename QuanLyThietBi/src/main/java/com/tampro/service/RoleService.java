package com.tampro.service;

import java.util.List;

import com.tampro.dto.RolesDTO;

public interface RoleService {
	List<RolesDTO> getAll();
	RolesDTO findById(int id);

}
