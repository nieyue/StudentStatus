package com.nieyue.controller;

import com.nieyue.bean.Account;
import com.nieyue.exception.*;
import com.nieyue.service.AccountService;
import com.nieyue.util.MyDESutil;
import com.nieyue.util.ResultUtil;
import com.nieyue.util.StateResultList;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;


/**
 * 账户控制类
 * @author yy
 *
 */
@Api(tags={"account"},value="账户",description="账户管理")
@RestController
@RequestMapping("/account")
public class AccountController {
	@Resource
	private AccountService accountService;
	
	/**
	 * 账户分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "账户列表", notes = "账户分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="realname",value="姓名",dataType="string", paramType = "query"),
	  @ApiImplicitParam(name="roleId",value="角色id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="status",value="状态，默认0正常，1封禁，2异常",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> browsePagingAccount(
			@RequestParam(value="realname",required=false)String realname,
			@RequestParam(value="roleId",required=false)Integer roleId,
			@RequestParam(value="status",required=false)Integer status,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="create_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<Account> list = new ArrayList<Account>();
			list= accountService.browsePagingAccount(realname,roleId,status,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 账户修改
	 * @return
	 */
	@ApiOperation(value = "账户修改", notes = "账户修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> updateAccount(
			@ModelAttribute Account account,
			HttpSession session)  {
		Account oldAccount = accountService.loadAccount(account.getAccountId());
		if(oldAccount==null){
			throw new AccountIsNotExistException();//账户不存在
		}
		//账户已经存在
		if("学生".equals(oldAccount.getRole().getName())){
			if(accountService.loginAccount(account.getPhone(), null,account.getAccountId())!=null
					||accountService.loginAccount(account.getSid(), null,account.getAccountId())!=null
			){
				throw new AccountIsExistException();//账户已经存在
			}
		}else{
			if(accountService.loginAccount(account.getPhone(), null,account.getAccountId())!=null
			){
				throw new AccountIsExistException();//账户已经存在
			}
		}

		boolean um = accountService.updateAccount(account);
		if(um){
			List<Account> list = new ArrayList<Account>();
			list.add(account);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 账户修改密码
	 * @param accountId 账户id
	 * @param password  新密码
	 * @param oldPassword 旧密码
	 * @return
	 */
	@ApiOperation(value = "账户修改密码", notes = "账户修改密码")
	@ApiImplicitParams({
			@ApiImplicitParam(name="accountId",value="账户id",dataType="int", paramType = "query",required=true),
			@ApiImplicitParam(name="password",value="新密码",dataType="string", paramType = "query",required=true),
			@ApiImplicitParam(name="oldPassword",value="旧密码",dataType="string", paramType = "query",required=true)
	})
	@RequestMapping(value = "/updatePassword", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> updateAccountPassword(
			@RequestParam("accountId")Integer accountId,
			@RequestParam("password")String password,
			@RequestParam(value="oldPassword") String oldPassword,
			HttpSession session)  {
		//判断是否存在
		Account ac = accountService.loadAccount(accountId);
		if(ac==null || ac.getAccountId()==null){
			throw new AccountIsNotExistException();//账户不存在
		}
		if(oldPassword==null||!MyDESutil.getMD5(oldPassword).equals(ac.getPassword())){
			throw new CommonRollbackException("旧密码错误");//旧密码错误
		}
		if(password==null||password.length()<6||password.length()>18){
			throw new CommonRollbackException("密码长度6-18");//旧密码错误

		}
		ac.setPassword(MyDESutil.getMD5(password));
		boolean a = accountService.updateAccount(ac);
		List<Account> list = new ArrayList<Account>();
		list.add(ac);
		return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 账户增加
	 * @return 
	 */
	@ApiOperation(value = "账户增加", notes = "账户增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> addAccount(@ModelAttribute Account account, HttpSession session) {
		String password = account.getPassword();
		if(password==null||password.length()<6||password.length()>18){
			throw new CommonRollbackException("密码长度6-18");//旧密码错误

		}
		account.setPassword(MyDESutil.getMD5(password));
		boolean am = accountService.addAccount(account);
		if(am){
			List<Account> list = new ArrayList<Account>();
			list.add(account);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 账户删除
	 * @return
	 */
	@ApiOperation(value = "账户删除", notes = "账户删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> delAccount(@RequestParam("accountId") Integer accountId,HttpSession session)  {
		Account account = accountService.loadAccount(accountId);
		boolean dm = accountService.delAccount(accountId);
		if(dm){
			List<Account> list = new ArrayList<Account>();
			list.add(account);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 账户浏览数量
	 * @return
	 */
	@ApiOperation(value = "账户数量", notes = "账户数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="realname",value="姓名",dataType="string", paramType = "query"),
			@ApiImplicitParam(name="roleId",value="角色id",dataType="int", paramType = "query"),
			@ApiImplicitParam(name="status",value="状态，默认0正常，1封禁，2异常",dataType="int", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> countAll(
			@RequestParam(value="realname",required=false)String realname,
			@RequestParam(value="roleId",required=false)Integer roleId,
			@RequestParam(value="status",required=false)Integer status,
			HttpSession session)  {
		Integer count = accountService.countAll(realname,roleId,status);
			List<Integer> list = new ArrayList<>();
			list.add(count);
			return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 账户单个加载
	 * @return
	 */
	@ApiOperation(value = "账户单个加载", notes = "账户单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="accountId",value="账户ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Account>> loadAccount(@RequestParam("accountId") Integer accountId,HttpSession session)  {
		List<Account> list = new ArrayList<Account>();
		Account account = accountService.loadAccount(accountId);
			if(account!=null &&!account.equals("")){
				list.add(account);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("账户");//不存在
			}
	}
	
	/**
	 * 登录
	 * @return
	 */
	@ApiOperation(value = "登录", notes = "登录")
	@ApiImplicitParams({
			@ApiImplicitParam(name="adminName",value="账户",dataType="string", paramType = "query",required=true),
			@ApiImplicitParam(name="password",value="密码",dataType="string", paramType = "query",required=true),
			@ApiImplicitParam(name="verificationCode",value="图片验证码",dataType="string", paramType = "query",required=true)
	})
	@RequestMapping(value = "/login", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<Account>> loginAccount(
			@RequestParam(value="adminName") String adminName,
			@RequestParam(value="password") String password,
			@RequestParam(value="verificationCode") String verificationCode,
			HttpSession session)  {
		//1代验证码
		String ran= (String) session.getAttribute("verificationCode");
		if(ran==null||!ran.equals(verificationCode)){
			throw new VerifyCodeErrorException();//验证码
		}
		List<Account> list = new ArrayList<Account>();
		Account account = accountService.loginAccount(adminName, MyDESutil.getMD5(password),null);
			if(account!=null &&!account.equals("")){
				list.add(account);
				session.setAttribute("account",account);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new AccountLoginException();//登录异常
			}
	}
	/**
	 * 是否登录
	 * @return
	 */
	@ApiOperation(value = "是否登录", notes = "是否登录")
	@RequestMapping(value = "/islogin", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> isLoginAccount(
			HttpSession session)  {
		Account account = (Account) session.getAttribute("account");
		List<Account> list = new ArrayList<Account>();
		if(account!=null && !account.equals("")){
			list.add(account);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		throw new AccountIsNotLoginException();//没有登录
	}
	/**
	 * 登出
	 * @return
	 */
	@ApiOperation(value = "登出", notes = "登出")
	@ApiImplicitParams({
			@ApiImplicitParam(name="accountId",value="账户id",dataType="long", paramType = "query",required=true)
	})
	@RequestMapping(value = "/loginout", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Account>> loginoutAccount(
			@RequestParam("accountId") Long accountId,
			HttpSession session)  {
		session.invalidate();
		return ResultUtil.getSlefSRSuccessList(null);
	}

}
