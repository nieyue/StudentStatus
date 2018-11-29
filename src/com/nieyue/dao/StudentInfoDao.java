package com.nieyue.dao;

import com.nieyue.bean.StudentInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 学生信息数据库接口
 * @author yy
 *
 */
@Mapper
public interface StudentInfoDao {
    /** 新增学生信息*/
    public boolean addStudentInfo(StudentInfo studentInfo) ;
    /** 删除学生信息 */
    public boolean delStudentInfo(Integer studentInfoId) ;
    /** 更新学生信息*/
    public boolean updateStudentInfo(StudentInfo studentInfo);
    /** 装载学生信息 */
    public StudentInfo loadStudentInfo(Integer studentInfoId);
    /** 学生信息总共数目 */
    public int countAll(@Param("accountId") Integer accountId);
    /** 分页学生信息信息 */
    public List<StudentInfo> browsePagingStudentInfo(
            @Param("accountId") Integer accountId,
            @Param("pageNum") int pageNum,
            @Param("pageSize") int pageSize,
            @Param("orderName") String orderName,
            @Param("orderWay") String orderWay) ;
}