package com.nieyue.service.impl;

import com.nieyue.bean.StudentInfo;
import com.nieyue.dao.StudentInfoDao;
import com.nieyue.service.StudentInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class StudentInfoServiceImpl implements StudentInfoService{
	@Autowired
	StudentInfoDao studentInfoDao;
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean addStudentInfo(StudentInfo studentInfo) {
		studentInfo.setCreateDate(new Date());
		studentInfo.setUpdateDate(new Date());
		boolean b = studentInfoDao.addStudentInfo(studentInfo);
		return b;
	}
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public boolean delStudentInfo(Integer studentInfo) {
		boolean b = studentInfoDao.delStudentInfo(studentInfo);
		return b;
	}
	@Transactional(propagation= Propagation.REQUIRED)
	@Override
	public boolean updateStudentInfo(StudentInfo studentInfo) {
		boolean b = studentInfoDao.updateStudentInfo(studentInfo);
		return b;
	}

	@Override
	public StudentInfo loadStudentInfo(Integer studentInfo) {
		StudentInfo r = studentInfoDao.loadStudentInfo(studentInfo);
		return r;
	}

	@Override
	public int countAll(Integer accountId) {
		int c = studentInfoDao.countAll(accountId);
		return c;
	}

	@Override
	public List<StudentInfo> browsePagingStudentInfo(
			Integer accountId,
			int pageNum, int pageSize,
			String orderName, String orderWay) {
		if(pageNum<1){
			pageNum=1;
		}
		if(pageSize<1){
			pageSize=0;//没有数据
		}
		List<StudentInfo> l = studentInfoDao.browsePagingStudentInfo(
				accountId,
				pageNum-1, pageSize, orderName, orderWay);
		return l;
	}

	
}
