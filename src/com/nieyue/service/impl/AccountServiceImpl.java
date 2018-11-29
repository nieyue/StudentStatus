package com.nieyue.service.impl;

import com.nieyue.bean.Account;
import com.nieyue.bean.Role;
import com.nieyue.dao.AccountDao;
import com.nieyue.service.AccountService;
import com.nieyue.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService{
	@Autowired
	AccountDao accountDao;
	@Autowired
	RoleService roleService;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addAccount(Account account) {
		account.setCreateDate(new Date());
		account.setLoginDate(new Date());
		boolean b = accountDao.addAccount(account);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delAccount(Integer accountId) {
		boolean b = accountDao.delAccount(accountId);
		return b;
	}
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public boolean updateAccount(Account account) {
		boolean b = accountDao.updateAccount(account);
		return b;
	}

	@Override
	public Account loadAccount(Integer accountId) {
		Account r = accountDao.loadAccount(accountId);
		if(r!=null){
			Role role = roleService.loadRole(r.getRoleId());
			r.setRole(role);
		}
		return r;
	}

	@Override
	public Account loginAccount(String adminName, String password,Integer accountId) {
		Account r = accountDao.loginAccount(adminName,password,accountId);
		if(r!=null){
			Role role = roleService.loadRole(r.getRoleId());
			r.setRole(role);
		}
		return r;
	}

	@Override
	public int countAll(String realname,
						Integer roleId,
						Integer status) {
		int c = accountDao.countAll(realname,roleId,status);
		return c;
	}

	@Override
	public List<Account> browsePagingAccount(
			String realname,
			Integer roleId,
			Integer status,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<Account> l = accountDao.browsePagingAccount(
				realname,roleId,status,
				pageNum-1, pageSize, orderName, orderWay);
		l.forEach(e->{
			if(e!=null){
				Role role = roleService.loadRole(e.getRoleId());
				e.setRole(role);
			}
		});
		return l;
	}

	
}
