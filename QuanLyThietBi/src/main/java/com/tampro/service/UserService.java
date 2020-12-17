package com.tampro.service;

import java.util.List;

import com.tampro.dto.UserDTO;
import com.tampro.utils.Paging;

public interface UserService {
	
	void add(UserDTO userDTO) throws Exception;
	void delete(UserDTO userDTO) ;
	void update(UserDTO userDTO) throws Exception;
	List<UserDTO> getAll(UserDTO userDTO , Paging paging);
	List<UserDTO> getAllByProperty(String property , Object object);
	UserDTO findById(int id);
	UserDTO findByUsername(String username);
	public long getTotalUser();
	
}
