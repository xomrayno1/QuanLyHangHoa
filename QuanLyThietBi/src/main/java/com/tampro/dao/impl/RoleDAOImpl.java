package com.tampro.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.tampro.dao.RoleDAO;
import com.tampro.entity.Roles;

@Repository
@Transactional(rollbackFor = Exception.class)
public class RoleDAOImpl extends BaseDAOImpl<Roles> implements RoleDAO<Roles> {

}
