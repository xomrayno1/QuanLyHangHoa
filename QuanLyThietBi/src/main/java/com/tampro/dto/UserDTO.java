package com.tampro.dto;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDTO extends Base{
	private int id;
	private String username;
	private String password;
	private String name;
	private String email;
	private Set<RolesDTO> roleDTOs;
	private List<Integer> integers;
	 
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	 
	 
	@Override
	public String toString() {
		return "UserDTO [id=" + id + ", username=" + username + ", password=" + password + ", name=" + name + ", email="
				+ email    + "]";
	}
	public Set<RolesDTO> getRoleDTOs() {
		return roleDTOs;
	}
	public void setRoleDTOs(Set<RolesDTO> roleDTOs) {
		this.roleDTOs = roleDTOs;
	}
	
	public List<String> getListRole(){
		List<String> strings = new ArrayList<String>();
		roleDTOs.forEach(item->{
			strings.add(item.getName());
		});
		return strings;
	}
	public List<Integer> getIntegers() {
		return integers;
	}
	public void setIntegers(List<Integer> integers) {
		this.integers = integers;
	}
	
	
	
}
