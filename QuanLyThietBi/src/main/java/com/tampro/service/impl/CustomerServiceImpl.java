package com.tampro.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.tampro.dao.CustomerDAO;
import com.tampro.dto.CustomerDTO;
import com.tampro.entity.Customer;
import com.tampro.service.CustomerService;
import com.tampro.utils.ConvertDTO;
import com.tampro.utils.Paging;

@Service
public class CustomerServiceImpl  implements CustomerService{
	@Autowired
	CustomerDAO<Customer> cusDAO;

	@Override
	public void add(CustomerDTO customerDTO) throws Exception {
		// TODO Auto-generated method stub
		Customer customer = new Customer();
		customer.setActiveFlag(1);
		customer.setCreateDate(new Date());
		customer.setUpdateDate(new Date());
		customer.setName(customerDTO.getName());
		customer.setAddress(customerDTO.getAddress());
		customer.setEmail(customerDTO.getEmail());
		customer.setPhone(customerDTO.getPhone()); 
		cusDAO.insert(customer);
	}

	@Override
	public void delete(CustomerDTO customerDTO) {
		// TODO Auto-generated method stub
		Customer customer = new Customer();
		customer.setActiveFlag(0);
		customer.setId(customerDTO.getId());
		customer.setCreateDate(customerDTO.getCreateDate());
		customer.setUpdateDate(new Date());
		customer.setName(customerDTO.getName());
		customer.setAddress(customerDTO.getAddress());
		customer.setEmail(customerDTO.getEmail());
		customer.setPhone(customerDTO.getPhone());
		cusDAO.delete(customer);
	}

	@Override
	public void update(CustomerDTO customerDTO) throws Exception {
		// TODO Auto-generated method stub
		Customer customer = new Customer();
		customer.setActiveFlag(customerDTO.getActiveFlag());
		customer.setId(customerDTO.getId());
		customer.setCreateDate(customerDTO.getCreateDate());
		customer.setUpdateDate(new Date());
		customer.setName(customerDTO.getName());
		customer.setAddress(customerDTO.getAddress());
		customer.setEmail(customerDTO.getEmail());
		customer.setPhone(customerDTO.getPhone()); 
		cusDAO.update(customer);
	}

	@Override
	public List<CustomerDTO> getAll(CustomerDTO customerDTO, Paging paging) {
		// TODO Auto-generated method stub
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		StringBuilder queryStr = new StringBuilder();
		if(customerDTO != null) {
 
			if(!StringUtils.isEmpty(customerDTO.getName())) {
				queryStr.append(" and model.name like :name ");
				mapParams.put("name", "%"+customerDTO.getName()+"%");
			}
		} 
		List<CustomerDTO> list = new ArrayList<CustomerDTO>();
		for(Customer customer : cusDAO.findAll(mapParams, queryStr.toString(), paging)) {
			CustomerDTO dto = ConvertDTO.convertCustomerToDTO(customer);
			list.add(dto);
		}
		return list;
	}

	@Override
	public List<CustomerDTO> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<CustomerDTO> list = new ArrayList<CustomerDTO>();
		for(Customer customer : cusDAO.findByProperty(property, object)) {
			CustomerDTO dto = ConvertDTO.convertCustomerToDTO(customer);
			list.add(dto);
		}
		return list;
	}

	@Override
	public CustomerDTO findById(int id) {
		// TODO Auto-generated method stub
		Customer customer = cusDAO.findById(Customer.class, id);
		CustomerDTO dto = ConvertDTO.convertCustomerToDTO(customer);
		return dto;
	}

	@Override
	public CustomerDTO findByName(String name) {
		// TODO Auto-generated method stub
		Customer customer = cusDAO.findByName(name);
		CustomerDTO dto = ConvertDTO.convertCustomerToDTO(customer);
		return dto;
	}

}
