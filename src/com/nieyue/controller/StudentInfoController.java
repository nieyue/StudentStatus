package com.nieyue.controller;

import com.nieyue.bean.StudentInfo;
import com.nieyue.exception.NotAnymoreException;
import com.nieyue.exception.NotIsNotExistException;
import com.nieyue.service.StudentInfoService;
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
 * 学生信息控制类
 * @author yy
 *
 */
@Api(tags={"studentInfo"},value="学生信息",description="学生信息管理")
@RestController
@RequestMapping("/studentInfo")
public class StudentInfoController {
	@Resource
	private StudentInfoService studentInfoService;
	
	/**
	 * 学生信息分页浏览
	 * @param orderName 商品排序数据库字段
	 * @param orderWay 商品排序方法 asc升序 desc降序
	 * @return
	 */
	@ApiOperation(value = "学生信息列表", notes = "学生信息分页浏览")
	@ApiImplicitParams({
	  @ApiImplicitParam(name="accountId",value="账户id",dataType="int", paramType = "query"),
	  @ApiImplicitParam(name="pageNum",value="页头数位",dataType="int", paramType = "query",defaultValue="1"),
	  @ApiImplicitParam(name="pageSize",value="每页数目",dataType="int", paramType = "query",defaultValue="10"),
	  @ApiImplicitParam(name="orderName",value="排序字段",dataType="string", paramType = "query",defaultValue="create_date"),
	  @ApiImplicitParam(name="orderWay",value="排序方式",dataType="string", paramType = "query",defaultValue="desc")
	  })
	@RequestMapping(value = "/list", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<StudentInfo>> browsePagingStudentInfo(
			@RequestParam(value="accountId",required=false)Integer accountId,
			@RequestParam(value="pageNum",defaultValue="1",required=false)int pageNum,
			@RequestParam(value="pageSize",defaultValue="10",required=false) int pageSize,
			@RequestParam(value="orderName",required=false,defaultValue="update_date") String orderName,
			@RequestParam(value="orderWay",required=false,defaultValue="desc") String orderWay)  {
			List<StudentInfo> list = new ArrayList<StudentInfo>();
			list= studentInfoService.browsePagingStudentInfo(accountId,pageNum, pageSize, orderName, orderWay);
			if(list.size()>0){
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotAnymoreException();//没有更多
			}
	}
	/**
	 * 学生信息修改
	 * @return
	 */
	@ApiOperation(value = "学生信息修改", notes = "学生信息修改")
	@RequestMapping(value = "/update", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<StudentInfo>> updateStudentInfo(@ModelAttribute StudentInfo studentInfo,HttpSession session)  {
		boolean um = studentInfoService.updateStudentInfo(studentInfo);
		if(um){
			List<StudentInfo> list = new ArrayList<>();
			list.add(studentInfo);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 学生信息增加
	 * @return 
	 */
	@ApiOperation(value = "学生信息增加", notes = "学生信息增加")
	@RequestMapping(value = "/add", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<StudentInfo>> addStudentInfo(@ModelAttribute StudentInfo studentInfo, HttpSession session) {
		boolean am = studentInfoService.addStudentInfo(studentInfo);
		if(am){
			List<StudentInfo> list = new ArrayList<>();
			list.add(studentInfo);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 学生信息删除
	 * @return
	 */
	@ApiOperation(value = "学生信息删除", notes = "学生信息删除")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="studentInfoId",value="学生信息ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/delete", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody  StateResultList<List<StudentInfo>> delStudentInfo(@RequestParam("studentInfoId") Integer studentInfoId,HttpSession session)  {
		StudentInfo studentInfo= studentInfoService.loadStudentInfo(studentInfoId);
		boolean dm = studentInfoService.delStudentInfo(studentInfoId);
		if(dm){
			List<StudentInfo> list = new ArrayList<>();
			list.add(studentInfo);
			return ResultUtil.getSlefSRSuccessList(list);
		}
		return ResultUtil.getSlefSRFailList(null);
	}
	/**
	 * 学生信息浏览数量
	 * @return
	 */
	@ApiOperation(value = "学生信息数量", notes = "学生信息数量查询")
	@ApiImplicitParams({
			@ApiImplicitParam(name="accountId",value="账户id",dataType="int", paramType = "query"),
	})
	@RequestMapping(value = "/count", method = {RequestMethod.GET,RequestMethod.POST})
	public @ResponseBody StateResultList<List<Integer>> countAll(
			@RequestParam(value="accountId",required=false)Integer accountId,
			HttpSession session)  {
		Integer count = studentInfoService.countAll(accountId);
			List<Integer> list = new ArrayList<>();
			list.add(count);
			return ResultUtil.getSlefSRSuccessList(list);
	}
	/**
	 * 学生信息单个加载
	 * @return
	 */
	@ApiOperation(value = "学生信息单个加载", notes = "学生信息单个加载")
	@ApiImplicitParams({
		  @ApiImplicitParam(name="studentInfoId",value="学生信息ID",dataType="int", paramType = "query",required=true)
		  })
	@RequestMapping(value = "/load", method = {RequestMethod.GET,RequestMethod.POST})
	public  StateResultList<List<StudentInfo>> loadStudentInfo(@RequestParam("studentInfoId") Integer studentInfoId,HttpSession session)  {
		List<StudentInfo> list = new ArrayList<StudentInfo>();
		StudentInfo studentInfo = studentInfoService.loadStudentInfo(studentInfoId);
			if(studentInfo!=null &&!studentInfo.equals("")){
				list.add(studentInfo);
				return ResultUtil.getSlefSRSuccessList(list);
			}else{
				throw new NotIsNotExistException("学生信息");//不存在
			}
	}
	
}
