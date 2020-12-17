package com.tampro.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.HistoryDAO;
import com.tampro.entity.MaintenanceDetail;
@Repository
@Transactional(rollbackFor = Exception.class)
public class HistoryDAOImpl extends BaseDAOImpl<MaintenanceDetail> implements HistoryDAO<MaintenanceDetail>{

}
