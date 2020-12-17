package com.tampro.dto;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class MaintenanceDetailDTO  extends Base{
	private int id;
	private String description;
	private int maintenanceId;
	//private MaintenanceDTO maintenanceDTO;
	private MultipartFile multipartFile;
	private String imgUrl;
	private Date dateTo;
	private Date dateFrom;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getMaintenanceId() {
		return maintenanceId;
	}
	public void setMaintenanceId(int maintenanceId) {
		this.maintenanceId = maintenanceId;
	}
	public MultipartFile getMultipartFile() {
		return multipartFile;
	}
	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}
	public String getImgUrl() {
		return imgUrl;
	}
	public void setImgUrl(String imgUrl) {
		this.imgUrl = imgUrl;
	}
	public Date getDateTo() {
		return dateTo;
	}
	public void setDateTo(Date dateTo) {
		this.dateTo = dateTo;
	}
	public Date getDateFrom() {
		return dateFrom;
	}
	public void setDateFrom(Date dateFrom) {
		this.dateFrom = dateFrom;
	}
//	public MaintenanceDTO getMaintenanceDTO() {
//		return maintenanceDTO;
//	}
//	public void setMaintenanceDTO(MaintenanceDTO maintenanceDTO) {
//		this.maintenanceDTO = maintenanceDTO;
//	}
	
	
	
}
