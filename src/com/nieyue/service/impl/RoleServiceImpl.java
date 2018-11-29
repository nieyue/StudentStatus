package com.nieyue.service.impl;

import com.nieyue.bean.Role;
import com.nieyue.dao.RoleDao;
import com.nieyue.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
@Service
public class RoleServiceImpl implements RoleService{
	@Resource
	RoleDao roleDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addRole(Role role) {
		role.setUpdateDate(new Date());
		boolean b = roleDao.addRole(role);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delRole(Integer roleId) {
		boolean b = roleDao.delRole(roleId);
		return b;
	}
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public boolean updateRole(Role role) {
		role.setUpdateDate(new Date());
		boolean b = roleDao.updateRole(role);
		return b;
	}

	@Override
	public Role loadRole(Integer roleId) {
		Role r = roleDao.loadRole(roleId);
		return r;
	}

	@Override
	public int countAll() {
		int c = roleDao.countAll();
		return c;
	}

	@Override
	public List<Role> browsePagingRole(int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Role> l = roleDao.browsePagingRole(pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
