package com.tampro.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.BrandDAO;
import com.tampro.entity.Brand;
@Repository
@Transactional(rollbackFor = Exception.class)
public class BrandDAOImpl  extends BaseDAOImpl<Brand> implements BrandDAO<Brand>{

}
