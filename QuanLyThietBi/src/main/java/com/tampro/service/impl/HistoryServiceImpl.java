package com.tampro.service.impl;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.commons.collections4.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.tampro.dao.HistoryDAO;
import com.tampro.dto.MaintenanceDetailDTO;
import com.tampro.entity.MaintenanceDetail;
import com.tampro.entity.Maintenance;
import com.tampro.service.MaintenanceDetailService;
import com.tampro.utils.Constant;
import com.tampro.utils.ConvertDTO;
import com.tampro.utils.Paging;

@Service
public class HistoryServiceImpl implements MaintenanceDetailService {

	@Autowired
	HistoryDAO<MaintenanceDetail> historyDAO;
	@Autowired
	ServletContext context;
	
	public void delete(MaintenanceDetailDTO historyDTO) {
		// TODO Auto-generated method stub
		MaintenanceDetail history = new MaintenanceDetail();
		history.setActiveFlag(0);
		history.setCreateDate(historyDTO.getCreateDate());
		history.setDescription(historyDTO.getDescription());
		history.setId(historyDTO.getId());
		history.setImgUrl(historyDTO.getImgUrl());
		history.setMaintenance(new Maintenance(historyDTO.getMaintenanceId()));
		history.setUpdateDate(new Date());
		historyDAO.delete(history);
	}

	public List<MaintenanceDetailDTO> getAll(MaintenanceDetailDTO historyDTO, Paging paging) {
		// TODO Auto-generated method stub
		
		Map<String, Object> mapParams = new HashedMap<String, Object>();
		StringBuilder queryStr = new StringBuilder();
		if(historyDTO != null) {
//			if(historyDTO.getDateTo() != null && historyDTO.getDateFrom() != null) {
//			queryStr.append(" and model.createDate  between date(:dateTo) and date(:dateFrom) ");
//			mapParams.put("dateTo", historyDTO.getDateTo());
//			mapParams.put("dateFrom", historyDTO.getDateFrom());
//			}
		}
		List<MaintenanceDetailDTO> list = new ArrayList<MaintenanceDetailDTO>();
		for(MaintenanceDetail history : historyDAO.findAll(mapParams, queryStr.toString(), paging)) {
			MaintenanceDetailDTO dto = ConvertDTO.convertHistoryToDTO(history);
			list.add(dto);
		}
		return list;
	}

	public List<MaintenanceDetailDTO> getAllByProperty(String property, Object object) {
		// TODO Auto-generated method stub
		List<MaintenanceDetailDTO> list = new ArrayList<MaintenanceDetailDTO>();
		for(MaintenanceDetail history : historyDAO.findByProperty(property, object)) {
			MaintenanceDetailDTO dto = ConvertDTO.convertHistoryToDTO(history);
			list.add(dto);
		}
		return list;
	}

	public MaintenanceDetailDTO findById(int id) {
		// TODO Auto-generated method stub
		MaintenanceDetail history = historyDAO.findById(MaintenanceDetail.class, id);
		MaintenanceDetailDTO dto = ConvertDTO.convertHistoryToDTO(history);
		return dto;
	}

	public void add(MaintenanceDetailDTO historyDTO) throws Exception {
		// TODO Auto-generated method stub
		MaintenanceDetail history = new MaintenanceDetail();
		history.setActiveFlag(1);
		history.setCreateDate(new Date());
		history.setDescription(historyDTO.getDescription());
		if(!historyDTO.getMultipartFile().isEmpty()) {
			String images = System.currentTimeMillis()+"_"+historyDTO.getMultipartFile().getOriginalFilename();
			uploadFile(historyDTO.getMultipartFile(),images);
			history.setImgUrl("/upload/"+images);			
		}
		history.setMaintenance(new Maintenance(historyDTO.getMaintenanceId()));
		history.setUpdateDate(new Date());
		historyDAO.insert(history);
	}
	public void uploadFile(MultipartFile multipartFile,String images ) throws IOException {
		File file = new File(Constant.ABSOLUTE_PATH + images);
		String realPath = context.getRealPath("/")+"upload\\"+images;
		byte[] bytes = multipartFile.getBytes();
		BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(file));
		stream.write(bytes);
		stream.close();
		multipartFile.transferTo(new File(realPath));
		
	}
}
