package com.nieyue.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 学生信息
 */
@ApiModel(value="学生信息",description="学生信息")
@Data
public class StudentInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 学生信息id
	 */
	@ApiModelProperty(value="学生信息id")
	private Integer studentInfoId;
	/**
	 * 报考账号
	 */
	@ApiModelProperty(value="报考账号")
	private String examinationAccount;
	/**
	 * 报考密码
	 */
	@ApiModelProperty(value="报考密码")
	private String examinationPasword;
	/**
	 * 推荐人
	 */
	@ApiModelProperty(value="推荐人")
	private String recommender;
	/**
	 * 报送主管单位
	 */
	@ApiModelProperty(value="报送主管单位")
	private String directorUnit;
	/**
	 * 报送单位收费金额
	 */
	@ApiModelProperty(value="报送单位收费金额")
	private Double unitCharge;
	/**
	 * 证书查询认证网址
	 */
	@ApiModelProperty(value="证书查询认证网址")
	private String cerQueryWebsite;

	/**
	 * 链接
	 */
	@ApiModelProperty(value="链接")
	private String link;
	/**
	 * 录入老师姓名
	 */
	@ApiModelProperty(value="录入老师姓名")
	private String inputTeacher;
	/**
	 * 入学年份
	 */
	@ApiModelProperty(value="入学年份")
	private String enrollmentYear;
	/**
	 * 报考院校
	 */
	@ApiModelProperty(value="报考院校")
	private String examinationSchool;
	/**
	 * 考点
	 */
	@ApiModelProperty(value="考点")
	private String examinationPoint;
	/**
	 * 科类
	 */
	@ApiModelProperty(value="科类")
	private String sectionType;
	/**
	 * 学习形式
	 */
	@ApiModelProperty(value="学习形式")
	private String learnMethod;
	/**
	 * 代理（招生员）
	 */
	@ApiModelProperty(value="代理（招生员）")
	private String recruiter;
	/**
	 * 注册地
	 */
	@ApiModelProperty(value="注册地")
	private String regPlace;

	/**
	 * 第1年注册(金额)
	 */
	@ApiModelProperty(value="第1年注册(金额)")
	private Double regMoney1;
	/**
	 * 第2年注册(金额)
	 */
	@ApiModelProperty(value="第2年注册(金额)")
	private Double regMoney2;
	/**
	 * 第3年注册(金额)
	 */
	@ApiModelProperty(value="第3年注册(金额)")
	private Double regMoney3;
	/**
	 * 毕业时间
	 */
	@ApiModelProperty(value="毕业时间",example="毕业时间")
	private Date graduationDate;
	/**
	 * 备注
	 */
	@ApiModelProperty(value="备注")
	private String mark;
	/**
	 * 创建时间
	 */
	@ApiModelProperty(value="创建时间",example="创建时间")
	private Date createDate;
	/**
	 * 更新时间
	 */
	@ApiModelProperty(value="更新时间",example="更新时间")
	private Date updateDate;
	/**
	 * 账户id外键
	 */
	@ApiModelProperty(value="账户id外键")
	private Integer accountId;
	}
