package com.nieyue.service;

import com.nieyue.bean.Account;

import java.util.List;

/**
 * 账户逻辑层接口
 * @author yy
 *
 */
public interface AccountService {
	/** 新增账户 */	
	public boolean addAccount(Account account) ;
	/** 删除账户 */	
	public boolean delAccount(Integer accountId) ;
	/** 更新账户*/	
	public boolean updateAccount(Account account);
	/** 装载账户 */	
	public Account loadAccount(Integer accountId);
	/** 登录 */
	public Account loginAccount(String adminName,
								String password,
								Integer accountId);
	/** 账户总共数目 */	
	public int countAll(String realname,
						Integer roleId,
						Integer status);
	/** 分页账户信息 */
	public List<Account> browsePagingAccount(
			String realname,
            Integer roleId,
            Integer status,
            int pageNum,
            int pageSize,
            String orderName,
            String orderWay) ;
}
