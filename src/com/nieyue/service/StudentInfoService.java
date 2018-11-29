package com.nieyue.service;

import com.nieyue.bean.StudentInfo;

import java.util.List;

/**
 * 学生信息逻辑层接口
 * @author yy
 *
 */
public interface StudentInfoService {
	/** 新增学生信息 */	
	public boolean addStudentInfo(StudentInfo studentInfo) ;
	/** 删除学生信息 */	
	public boolean delStudentInfo(Integer studentInfoId) ;
	/** 更新学生信息*/	
	public boolean updateStudentInfo(StudentInfo studentInfo);
	/** 装载学生信息 */	
	public StudentInfo loadStudentInfo(Integer studentInfoId);
	/** 学生信息总共数目 */	
	public int countAll(Integer accountId);
	/** 分页学生信息信息 */
	public List<StudentInfo> browsePagingStudentInfo(
            Integer accountId,
            int pageNum,
            int pageSize,
            String orderName,
            String orderWay) ;
}
