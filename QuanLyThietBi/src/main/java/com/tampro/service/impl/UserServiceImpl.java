package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tampro.dao.UserDAO;
import com.tampro.dto.UserDTO;
import com.tampro.entity.Roles;
import com.tampro.entity.User;
import com.tampro.service.UserService;
import com.tampro.utils.ConvertDTO;
import com.tampro.utils.Paging;

@Service
public class UserServiceImpl  implements UserService{
	@Autowired
	UserDAO<User> userDAO;
	 
	
	public void add(UserDTO userDTO) throws Exception {
		// TODO Auto-generated method stub
		User user =  new User();
		user.setCreateDate(new Date());
		user.setActiveFlag(1);
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		 
		user.setUpdateDate(new Date());
		user.setUsername(userDTO.getUsername());
		Set<Roles> roles = new HashSet<Roles>();
		userDTO.getIntegers().forEach(item->{
			System.out.println(item);
			roles.add(new Roles(item));
		});
		user.setRoles(roles);
		userDAO.insert(user);
	}

	public void delete(UserDTO userDTO)  {
		// TODO Auto-generated method stub
		User user =  new User();
		user.setCreateDate(userDTO.getCreateDate());
		user.setActiveFlag(0);
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		//user.setRole(userDTO.getRole());
		user.setUpdateDate(new Date());
		user.setUsername(userDTO.getUsername());
		user.setId(userDTO.getId());
		userDAO.delete(user);
	}

	public void update(UserDTO userDTO) throws Exception {
		// TODO Auto-generated method stub
		User user =  new User();
		user.setCreateDate(userDTO.getCreateDate());
		user.setActiveFlag(userDTO.getActiveFlag());
		user.setId(userDTO.getId());
		user.setEmail(userDTO.getEmail());
		user.setName(userDTO.getName());
		user.setPassword(userDTO.getPassword());
		Set<Roles> roles = new HashSet<Roles>();
		userDTO.getIntegers().forEach(item->{
			System.out.println(item);
			roles.add(new Roles(item));
		});
		user.setRoles(roles);
		user.setUpdateDate(new Date());
		user.setUsername(userDTO.getUsername());
		userDAO.update(user);
	}

	public List<UserDTO> getAll(UserDTO userDTO, Paging paging) {
		// TODO Auto-generated method stub
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		StringBuilder queryStr = new StringBuilder();
		if(userDTO != null) {
			
			if(!StringUtils.isEmpty(userDTO.getName())) {
				queryStr.append(" and model.name like :name ");
				mapParams.put("name", "%"+userDTO.getName()+"%");
			}
			if(!StringUtils.isEmpty(userDTO.getUsername())) {
				queryStr.append(" and model.username like :username ");
				mapParams.put("username", "%"+userDTO.getUsername()+"%");
			}
		}
		List<UserDTO> list = new ArrayList<UserDTO>();
		for(User user : userDAO.findAll(mapParams, queryStr.toString(), paging)) {
			UserDTO dto = ConvertDTO.convertUserToDTO(user);
			list.add(dto);
		}
		return list;
	}

	public List<UserDTO> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<UserDTO> list = new ArrayList<UserDTO>();
		for(User user : userDAO.findByProperty(property, object)) {
			UserDTO dto = ConvertDTO.convertUserToDTO(user);
			list.add(dto);
		}
		return list;
	}

	public UserDTO findById(int id) {
		// TODO Auto-generated method stub
		User user  = userDAO.findById(User.class, id);
		UserDTO dto = ConvertDTO.convertUserToDTO(user);
		return dto;
	}

	public long getTotalUser() {
		// TODO Auto-generated method stub
		return userDAO.getTotalUser();
	}

	public UserDTO findByUsername(String username) {
		// TODO Auto-generated method stub
		User user  = userDAO.findByUsername(username);
		UserDTO dto = ConvertDTO.convertUserToDTO(user);
		return dto;
	}

	

}
